package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    GroupRepository groupRepository;

    @Before
    public void setUp(){
        Course course = new Course.Builder("A").build();
        CourseComponent courseComponent = new CourseComponent.Builder(course, CourseType.LECTURE).build();
        User user = new User("Lukas","wede","w001","password",true);
        Room room = new Room(17);
        Term term = new Term.Builder().build();

        Group group = new Group.Builder()
                .hasLecturer(user)
                .onDayOfWeek(DayOfWeek.MONDAY)
                .withStartTime(LocalTime.of(14,0))
                .withEndTime(LocalTime.of(15,0))
                .hasSlots(17)
                .inRoom(room)
                .inTerm(term)
                .build(courseComponent,'A');

        entityManager.persist(course);
        entityManager.persist(courseComponent);
        entityManager.persist(user);
        entityManager.persist(room);
        entityManager.persist(group);
        entityManager.persist(group);
    }
}
