package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.StudyProgram;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudyProgramRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudyProgramRepository studyProgramRepository;

    private long id;

    @Before
    public void setUp(){
        StudyProgram  studyProgram = StudyProgram.builder()
                .degree("Bsc")
                .title("Mind")
                .build();

        entityManager.persist(studyProgram);
        id = studyProgram.getId();
    }

    @Test
    public void whenFindById_thenReturnStudyProgram(){
            assertTrue(studyProgramRepository.findById(id).isPresent());
            assertThat(studyProgramRepository.findById(id).get(),
                    hasProperty("title",is("Mind")));
    }

    @Test
    public void whenFindByTitle_thenReturnStudyProgramList(){
        assertThat(studyProgramRepository.findByTitle("Mind"), hasItem(hasProperty("title", is("Mind"))));
    }

    @Test
    public void whenFindByDegree_thenReturnStudyProgramList(){
        assertThat(studyProgramRepository.findByDegree("Bsc"), hasItem(hasProperty("degree", is("Bsc"))));
    }
}
