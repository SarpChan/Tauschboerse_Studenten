package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashSet;
import static org.junit.Assert.assertThat;
import static  org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.hasItem;

@SpringBootTest
public class StudyProgramTest {

    private StudyProgram studyProgram;
    private FieldOfStudy fieldOfStudy;
    private ExamRegulation examRegulation;

    @Before
    public void setUp(){

        fieldOfStudy = FieldOfStudy.builder().title("Natur").build();
        examRegulation = ExamRegulation.builder().id(12).build();

        studyProgram = StudyProgram.builder()
                .id(123)
                .title("Informatik")
                .degree("Bsc")
                .fieldsOfStudy(new HashSet<>(Collections.singletonList(fieldOfStudy)))
                .examRegulations(new HashSet<>(Collections.singletonList(examRegulation)))
                .build();

    }

    @Test
    public void whenGetId_thanReturnId(){assertEquals(studyProgram.getId(), 123);}

    @Test
    public void whenGetTitle_thanReturnTitle(){assertEquals(studyProgram.getTitle(), "Informatik");}

    @Test
    public void whenGetDegree_thanReturnDegree(){assertEquals(studyProgram.getDegree(), "Bsc");}

    @Test
    public void whenGetFieldsOfStudy_thanReturnFieldsOfStudy(){assertThat(studyProgram.getFieldsOfStudy(), hasItem(fieldOfStudy));}

    @Test
    public void whenGetExamRegulations_thanReturnExamRegulations(){assertThat(studyProgram.getExamRegulations(), hasItem(examRegulation));}

    @Test
    public void whenSetId_thanSaveId(){
        studyProgram.setId(1756);
        assertEquals(studyProgram.getId(), 1756);
    }

    @Test
    public void whenSetTitle_thanSaveTitle(){
        studyProgram.setTitle("Design");
        assertEquals(studyProgram.getTitle(), "Design");
    }

    @Test
    public void whenSetDegree_thanSaveDegree(){
        studyProgram.setDegree("Msc");
        assertEquals(studyProgram.getDegree(), "Msc");
    }

    @Test
    public void whenSetFieldsOfStudy_thanSaveFieldsOfStudy(){
        FieldOfStudy fos = FieldOfStudy.builder().title("Kunst").build();
        studyProgram.setFieldsOfStudy(new HashSet<>(Collections.singletonList(fos)));
        assertThat(studyProgram.getFieldsOfStudy(), hasItem(fos));
    }

    @Test
    public void whenSetExamsRegulations_thanSaveExamsRegulations(){
        ExamRegulation eg = ExamRegulation.builder().id(666).build();
        studyProgram.setExamRegulations(new HashSet<>(Collections.singletonList(eg)));
        assertThat(studyProgram.getExamRegulations(), hasItem(eg));
    }

}
