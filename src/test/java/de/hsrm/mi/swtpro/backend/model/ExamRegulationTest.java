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

    @Before
    public void setUp(){
        studyProgram = StudyProgram.builder()
                .title("Medieninformatik")
                .degree("Bachlor")
                .build();

        examRegulation = ExamRegulation.builder()
                .id(17L)
                .date(Date.valueOf(LocalDate.of(2017,7,17)))
                .studyProgram(studyProgram)
                .rule(4)
                .build();
    }

    @Test
    public void whenGetId_thenReturnId(){
        assertEquals(17L,examRegulation.getId());
    }

    @Test
    public void whenGetRule_thenReturnRule(){
        assertEquals(4,examRegulation.getRule());
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
    public void whenSetRule_thenSaveRule(){
        examRegulation.setRule(2);
        assertEquals(2,examRegulation.getRule());
    }

    @Test
    public void whenSetDate_thenSaveDate(){
        examRegulation.setDate(Date.valueOf(LocalDate.of(2020,7,21)));
        assertEquals(Date.valueOf(LocalDate.of(2020,7,21)).toString(),examRegulation.getDate().toString());
    }

    @Test
    public void whenSetStudyProgram_thenSaveStudyProgram(){
        studyProgram = StudyProgram.builder()
                .title("anderer Studiengang")
                .degree("Master")
                .build();

        examRegulation.setStudyProgram(studyProgram);
        assertEquals("anderer Studiengang",examRegulation.getStudyProgram().getTitle());
    }


}
