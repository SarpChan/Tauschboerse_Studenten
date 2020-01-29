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

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LectureRepositoryTest {

    private Group group;
    private User user;
    private long id;

    @Autowired
    EntityManager entityManager;
    @Autowired
    LectureRepository lectureRepository;

    @Before
    public void setUp() {
        University university = University.builder()
                .name("Hochschule RheinMain")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .build();

        Campus campus = Campus.builder()
                .name("Unter den Eichen")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .university(university)
                .build();


        Building building = Building.builder()
                .name("D Gebäude")
                .campus(campus)
                .build();

        Room room = Room.builder()
                .number(17)
                .building(building)
                .build();

        user = User.builder()
                .loginName("123489")
                .firstName("Marc-Uwe")
                .lastName("Kling")
                .password("Känguru")
                .build();

        Course course = Course.builder()
                .owner(user)
                .title("A")
                .build();

        CourseComponent courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .exam("Prüfung")
                .build();

        Lecturer lecturer = Lecturer.builder()
                .priviledge(10)
                .user(user)
                .build();

        Term term = Term.builder()
                .startDate(new Date(System.currentTimeMillis()))
                .endDate(new Date(System.currentTimeMillis()))
                .period(1)
                .build();

        StudyProgram studyProg = StudyProgram.builder()
                .title("test")
                .degree("E")
                .build();

        ExamRegulation examReg = ExamRegulation.builder()
                .date(new Date(System.currentTimeMillis()))
                .studyProgram(studyProg)
                .build();

        Student student = Student.builder().user(user)
                .mail("3@e.de")
                .enrollmentNumber(10)
                .examRegulation(examReg)
                .enrolmentTerm(term)
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
        entityManager.persist(user);
        entityManager.persist(lecturer);
        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
        entityManager.persist(room);
        entityManager.persist(term);
        entityManager.persist(student);
        entityManager.persist(group);
        id = lecturer.getId();
    }

    @Test
    public void whenFindById_thenReturnLecture() {
        assertTrue(lectureRepository.findById(id).isPresent());
        assertThat(lectureRepository.findById(id).get(),
                hasProperty("priviledge", is(10)));
    }

    @Test
    public void whenFindAll_thenReturnLectureList() {
        assertThat(lectureRepository.findAll(), hasItem(
                hasProperty("priviledge", is(10))
        ));
    }

    @Test
    public void whenFindByPriviledge_thenReturnLectureList() {
        assertThat(lectureRepository.findByPriviledge(10), hasItem(
                hasProperty("id", is(id))
        ));
    }

    @Test
    public void whenFindByGroups_thenReturnLectureList() {
        assertThat(lectureRepository.findByGroups(group), hasItem(
                hasProperty("priviledge", is(10))
        ));
    }

    @Test
    public void whenFindByUser_thenReturnLectureList() {
        assertThat(lectureRepository.findByUser(user), hasItem(
                hasProperty("priviledge", is(10))
        ));
    }

}
