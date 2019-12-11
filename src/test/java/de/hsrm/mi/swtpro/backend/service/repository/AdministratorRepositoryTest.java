package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Administrator;
import de.hsrm.mi.swtpro.backend.model.User;
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
public class AdministratorRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    AdministratorRepository administratorRepository;

    private User user;
    private long id;

    @Before
    public void setUp(){

        user = User.builder()
                .firstName("Max")
                .lastName("Mustermann")
                .loginName("backfisch")
                .build();

        Administrator administrator = Administrator.builder()
                .priviledge(2)
                .user(user)
                .build();

        entityManager.persist(administrator);
        entityManager.persist(user);
        id = administrator.getId();
    }

    @Test
    public void whenFindAll_thenReturnAdministratorList(){
        assertThat(administratorRepository.findAll(),hasItem(
                hasProperty("priviledge",is(2))
        ));
    }

    @Test
    public void whenFindById_thenReturnAdministratorList(){
        assertTrue(administratorRepository.findById(id).isPresent());
        assertThat(administratorRepository.findById(id).get(), hasProperty("priviledge",is(2)));
    }

    @Test
    public void whenFindByPriviledge_thenReturnAdministratorList(){
        assertThat(administratorRepository.findByPriviledge(2), hasItem(
                hasProperty("id",is(id))
        ));
    }

    @Test
    public void whenFindByUser_thenReturnAdministratorList(){
        assertThat(administratorRepository.findByUser(user),hasItem(
                hasProperty("priviledge",is(2))
        ));
    }


}
