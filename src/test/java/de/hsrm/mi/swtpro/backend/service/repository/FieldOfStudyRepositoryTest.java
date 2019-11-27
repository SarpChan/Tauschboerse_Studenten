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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FieldOfStudyRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    FieldOfStudyRepository fieldOfStudyRepository;

    University university;
    StudyProgram studyProgram;

    @Before
    public void setUp(){

        university = University.builder()
                .name("Hauptschule RheinMain")
                .adress("76213876 uertoi3 09ujwdfij")
                .build();

        studyProgram = StudyProgram.builder()
                .title("Dongel")
                .build();

        FieldOfStudy fieldOfStudy = FieldOfStudy.builder()
                .id(17)
                .title("Faulenzen")
                .studyPrograms(new HashSet<>(Collections.singleton(studyProgram)))
                .university(university)
                .build();

        entityManager.persist(university);
        entityManager.persist(studyProgram);
        entityManager.persist(fieldOfStudy);
    }

    @Test
    public void whenFindAll_thenReturnFieldOfStudyList(){
        assertThat(fieldOfStudyRepository.findAll(),hasItem(
                hasProperty("id",is(17))
        ));
    }

    @Test
    public void whenFindById_thenReturnFieldOfStudyList(){
        assertThat(fieldOfStudyRepository.findById(17),
                hasProperty("title",is("Faulenzen")));
    }

    @Test
    public void whenFindByStudyProgram_thenReturnFieldOfStudyList(){
        assertThat(fieldOfStudyRepository.findByStudyPrograms(studyProgram),hasItem(
                hasProperty("id",is(17))
        ));
    }

    @Test
    public void whenFindByUniversity_thenReturnFieldOfStudyList(){
        assertThat(fieldOfStudyRepository.findByUniversity(university),hasItem(
                hasProperty("id",is(17))
        ));
    }

}
