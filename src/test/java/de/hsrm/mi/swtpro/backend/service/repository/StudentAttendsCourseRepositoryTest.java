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
public class StudentAttendsCourseRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudentAttendsCourseRepository studentAttendsCourseRepository;

    private Term term;
    private Student student;
    private Course course;
    private long id;

    @Before
    public void setUp(){
        User user = User.builder()
                .firstName("Lukas")
                .lastName("wede")
                .loginName("w001")
                .password("password")
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

        student = Student.builder().user(user)
                .mail("3@e.de")
                .enrollmentNumber(10)
                .examRegulation(examReg)
                .enrolmentTerm(term)
                .build();

        course = Course.builder()
                .owner(user)
                .title("A")
                .build();

        StudentAttendsCourse studentAttendsCourse = StudentAttendsCourse.builder()
                .term(term)
                .student(student)
                .course(course)
                .build();

        entityManager.persist(term);
        entityManager.persist(user);
        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(student);
        entityManager.persist(course);
        entityManager.persist(studentAttendsCourse);
        id = studentAttendsCourse.getId();
    }

    @Test
    public void whenFindAll_thenReturnStudentAttendsCourseList(){
        assertThat(studentAttendsCourseRepository.findAll(),hasItem(
               hasProperty("id",is(id))
        ));
    }

    @Test
    public void whenFindById_thenReturnStudentAttendsCourse(){
        assertTrue(studentAttendsCourseRepository.findById(id).isPresent());
        assertThat(studentAttendsCourseRepository.findById(id).get(),
                hasProperty("id",is(id))
        );
    }

    @Test
    public void whenFindByTerm_thenReturnStudentAttendsCourseList(){
        assertThat(studentAttendsCourseRepository.findByTerm(term),hasItem(
                hasProperty("id",is(id))
        ));
    }

    @Test
    public void whenFindByCourse_thenReturnStudentAttendsCourseList(){
        assertThat(studentAttendsCourseRepository.findByCourse(course),hasItem(
                hasProperty("id",is(id))
        ));
    }

    @Test
    public void whenFindByStudent_thenReturnStudentAttendsCourseList(){
        assertThat(studentAttendsCourseRepository.findByStudent(student),hasItem(
                hasProperty("id",is(id))
        ));
    }

}
