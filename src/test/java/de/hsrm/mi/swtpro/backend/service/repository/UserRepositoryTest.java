package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Administrator;
import de.hsrm.mi.swtpro.backend.model.Role;
import de.hsrm.mi.swtpro.backend.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    private long id;
    private Role role;

    @Before
    public void setUp(){
        User user = User.builder()
                .loginName("MaxMu")
                .firstName("Max")
                .lastName("Mustermann")
                .build();

        role = Administrator.builder()
                .user(user)
                .build();

        entityManager.persist(user);
        entityManager.persist(role);
        id = user.getId();
    }

    @Test
    public void whenFindAll_thenReturnUser(){
        assertThat(userRepository.findAll(),hasItem(
                hasProperty("lastName", is("Mustermann"))
        ));
    }

    @Test
    public void whenFindId_thenReturnUser(){
        assertTrue(userRepository.findById(id).isPresent());
        assertThat(userRepository.findById(id).get(),
                hasProperty("lastName", is("Mustermann")));
    }

    @Test
    public void whenFindLoginName_thenReturnUser(){
        assertTrue(userRepository.findByLoginName("MaxMu").isPresent());
        User user = userRepository.findByLoginName("MaxMu").get();
        assertEquals(user.getLoginName(), "MaxMu");
    }

    @Test
    public void whenFindByFirstName_thenReturnUserList(){
        assertThat(userRepository.findByFirstName("Max"),
                hasItem(hasProperty("firstName", is("Max"))));
    }

    @Test
    public void whenFindByLasName_thenReturnUserList(){
        assertThat(userRepository.findByLastName("Mustermann"),
                hasItem(hasProperty("lastName", is("Mustermann"))));
    }

    @Test
    public void whenFindByRole_thenReturnUserList(){
        assertThat(userRepository.findByRoles(role),
                hasItem(hasProperty("lastName", is("Mustermann"))));
    }

}
