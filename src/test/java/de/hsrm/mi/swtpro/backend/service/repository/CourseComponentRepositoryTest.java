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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseComponentRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    CourseComponentRepository courseComponentRepository;

    private Group group;
    private Term term;
    private Room room;
    private User user;
    private Lecturer lecturer;
    private long id;
    private Course course;
    private StudentPassedExam passedExam;

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

        room = Room.builder()
                .number(17)
                .building(building)
                .build();


        user = User.builder()
                .firstName("Max")
                .lastName("Mustermann")
                .loginName("backfisch")
                .password("password")
                .build();

        lecturer = Lecturer.builder()
                .priviledge(1)
                .user(user)
                .build();

        course = Course.builder()
                .owner(user)
                .title("Analysis")
                .build();

        term = Term.builder()
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
                .enrollmentNumber(691742)
                .mail("ab@a.com")
                .examRegulation(examReg)
                .enrolmentTerm(term)
                .build();

        CourseComponent courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .exam("Test")
                .creditPoints(10)
                .build();

        passedExam = StudentPassedExam.builder()
                .grade(17f)
                .courseComponent(courseComponent)
                .student(student)
                .build();

        HashSet<StudentPassedExam> studentPassedExams = new HashSet<>();
        studentPassedExams.add(passedExam);
        courseComponent.setStudentsPassedExam(studentPassedExams);

        group = Group.builder()
                .groupChar('D')
                .lecturer(lecturer)
                .dayOfWeek(DayOfWeek.MONDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now())
                .room(room)
                .term(term)
                .courseComponent(courseComponent)
                .build();


        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(student);
        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
        entityManager.persist(room);
        entityManager.persist(term);
        entityManager.persist(user);
        entityManager.persist(lecturer);
        entityManager.persist(course);
        entityManager.persist(courseComponent);
        entityManager.persist(passedExam);
        entityManager.persist(group);
        id = courseComponent.getId();
    }

    @Test
    public void whenFindAll_thenReturnCourseComponentList() {
        assertEquals(1, courseComponentRepository.findAll().size());
    }

    @Test
    public void whenFindById_theReturnCourseComponent() {
        assertTrue(courseComponentRepository.findById(id).isPresent());
        assertEquals("Test", courseComponentRepository.findById(id).get().getExam());
    }

    @Test
    public void whenFindByType_thenReturnCourseComponentList() {
        assertEquals(id, courseComponentRepository.findByType(CourseType.LECTURE).get(0).getId());
    }

    @Test
    public void whenFindByCreditPoints_thenReturnCourseComponentList() {
        assertThat(courseComponentRepository.findByCreditPoints(10),
                hasItem(hasProperty("exam", is("Test"))));
    }

    @Test
    public void whenFindByGroup_thenReturnCourseComponentList() {
        assertThat(courseComponentRepository.findByGroups(group),
                hasItem(hasProperty("creditPoints", is(10))));
    }

    @Test
    public void whenFindByExam_thenReturnCourseComponentList() {
        assertThat(courseComponentRepository.findByExam("Test"),
                hasItem(hasProperty("creditPoints", is(10))));
    }

    @Test
    public void whenFindByCourse_thenReturnCourseComponentList() {
        assertThat(courseComponentRepository.findByCourse(course), hasItem(
                hasProperty("creditPoints", is(10))
        ));
    }

    @Test
    public void whenFindByStudentPassedExam_thenReturnCourseComponentList() {
        assertThat(courseComponentRepository.findByStudentsPassedExam(passedExam), hasItem(
                hasProperty("creditPoints", is(10))
        ));
    }
}
