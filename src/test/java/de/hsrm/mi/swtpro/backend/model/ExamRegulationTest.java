package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ExamRegulationTest {

    private ExamRegulation examRegulation;
    private StudyProgram studyProgram;
    private Curriculum curriculum;

    @Before
    public void setUp(){
        studyProgram = StudyProgram.builder()
                .title("Medieninformatik")
                .degree("Bachlor")
                .build();
        examRegulation = new ExamRegulation.Builder(Date.valueOf(LocalDate.of(2017,7,17)))
                .forStudyProgram(studyProgram)
                .build();
        curriculum = new Curriculum.Builder(examRegulation).build();
    }

    @Test
    public void whenGetDate_thenReturnDate(){
        assertEquals(Date.valueOf(LocalDate.of(2017,7,17)).toString(),examRegulation.getDate().toString());
    }

    @Test
    public void whenGetStudyProgram_thenReturnStudyProgram(){
        assertEquals("Medieninformatik",examRegulation.getStudyProgram().getTitle());
    }

    @Test
    public void whenSetId_thenSaveId(){
        examRegulation.setId(17);
        assertEquals(17,examRegulation.getId());
    }

    @Test
    public void whenSetDate_thenSaveDate(){
        examRegulation.setDate(Date.valueOf(LocalDate.of(2020,7,21)));
        assertEquals(Date.valueOf(LocalDate.of(2020,7,21)).toString(),examRegulation.getDate().toString());
    }

    @Test
    public void whenSetStudyProgram_thenSaveStudyProgram(){
        studyProgram = new StudyProgram.Builder("anderer Studiengang","Master").build();

        examRegulation.setStudyProgram(studyProgram);
        assertEquals("anderer Studiengang",examRegulation.getStudyProgram().getTitle());
    }


}
