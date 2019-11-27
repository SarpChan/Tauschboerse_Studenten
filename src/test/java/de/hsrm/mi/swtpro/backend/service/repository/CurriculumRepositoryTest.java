package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.Module;
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
public class CurriculumRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    CurriculumRepository curriculumRepository;

    private Module module;
    private ExamRegulation examRegulation;

    @Before
    public void setUp(){

        module = Module.builder()
                .id(42)
                .build();

        examRegulation = ExamRegulation.builder()
                .id(72)
                .build();

        Curriculum curriculum = Curriculum.builder()
                .id(17)
                .examRegulation(examRegulation)
                .term(1)
                .modules(new HashSet<>(Collections.singleton(module)))
                .build();

        entityManager.persist(module);
        entityManager.persist(examRegulation);
        entityManager.persist(curriculum);
    }

    @Test
    public void whenFindAll_thenReturnCurriculumList(){
        assertThat(curriculumRepository.findAll(),hasItem(
                hasProperty("id",is(17))
        ));
    }

    @Test
    public void whenFindById_thenReturnCurriculumList(){
        assertThat(curriculumRepository.findById(17), hasProperty("term",is(1)));
    }

    @Test
    public void whenFindByPeriod_thenReturnCurriculumList(){
        assertThat(curriculumRepository.findByTerm(1),hasItem(
                hasProperty("id",is(17))
        ));
    }

    @Test
    public void whenFindByModules_thenReturnCurriculumList(){
        assertThat(curriculumRepository.findByModules(module),hasItem(
                hasProperty("id",is(17))
        ));
    }

    @Test
    public void whenFindByExamRegulation_thenReturnCurriculumList(){
        assertThat(curriculumRepository.findByExamRegulation(examRegulation),
                hasItem(hasProperty("id",is(17)))
        );
    }
}
