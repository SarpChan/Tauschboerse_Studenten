package de.hsrm.mi.swtpro.backend.service.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    GroupRepository groupRepository;

    @Before
    public void setUp(){
    }
}
