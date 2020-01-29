package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
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

import java.sql.Date;

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
    private ExamRegulation examRegulation;
    private FieldOfStudy fieldOfStudy;

    @Before
    public void setUp(){
        University university = University.builder()
                .name("Hochschule RheinMain")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .build();

        fieldOfStudy = FieldOfStudy.builder()
                .title("Medieninformatik")
                .university(university)
                .build();

        StudyProgram  studyProgram = StudyProgram.builder()
                .degree("Bsc")
                .fieldOfStudy(fieldOfStudy)
                .title("Mind")
                .build();

        examRegulation = ExamRegulation.builder()
                .studyProgram(studyProgram)
                .date(new Date(System.currentTimeMillis()))
                .build();

        entityManager.persist(university);
        entityManager.persist(studyProgram);
        entityManager.persist(fieldOfStudy);
        entityManager.persist(examRegulation);
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

    @Test
    public void whenFindByExamRegulation_thenReturnStudyProgramList(){
        assertThat(studyProgramRepository.findByExamRegulations(examRegulation),hasItem(
                hasProperty("degree", is("Bsc")))
        );
    }

    @Test
    public void whenFindByFieldOfStudy_thenReturnStudyProgramList(){
        assertThat(studyProgramRepository.findByFieldsOfStudy(fieldOfStudy),hasItem(
                hasProperty("degree", is("Bsc")))
        );
    }
}
