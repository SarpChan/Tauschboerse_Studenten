package de.hsrm.mi.swtpro.backend.service.repository;


import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentAttendsCourse;
import de.hsrm.mi.swtpro.backend.model.Term;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

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
        term = Term.builder().build();
        student = Student.builder().build();
        course = Course.builder().build();

        StudentAttendsCourse studentAttendsCourse = StudentAttendsCourse.builder()
                .term(term)
                .student(student)
                .course(course)
                .build();

        entityManager.persist(term);
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
