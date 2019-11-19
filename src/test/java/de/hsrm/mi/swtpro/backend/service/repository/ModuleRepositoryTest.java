package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Module;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ModuleRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    ModuleRepository moduleRepository;

    @Before
    public void setUp(){

        Module module = new Module.Builder("Analysis").inPeriod(1).build();
        entityManager.persist(module);
    }

    @Test
    public void whenFindByTitle_thenReturnModuleList(){
        List<Module> modules = moduleRepository.findByTitle("Analysis");
        assertEquals(modules.get(0).getPeriod(),1);
    }

    @Test
    public void whenFindAll_thenReturnModuleList(){
        List<Module> modules = moduleRepository.findAll();
        assertEquals(modules.size(),1);
    }
}
