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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentPriorizesGroupRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudentPrioritizesGroupRepository studentPrioritizesGroupRepository;

    private Student student;
    private Group group;
    private long id;

    @Before
    public void setUp() {
        User user = User.builder()
                .firstName("Lukas")
                .lastName("wede")
                .loginName("w001")
                .password("password")
                .build();

        Lecturer lecturer = Lecturer.builder().build();

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

        student = Student.builder().user(user)
                .mail("3@e.de")
                .enrollmentNumber(10)
                .examRegulation(examReg)
                .enrolmentTerm(term)
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

        StudentPrioritizesGroup studentPrioritizesGroup = StudentPrioritizesGroup.builder()
                .group(group)
                .student(student)
                .priority(2)
                .build();

        entityManager.persist(user);
        entityManager.persist(lecturer);
        entityManager.persist(term);
        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
        entityManager.persist(room);
        entityManager.persist(course);
        entityManager.persist(courseComponent);
        entityManager.persist(student);
        entityManager.persist(group);
        entityManager.persist(studentPrioritizesGroup);
        id = studentPrioritizesGroup.getId();
    }

    @Test
    public void whenFindAll_thenReturnStudentAttendsCourseList() {
        assertThat(studentPrioritizesGroupRepository.findAll(), hasItem(
                hasProperty("priority", is(2))
        ));
    }

    @Test
    public void whenFindById_thenReturnStudentAttendsCourse() {
        assertTrue(studentPrioritizesGroupRepository.findById(id).isPresent());
        assertThat(studentPrioritizesGroupRepository.findById(id).get(),
                hasProperty("priority", is(2))
        );
    }

    @Test
    public void whenFindByPriority_thenReturnStudentAttendsCourseList() {
        assertThat(studentPrioritizesGroupRepository.findByPriority(2), hasItem(
                hasProperty("id", is(id))
        ));
    }

    @Test
    public void whenFindByStudent_thenReturnStudentAttendsCourseList() {
        assertThat(studentPrioritizesGroupRepository.findByStudent(student), hasItem(
                hasProperty("priority", is(2))
        ));
    }

    @Test
    public void whenFindByGroup_thenReturnStudentAttendsCourseList() {
        assertThat(studentPrioritizesGroupRepository.findByGroup(group), hasItem(
                hasProperty("priority", is(2))
        ));
    }

}
