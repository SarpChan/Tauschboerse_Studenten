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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudentRepository studentRepository;

    private Term term;
    private Group group;
    private StudentPrioritizesGroup studentPrioritizesGroup;
    private StudentPassedExam passedExam;
    private StudentAttendsCourse studentAttendsCourse;
    private ExamRegulation examRegulation;
    private User user;

    private long id;

    @Before
    public void setUp() {
        term = Term.builder()
                .startDate(new Date(System.currentTimeMillis()))
                .endDate(new Date(System.currentTimeMillis()))
                .period(1)
                .build();

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

        studentAttendsCourse = StudentAttendsCourse.builder().build();
        StudyProgram studyProg = StudyProgram.builder()
                .title("test")
                .degree("E")
                .build();

        examRegulation = ExamRegulation.builder()
                .date(new Date(System.currentTimeMillis()))
                .studyProgram(studyProg)
                .build();

        user = User.builder()
                .firstName("Lukas")
                .lastName("wede")
                .loginName("w001")
                .password("password")
                .build();

        Lecturer lecturer = Lecturer.builder()
                .build();

        Student student = Student.builder()
                .mail("1234@gut.de")
                .enrolmentTerm(term)
                .prioritizeGroup(studentPrioritizesGroup)
                .passedExam(passedExam)
                .examRegulation(examRegulation)
                .attendCourse(studentAttendsCourse)
                .user(user)
                .enrollmentNumber(1742).build();

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

        studentPrioritizesGroup = StudentPrioritizesGroup.builder()
                .student(student)
                .group(group)
                .build();

        passedExam = StudentPassedExam.builder()
                .student(student)
                .courseComponent(courseComponent)
                .build();

        studentAttendsCourse = StudentAttendsCourse.builder()
                .student(student)
                .course(course)
                .term(term)
                .build();

        entityManager.persist(user);
        entityManager.persist(course);
        entityManager.persist(courseComponent);
        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
        entityManager.persist(room);
        entityManager.persist(lecturer);
        entityManager.persist(student);
        entityManager.persist(term);
        entityManager.persist(group);
        entityManager.persist(studentPrioritizesGroup);
        entityManager.persist(passedExam);
        entityManager.persist(studentAttendsCourse);
        entityManager.persist(studyProg);
        entityManager.persist(examRegulation);

        id = student.getId();
    }

    @Test
    public void whenFindById_thenReturnStudent() {
        assertTrue(studentRepository.findById(id).isPresent());
        assertThat(studentRepository.findById(id).get(),
                hasProperty("mail", is("1234@gut.de")));
    }

    @Test
    public void whenFindAll_thenReturnStudentList() {
        assertThat(studentRepository.findAll(), hasItem(
                hasProperty("mail", is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByPrioritizeGroup_thenReturnStudentList() {
        assertThat(studentRepository.findByPrioritizeGroups(studentPrioritizesGroup), hasItem(
                hasProperty("mail", is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByPassedExams_thenReturnStudentList() {
        assertThat(studentRepository.findByPassedExams(passedExam), hasItem(
                hasProperty("mail", is("1234@gut.de"))
        ));
    }


    @Test
    public void whenFindByGroups_thenReturnStudentList() {
        assertThat(studentRepository.findByGroups(group), hasItem(
                hasProperty("mail", is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByAttendsCourse_thenReturnStudentList() {
        assertThat(studentRepository.findByAttendCourses(studentAttendsCourse), hasItem(
                hasProperty("mail", is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByExamRegulation_thenReturnStudentList() {
        assertThat(studentRepository.findByExamRegulation(examRegulation), hasItem(
                hasProperty("mail", is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByUser_thenReturnStudentList() {
        assertTrue(studentRepository.findByUser(user).isPresent());
        assertThat(studentRepository.findByUser(user).get(),
                hasProperty("mail", is("1234@gut.de"))
        );
    }

    @Test
    public void whenFindByEnrolmentTerm_thenReturnStudentList() {
        assertThat(studentRepository.findByEnrolmentTerm(term), hasItem(
                hasProperty("mail", is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByMail_thenReturnStudentList() {
        assertThat(studentRepository.findByMail("1234@gut.de"), hasItem(
                hasProperty("id", is(id))
        ));
    }

    @Test
    public void whenFindByEnrollmentNumber_thanReturnStudent() {
        assertTrue(studentRepository.findByEnrollmentNumber(1742).isPresent());
        assertThat(studentRepository.findByEnrollmentNumber(1742).get(), hasProperty("enrollmentNumber", is(1742)));
    }

}
