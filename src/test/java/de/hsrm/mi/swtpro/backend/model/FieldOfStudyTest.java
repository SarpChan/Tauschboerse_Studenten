package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

@SpringBootTest
public class FieldOfStudyTest {

    FieldOfStudy fieldOfStudy;
    StudyProgram studyProgram;
    University university;
    Term term;

    @Before
    public void setUp(){
        university = new University("Störtebecker Hochschule","Adelheidstraße 84, 65185 Wiesbaden");
        studyProgram = new StudyProgram.Builder("MI","Master").build();
        term = new Term.Builder().withEndDate(Date.valueOf(LocalDate.MIN)).build();
        fieldOfStudy = new FieldOfStudy("Informatik");
    }

    @Test
    public void whenGetTitle_thenReturnTitle(){
        assertEquals("Informatik",fieldOfStudy.getTitle());
    }

    @Test
    public void whenSetTitle_thenSaveTitle(){
        fieldOfStudy.setTitle("Buchwissenschaften");
        assertEquals("Buchwissenschaften",fieldOfStudy.getTitle());
    }

    @Test
    public void whenSetStudyProgram_thenSaveStudyProgram(){
        List<StudyProgram> studyProgramList = new ArrayList<>();
        studyProgramList.add(studyProgram);
        fieldOfStudy.setStudyProgram(studyProgramList);
        assertThat(fieldOfStudy.getStudyProgram(),hasItem(studyProgram));
    }

    @Test
    public void whenSetUniversity_thenSaveUniversity(){
        fieldOfStudy.setUniversity(university);
        assertTrue("kein Getter fuer die University",false);
    }

    @Test
    public void whenSetTerm_thenSaveTerm(){
        assertTrue("Term -> Attribut fehlt ",false);
    }

}
