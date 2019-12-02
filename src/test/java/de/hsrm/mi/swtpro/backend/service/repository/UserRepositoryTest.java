package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static  org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.*;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp(){
        User user = User.builder()
                .loginName("MaxMu")
                .firstName("Max")
                .lastName("Mustermann")
                .build();

        entityManager.persist(user);

    }

    @Test
    public void whenFindUser_thenReturnUser(){
        User user = userRepository.findByLoginName("MaxMu");
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

}
