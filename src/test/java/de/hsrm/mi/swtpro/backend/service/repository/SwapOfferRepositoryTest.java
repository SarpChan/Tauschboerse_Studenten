package de.hsrm.mi.swtpro.backend.service.repository;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import javax.persistence.EntityManager;

import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SwapOfferRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    SwapOfferRepository  swapOfferRepository;

    private Student student;
    private Group fromGroup;
    private Group toGroup;
    private long id;

    @Before
    public void setUp(){
        student = Student.builder().build();
        toGroup = Group.builder().build();
        fromGroup = Group.builder().build();

        SwapOffer swapOffer = SwapOffer.builder()
                .toGroup(toGroup)
                .fromGroup(fromGroup)
                .student(student)
                .timestamp(new Timestamp(564738))
                .build();

        entityManager.persist(student);
        entityManager.persist(fromGroup);
        entityManager.persist(toGroup);
        entityManager.persist(swapOffer);
        id = swapOffer.getId();
    }

    @Test
    public void whenFindAll_thenReturnSwapOfferList(){
        assertThat(swapOfferRepository.findAll(),hasItem(
               hasProperty("date",is(new Timestamp(564738)))
        ));
    }

    @Test
    public void whenFindById_thenReturnSwapOffer(){
        assertTrue(swapOfferRepository.findById(id).isPresent());
        assertThat(swapOfferRepository.findById(id).get(),
                hasProperty("date",is(new Timestamp(564738))));
    }

    @Test
    public void whenFindByToGroup_thenReturnSwapOfferList(){
        assertThat(swapOfferRepository.findByToGroup(toGroup),hasItem(
                hasProperty("date",is(new Timestamp(564738)))
        ));
    }

    @Test
    public void whenFindByFromGroup_thenReturnSwapOfferList(){
        assertThat(swapOfferRepository.findByFromGroup(fromGroup),hasItem(
                hasProperty("date",is(new Timestamp(564738)))
        ));
    }

    @Test
    public void whenFindByDate_thenReturnSwapOfferList(){
        assertThat(swapOfferRepository.findByTimestamp(new Timestamp(564738)),
                hasItem(hasProperty("date", is(new Timestamp(564738)))));
    }
}
