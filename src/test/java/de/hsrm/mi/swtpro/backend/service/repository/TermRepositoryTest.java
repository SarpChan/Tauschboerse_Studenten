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
import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TermRepositoryTest {



    @Autowired
    EntityManager entityManager;
    @Autowired
    TermRepository termRepository;

    private Course course;
    private Student student;
    private Group group;
    private StudentAttendsCourse studentAttendsCourse;
    private long id;

    @Before
    public void setUp() {
        User user = User.builder()
                .firstName("Lukas")
                .lastName("wede")
                .loginName("w001")
                .password("password")
                .build();

        Term term = Term.builder()
                .period(2)
                .startDate(Date.valueOf(LocalDate.MIN))
                .endDate(Date.valueOf(LocalDate.MAX))
                .build();

        course = Course.builder()
                .title("Mathe")
                .owner(user)
                .term(term)
                .build();

        StudyProgram studyProg = StudyProgram.builder()
                .title("test")
                .degree("E")
                .build();

        ExamRegulation examReg = ExamRegulation.builder()
                .date(new Date(System.currentTimeMillis()))
                .studyProgram(studyProg)
                .build();

        student = Student.builder()
                .enrolmentNumber(152093)
                .enrolmentTerm(term)
                .examRegulation(examReg)
                .mail("a@b.c")
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

        Lecturer lecturer = Lecturer.builder().build();

        Building building = Building.builder()
                .name("D Gebäude")
                .campus(campus)
                .build();

        Room room = Room.builder()
                .number(17)
                .building(building)
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

        studentAttendsCourse = StudentAttendsCourse.builder()
                .term(term)
                .student(student)
                .course(course)
                .build();


        entityManager.persist(user);
        entityManager.persist(course);
        entityManager.persist(term);
        entityManager.persist(courseComponent);
        entityManager.persist(lecturer);
        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
        entityManager.persist(room);
        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(student);
        entityManager.persist(group);
        entityManager.persist(studentAttendsCourse);
        id = term.getId();
    }

    @Test
    public void whenFindById_thenReturnTerm() {
        assertTrue(termRepository.findById(id).isPresent());
        assertThat(termRepository.findById(id).get(),
                hasProperty("period", is(2)));
    }

    @Test
    public void whenFindAll_thenReturnTermList() {
        assertThat(termRepository.findAll(), hasItem(
                hasProperty("period", is(2))
        ));
    }

    @Test
    public void whenFindByPeriod_thenReturnTermList() {
        assertThat(termRepository.findByPeriod(2), hasItem(hasProperty("period", is(2))));
    }

    @Test
    public void whenFindByCourses_thenReturnTermList() {
        assertThat(termRepository.findByCourses(course), hasItem(hasProperty("period", is(2))));
    }

    @Test
    public void whenFindByStartDate_thenReturnTermList() {
        assertThat(termRepository.findByStartDate(Date.valueOf(LocalDate.MIN)),
                hasItem(hasProperty("startDate", is(Date.valueOf(LocalDate.MIN)))));
    }

    @Test
    public void whenFindByEndDate_thenReturnTermList() {
        assertThat(termRepository.findByEndDate(Date.valueOf(LocalDate.MAX)),
                hasItem(hasProperty("endDate", is(Date.valueOf(LocalDate.MAX)))));
    }

    @Test
    public void whenFindByEnroledStudent_thenReturnTerm(){
            assertTrue(termRepository.findByEnroledStudents(student).isPresent());
            assertThat(termRepository.findByEnroledStudents(student).get(),
                    hasProperty("period", is(2)));
    }

    @Test
    public void whenFindByGroup_thenReturnTermList() {
        assertThat(termRepository.findByGroups(group), hasItem(
                hasProperty("period", is(2))
        ));
    }

    @Test
    public void whenFindByStudentAttendsCourses_thenReturnTermList() {
        assertThat(termRepository.findByStudentAttendsCourses(studentAttendsCourse), hasItem(
                hasProperty("period", is(2))
        ));
    }
}
