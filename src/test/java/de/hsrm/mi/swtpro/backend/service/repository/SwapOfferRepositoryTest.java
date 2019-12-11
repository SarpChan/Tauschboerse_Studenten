package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentPrioritizesGroup;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.sql.Timestamp;

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
                .date(new Timestamp(564738))
                .id(id)
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
               hasProperty("date",is(564738))
        ));
    }

}
