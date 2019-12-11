package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.ModuleInCurriculum;
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
public class CurriculumRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    CurriculumRepository curriculumRepository;

    private Module module;
    private ModuleInCurriculum moduleInCurriculum;
    private ExamRegulation examRegulation;

    private long id;

    @Before
    public void setUp(){

        module = Module.builder()
                .title("cooles Module")
                .build();


        examRegulation = ExamRegulation.builder()
                .rule(17)
                .build();

        Curriculum curriculum = Curriculum.builder()
                .examRegulation(examRegulation)
                .termPeriod(1)
                .build();

        moduleInCurriculum = ModuleInCurriculum.builder()
                .termPeriod(10)
                .curriculum(curriculum)
                .module(module)
                .build();

        entityManager.persist(module);
        entityManager.persist(moduleInCurriculum);
        entityManager.persist(examRegulation);
        entityManager.persist(curriculum);
        id = curriculum.getId();
    }

    @Test
    public void whenFindAll_thenReturnCurriculumList(){
        assertThat(curriculumRepository.findAll(),hasItem(
                hasProperty("termPeriod",is(1))
        ));
    }

    @Test
    public void whenFindById_thenReturnCurriculumList(){
        assertTrue(curriculumRepository.findById(id).isPresent());
        assertThat(curriculumRepository.findById(id).get(), hasProperty("termPeriod",is(1)));
    }

    @Test
    public void whenFindByPeriod_thenReturnCurriculumList(){
        assertThat(curriculumRepository.findByTermPeriod(1),hasItem(
                hasProperty("id",is(id))
        ));
    }

    @Test
    public void whenFindByModules_thenReturnCurriculumList(){
        assertThat(curriculumRepository.findByModulesInCurriculum(moduleInCurriculum),hasItem(
                hasProperty("termPeriod",is(1))
        ));
    }

    @Test
    public void whenFindByExamRegulation_thenReturnCurriculumList(){
        assertThat(curriculumRepository.findByExamRegulation(examRegulation),
                hasItem(hasProperty("termPeriod",is(1)))
        );
    }
}
