package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class FieldOfStudyTest {

    private FieldOfStudy fieldOfStudy;
    private StudyProgram studyProgram;
    private University university;

    @Before
    public void setUp(){
        university = University.builder()
                .name("Störtebecker Hochschule")
                .address("Adelheidstraße 84, 65185 Wiesbaden")
                .build();

        studyProgram = StudyProgram.builder()
                .title("MI")
                .degree("Master")
                .build();

        fieldOfStudy = FieldOfStudy.builder()
                .id(17)
                .title("Informatik")
                .university(university)
                .studyProgram(studyProgram)
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
}
