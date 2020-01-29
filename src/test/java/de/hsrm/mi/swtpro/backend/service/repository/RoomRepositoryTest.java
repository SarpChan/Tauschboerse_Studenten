package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    RoomRepository roomRepository;

    private Building building;
    private Group group;
    private long id;

    @Before
    public void setUp(){

        University university = University.builder()
                .name("Hochschule RheinMain")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .build();

        Campus campus = Campus.builder()
                .name("Unter den Eichen")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .university(university)
                .build();

        building = Building.builder()
                .name("Haus D")
                .campus(campus)
                .build();

        Room room = Room.builder()
                .seats(170)
                .number(28)
                .building(building)
                .build();

        Term term = Term.builder()
                .startDate(new Date(System.currentTimeMillis()))
                .endDate(new Date(System.currentTimeMillis()))
                .period(1)
                .build();

        User user = User.builder()
                .firstName("Lukas")
                .lastName("wede")
                .loginName("w001")
                .password("password")
                .build();

        StudyProgram studyProg = StudyProgram.builder()
                .title("test")
                .degree("E")
                .build();

        ExamRegulation examReg = ExamRegulation.builder()
                .date(new Date(System.currentTimeMillis()))
                .studyProgram(studyProg)
                .build();

        Student student = Student.builder()
                .user(user)
                .mail("3@e.de")
                .enrollmentNumber(10)
                .examRegulation(examReg)
                .enrolmentTerm(term)
                .build();

        Lecturer lecturer = Lecturer.builder().build();

        Course course = Course.builder()
                .owner(user)
                .title("A")
                .build();

        CourseComponent courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .exam("Prüfung")
                .build();

        group = Group.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .startTime(LocalTime.of(14, 0))
                .endTime(LocalTime.of(15, 0))
                .slots(17)
                .room(room)
                .term(term)
                .lecturer(lecturer)
                .student(student)
                .courseComponent(courseComponent)
                .groupChar('A')
                .build();

        entityManager.persist(user);
        entityManager.persist(course);
        entityManager.persist(courseComponent);
        entityManager.persist(term);
        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(student);
        entityManager.persist(lecturer);
        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
        entityManager.persist(room);
        entityManager.persist(group);
        id = room.getId();
    }

    @Test
    public void whenFindAll_thanReturnRoomList(){
        List<Room> rooms = roomRepository.findAll();
        assertEquals(rooms.size(), 1);
    }

    @Test
    public void whenFindById_thenReturnRoomL(){
        assertTrue(roomRepository.findById(id).isPresent());
        assertThat(roomRepository.findById(id).get(),
                hasProperty("number", is(28)));
    }

    @Test
    public void whenFindByNumber_thanReturnRoomList(){
        assertThat(roomRepository.findByNumber(28), hasItem(hasProperty("number", is(28))));
    }

    @Test
    public void whenFindBySeats_thanReturnRoomList(){
        assertThat(roomRepository.findBySeats(170), hasItem(hasProperty("seats", is(170))));
    }

    @Test
    public void whenFindByBuilding_thanReturnRoomList(){
        assertThat(roomRepository.findByBuilding(building), hasItem(hasProperty("number", is(28))));
    }

    @Test
    public void whenFindByGroup_thenReturnRoomList(){
        assertThat(roomRepository.findByGroups(group),hasItem(
                hasProperty("number", is(28))
        ));
    }

}
