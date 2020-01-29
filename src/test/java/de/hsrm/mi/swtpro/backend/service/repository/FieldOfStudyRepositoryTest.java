package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.FieldOfStudy;
import de.hsrm.mi.swtpro.backend.model.StudyProgram;
import de.hsrm.mi.swtpro.backend.model.University;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FieldOfStudyRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    FieldOfStudyRepository fieldOfStudyRepository;

    @Autowired
    StudyProgramRepository studyProgramRepository;

    @Autowired
    UniversityRepository universityRepository;

    private University university;
    private StudyProgram studyProgram;
    private long id;

    @Before
    public void setUp(){

        university = University.builder()
                .name("Hauptschule RheinMain")
                .address("76213876 uertoi3 09ujwdfij")
                .build();


        FieldOfStudy fieldOfStudy = FieldOfStudy.builder()
                .title("Faulenzen")
                .university(university)
                .build();

        studyProgram = StudyProgram.builder()
                .title("Dongel")
                .degree("Bachelor")
                .fieldOfStudy(fieldOfStudy)
                .build();

        entityManager.persist(university);
        entityManager.persist(fieldOfStudy);
        entityManager.persist(studyProgram);
        id = fieldOfStudy.getId();
    }

    @Test
    public void whenFindAll_thenReturnFieldOfStudyList(){
        assertThat(fieldOfStudyRepository.findAll(),hasItem(
                hasProperty("title",is("Faulenzen"))
        ));
    }

    @Test
    public void whenFindById_thenReturnFieldOfStudyList(){
        assertTrue(fieldOfStudyRepository.findById(id).isPresent());
        assertThat(fieldOfStudyRepository.findById(id).get(),
                hasProperty("title",is("Faulenzen")));
    }

    @Test
    public void whenFindByStudyProgram_thenReturnFieldOfStudyList(){
        List c = fieldOfStudyRepository.findByStudyPrograms(studyProgram);
        List c1 = fieldOfStudyRepository.findAll();
        assertThat(fieldOfStudyRepository.findByStudyPrograms(studyProgram),hasItem(
                hasProperty("title",is("Faulenzen"))
        ));
    }

    @Test
    public void whenFindByUniversity_thenReturnFieldOfStudyList(){
        assertThat(fieldOfStudyRepository.findByUniversity(university),hasItem(
                hasProperty("title",is("Faulenzen"))
        ));
    }

    @Test
    public void whenFindByTitle_thenReturnFieldOfStudyList(){
        assertThat(fieldOfStudyRepository.findByTitle("Faulenzen"),hasItem(
                hasProperty("id",is(id))
        ));
    }
}
