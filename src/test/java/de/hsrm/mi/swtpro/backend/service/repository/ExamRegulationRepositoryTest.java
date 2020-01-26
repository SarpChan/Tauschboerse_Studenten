package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.StudyProgram;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExamRegulationRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    ExamRegulationRepository examRegulationRepository;

    private Curriculum curriculum;
    private StudyProgram studyProgram;
    private long id;

    @Before
    public void setUp(){

        studyProgram = StudyProgram.builder()
                .title("Heinrich")
                .degree("Bachelor")
                .build();

        ExamRegulation examRegulation = ExamRegulation.builder()
                .date(Date.valueOf(LocalDate.of(2019,4,17)))
                .rule(17)
                .studyProgram(studyProgram)
                .build();

        curriculum = Curriculum.builder()
                .maxTerms(1)
                .examRegulation(examRegulation)
                .build();


        entityManager.persist(studyProgram);
        entityManager.persist(examRegulation);
        entityManager.persist(curriculum);

        id = examRegulation.getId();
    }

    @Test
    public void whenFindAll_thenReturnExamRegulationList(){
        assertThat(examRegulationRepository.findAll(),hasItem(
                hasProperty("rule",is(17))
        ));
    }

    @Test
    public void whenFindById_thenReturnExamRegulation(){
        assertTrue(examRegulationRepository.findById(id).isPresent());
        assertThat(examRegulationRepository.findById(id).get(),hasProperty(
                "rule",is(17)
        ));
    }

    @Test
    public void whenFindByRule_thenReturnExamRegulation(){
        assertThat(examRegulationRepository.findByRule(17),
                hasItem(hasProperty("id",is(id)))
        );
    }

    @Test
    public void whenFindByDate_thenReturnExamRegulationList(){
        assertThat(examRegulationRepository.findByDate(Date.valueOf(LocalDate.of(2019,4,17))),
                        hasItem(hasProperty("rule",is(17)))
        );
    }

    @Test
    public void whenFindByCurricula_thenReturnExamRegulationList(){
        assertThat(examRegulationRepository.findByCurriculums(curriculum),
                hasItem(hasProperty("rule",is(17))));
    }

    @Test
    public void whenFindByStudyProgram_thenReturnExamRegulationList(){
        assertThat(examRegulationRepository.findByStudyProgram(studyProgram),
                hasItem(hasProperty("rule",is(17))));
    }
}
