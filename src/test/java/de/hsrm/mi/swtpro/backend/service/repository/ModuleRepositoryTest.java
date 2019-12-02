package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.Module;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ModuleRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    ModuleRepository moduleRepository;

    private Course course;

    @Before
    public void setUp(){

        Module module = Module.builder()
                .period(1)
                .title("Analysis")
                .creditPoints(55)
                .build();

        course = Course.builder()
                .title("Kurs")
                .module(module)
                .build();

        entityManager.persist(course);
        entityManager.persist(module);
    }

    @Test
    public void whenFindByTitle_thenReturnModuleList(){
        List<Module> modules = moduleRepository.findByTitle("Analysis");
        assertEquals(modules.size(),1);
    }

    @Test
    public void whenFindAll_thenReturnModuleList(){
        List<Module> modules = moduleRepository.findAll();
        assertEquals(modules.size(),1);
    }

    @Test
    public void whenFindByPeriod(){
        assertThat(moduleRepository.findByPeriod(1), hasItem(hasProperty("period", is(1))));
    }

    @Test
    public void whenFindByCreditPoints_thenReturnModuleList(){
        assertThat(moduleRepository.findByCreditPoints(55),
                hasItem(hasProperty("creditPoints", is(55))));
    }


    @Test
    public void findByCourses_theReturnModuleList(){

       assertThat(moduleRepository.findByCourses(course),
               hasItem(hasProperty("title", is("Analysis"))));

    }

}
