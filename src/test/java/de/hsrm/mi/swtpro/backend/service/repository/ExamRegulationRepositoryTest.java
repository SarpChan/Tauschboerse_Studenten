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

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExamRegulationRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    ExamRegulationRepository examRegulationRepository;

    private Curriculum curriculum;
    private StudyProgram studyProgram;

    @Before
    public void setUp(){
        curriculum = Curriculum.builder()
                .id(42)
                .build();

        studyProgram = StudyProgram.builder()
                .title("Heinrich")
                .build();


        ExamRegulation examRegulation = ExamRegulation.builder()
                .id(17)
                .date(Date.valueOf(LocalDate.of(2019,4,17)))
                .rule(17)
                .curriculum(curriculum)
                .studyProgram(studyProgram)
                .build();

        entityManager.persist(curriculum);
        entityManager.persist(studyProgram);
        entityManager.persist(examRegulation);
    }

    @Test
    public void whenFindAll_thenReturnExamRegulationList(){
        assertThat(examRegulationRepository.findAll(),hasItem(
                hasProperty("id",is(17))
        ));
    }

    @Test
    public void whenFindById_thenReturnExamRegulation(){
        assertThat(examRegulationRepository.findById(17),hasProperty(
                "rule",is(17)
        ));
    }

    @Test
    public void whenFindByRule_thenReturnExamRegulation(){
        assertThat(examRegulationRepository.findByRule(17),
                hasItem(hasProperty("id",is(17)))
        );
    }

    @Test
    public void whenFindByDate_thenReturnExamRegulationList(){
        assertThat(examRegulationRepository.findByDate(Date.valueOf(LocalDate.of(2019,4,17))),
                        hasItem(hasProperty("id",is(17)))
        );
    }

    @Test
    public void whenFindByCurricula_thenReturnExamRegulationList(){
        assertThat(examRegulationRepository.findByCurriculums(curriculum),
                hasItem(hasProperty("id",is(17))));
    }

    @Test
    public void whenFindByStudyProgram_thenReturnExamRegulationList(){
        assertThat(examRegulationRepository.findByStudyProgram(studyProgram),
                hasItem(hasProperty("id",is(17))));
    }
}
