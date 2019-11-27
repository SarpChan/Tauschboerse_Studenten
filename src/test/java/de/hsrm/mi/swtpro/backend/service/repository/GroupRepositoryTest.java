package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    GroupRepository groupRepository;

    @Before
    public void setUp(){
        Course course = Course.builder()
                .title("A")
                .build();

        CourseComponent courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .build();

        User user = User.builder()
                .firstName("Lukas")
                .lastName("wede")
                .loginName("w001")
                .password("password")
                .build();

        Room room = Room.builder()
                .number(17)
                .build();

        Term term = Term.builder().build();

        Group group = Group.builder()
                .id(17)
                .lecturer(user)
                .dayOfWeek(DayOfWeek.MONDAY)
                .startTime(LocalTime.of(14,0))
                .endTime(LocalTime.of(15,0))
                .slots(17)
                .room(room)
                .term(term)
                .courseComponent(courseComponent)
                .group('A')
                .build();

        entityManager.persist(course);
        entityManager.persist(courseComponent);
        entityManager.persist(user);
        entityManager.persist(room);
        entityManager.persist(group);
        entityManager.persist(group);
    }

    @Test
    public void whenFindAll_thenReturnGroupList(){
        assertThat(groupRepository.findAll(),hasItem(
                hasProperty("id",is(17))
        ));
    }

    @Test
    public void whenFindById_thenReturnGroup(){
        assertThat(groupRepository.findById(17),
                hasProperty("slots",is(17)
        ));
    }

    //TODO: Lecture Test

    @Test
    public void whenFindBySlots_thenReturnGroupList(){
        assertThat(groupRepository.findBySlots(17),
                hasProperty("id",is(17)
                ));
    }

    @Test
    public void whenFindByDayOfWeek_thenReturnGroupList(){
        assertThat(groupRepository.findByDayOfWeek(DayOfWeek.MONDAY),
                hasItem(hasProperty("id",is(17))));
    }

}
