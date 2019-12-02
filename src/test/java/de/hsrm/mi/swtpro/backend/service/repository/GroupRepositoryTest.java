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

    private Room room;
    private long id;

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

        room = Room.builder()
                .number(17)
                .build();

        Term term = Term.builder().build();

        Group group = Group.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .startTime(LocalTime.of(14,0))
                .endTime(LocalTime.of(15,0))
                .slots(17)
                .room(room)
                .term(term)
                .courseComponent(courseComponent)
                .groupChar('A')
                .build();

        entityManager.persist(term);
        entityManager.persist(course);
        entityManager.persist(courseComponent);
        entityManager.persist(user);
        entityManager.persist(room);
        entityManager.persist(group);
        id = group.getId();
    }

    @Test
    public void whenFindAll_thenReturnGroupList(){
        assertThat(groupRepository.findAll(),hasItem(
                hasProperty("slots",is(17))
        ));
    }

    @Test
    public void whenFindById_thenReturnGroup(){
        assertThat(groupRepository.findById(id),
                hasProperty("slots",is(17)
        ));
    }

    @Test
    public void whenFindBySlots_thenReturnGroupList(){
        assertThat(groupRepository.findBySlots(17),hasItem(
                hasProperty("id",is(id)
                )));
    }

    @Test
    public void whenFindByDayOfWeek_thenReturnGroupList(){
        assertThat(groupRepository.findByDayOfWeek(DayOfWeek.MONDAY),
                hasItem(hasProperty("slots",is(17))));
    }

    @Test
    public void whenFindByStartTime_thenReturnGroupList(){
        assertThat(groupRepository.findByStartTime(LocalTime.of(14,0)),hasItem(
                hasProperty("slots",is(17))
        ));
    }

    @Test
    public void whenFindByEndTime_thenReturnGroupList(){
        assertThat(groupRepository.findByEndTime(LocalTime.of(15,0)),hasItem(
                hasProperty("slots",is(17))
        ));
    }

    @Test
    public void whenFindByRoom_thenReturnGroupList(){
        assertThat(groupRepository.findByRoom(room),
                hasItem(hasProperty("slots",is(17))));
    }

}
