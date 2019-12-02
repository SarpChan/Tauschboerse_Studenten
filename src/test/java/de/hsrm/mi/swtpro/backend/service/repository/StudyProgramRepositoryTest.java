package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.StudyProgram;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.*;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudyProgramRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudyProgramRepository studyProgramRepository;

    @Before
    public void setUp(){
        StudyProgram  studyProgram = StudyProgram.builder()
                .degree("Bsc")
                .title("Mind")
                .build();

        entityManager.persist(studyProgram);
    }

    @Test
    public void whenFindById_thenReturnStudyProgram(){
            //TODO: ID gehen noch nicht richtig. Test folgend implementieren
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
