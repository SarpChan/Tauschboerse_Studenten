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

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentPassedExamRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudentPassedExamRepository studentPassedExamRepository;

    private Student student;
    private CourseComponent courseComponent;
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

        Course course = Course.builder()
                .owner(user)
                .title("A")
                .build();

        courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .exam("Prüfung")
                .build();

        StudentPassedExam studentPassedExam = StudentPassedExam.builder()
                .student(student)
                .courseComponent(courseComponent)
                .grade(1.7f)
                .build();

        entityManager.persist(term);
        entityManager.persist(user);
        entityManager.persist(course);
        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(courseComponent);
        entityManager.persist(student);
        entityManager.persist(studentPassedExam);
        id = studentPassedExam.getId();
    }

    @Test
    public void whenFindAll_thenReturnStudentAttendsCourseList() {
        assertThat(studentPassedExamRepository.findAll(), hasItem(
                hasProperty("grade", is(1.7f))
        ));
    }

    @Test
    public void whenFindById_thenReturnStudentAttendsCourse() {
        assertTrue(studentPassedExamRepository.findById(id).isPresent());
        assertThat(studentPassedExamRepository.findById(id).get(),
                hasProperty("grade", is(1.7f))
        );
    }

    @Test
    public void whenFindByStudent_thenReturnStudentAttendsCourseList() {
        assertThat(studentPassedExamRepository.findByStudent(student), hasItem(
                hasProperty("grade", is(1.7f))
        ));
    }

    @Test
    public void whenFindByCourseComponent_thenReturnStudentAttendsCourseList() {
        assertThat(studentPassedExamRepository.findByCourseComponent(courseComponent), hasItem(
                hasProperty("grade", is(1.7f))
        ));
    }

    @Test
    public void whenFindByGrade_thenReturnStudentAttendsCourseList() {
        assertThat(studentPassedExamRepository.findByGrade(1.7f), hasItem(
                hasProperty("id", is(id))
        ));
    }


}
