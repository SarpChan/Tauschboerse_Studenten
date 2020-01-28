package de.hsrm.mi.swtpro.backend.service.repository;


import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.Module;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.sql.Date;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ModuleInCurriculumRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    ModuleInCurriculumRepository studentAttendsCourseRepository;

    private Module module;
    private Curriculum curriculum;
    private long id;

    @Before
    public void setUp(){
        module = Module.builder()
                .title("Titel")
                .build();

        StudyProgram studyProg = StudyProgram.builder()
                .title("test")
                .degree("E")
                .build();

        ExamRegulation examReg = ExamRegulation.builder()
                .date(new Date(System.currentTimeMillis()))
                .studyProgram(studyProg)
                .build();

        curriculum = Curriculum.builder()
                .examRegulation(examReg)
                .build();

        ModuleInCurriculum moduleInCurriculum = ModuleInCurriculum.builder()
                .module(module)
                .curriculum(curriculum)
                .termPeriod(2)
                .build();

        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(curriculum);
        entityManager.persist(module);
        entityManager.persist(moduleInCurriculum);
        id = moduleInCurriculum.getId();
    }

    @Test
    public void whenFindAll_thenReturnStudentAttendsCourseList(){
        assertThat(studentAttendsCourseRepository.findAll(),hasItem(
               hasProperty("termPeriod",is(2))
        ));
    }

    @Test
    public void whenFindById_thenReturnStudentAttendsCourse(){
        assertTrue(studentAttendsCourseRepository.findById(id).isPresent());
        assertThat(studentAttendsCourseRepository.findById(id).get(),
                hasProperty("termPeriod",is(2))
        );
    }

    @Test
    public void whenFindByTermPeriod_thenReturnStudentAttendsCourse(){
        assertThat(studentAttendsCourseRepository.findByTermPeriod(2),hasItem(
                hasProperty("id",is(id))
        ));
    }

    @Test
    public void whenFindByModule_thenReturnStudentAttendsCourse(){
        assertThat(studentAttendsCourseRepository.findByModule(module),hasItem(
                hasProperty("termPeriod",is(2))
        ));
    }

    @Test
    public void whenFindByCurriculum_thenReturnStudentAttendsCourse(){
        assertThat(studentAttendsCourseRepository.findByCurriculum(curriculum),hasItem(
                hasProperty("termPeriod",is(2))
        ));
    }



}
