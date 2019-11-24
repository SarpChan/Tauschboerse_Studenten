package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

@SpringBootTest
public class FieldOfStudyTest {

    private FieldOfStudy fieldOfStudy;
    private StudyProgram studyProgram;
    private University university;
    private Term term;

    @Before
    public void setUp(){
        university = University.builder()
                .name("Störtebecker Hochschule")
                .adress("Adelheidstraße 84, 65185 Wiesbaden")
                .build();

        studyProgram = StudyProgram.builder()
                .title("MI")
                .degree("Master")
                .build();

        term = Term.builder()
                .endDate(Date.valueOf(LocalDate.MIN))
                .build();
        fieldOfStudy = FieldOfStudy.builder()
                .title("Informatik")
                .build();
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
        Set<StudyProgram> studyProgramList = new HashSet<>();
        studyProgramList.add(studyProgram);
        fieldOfStudy.setStudyPrograms(studyProgramList);
        assertThat(fieldOfStudy.getStudyPrograms(),hasItem(studyProgram));
    }

    @Test
    public void whenSetUniversity_thenSaveUniversity(){
        fieldOfStudy.setUniversity(university);
        assertEquals("Störtebecker Hochschule",fieldOfStudy.getUniversity().getName());
    }

    @Test
    public void whenSetTerm_thenSaveTerm(){
        assertTrue("Term -> Attribut fehlt ",false);
    }

}
