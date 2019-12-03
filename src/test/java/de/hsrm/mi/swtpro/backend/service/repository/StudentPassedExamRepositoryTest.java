package de.hsrm.mi.swtpro.backend.service.repository;


import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentPassedExam;
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
public class StudentPassedExamRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudentPassedExamRepository studentPassedExamRepository;

    private Student student;
    private CourseComponent courseComponent;
    private long id;

    @Before
    public void setUp(){
        student = Student.builder().build();
        courseComponent = CourseComponent.builder().build();

        StudentPassedExam studentPassedExam = StudentPassedExam.builder()
                .student(student)
                .courseComponent(courseComponent)
                .grade(1.7f)
                .build();

        entityManager.persist(courseComponent);
        entityManager.persist(student);
        entityManager.persist(studentPassedExam);
        id = studentPassedExam.getId();
    }

    @Test
    public void whenFindAll_thenReturnStudentAttendsCourseList(){
        assertThat(studentPassedExamRepository.findAll(),hasItem(
               hasProperty("grade",is(1.7f))
        ));
    }

    @Test
    public void whenFindById_thenReturnStudentAttendsCourse(){
        assertTrue(studentPassedExamRepository.findById(id).isPresent());
        assertThat(studentPassedExamRepository.findById(id).get(),
                hasProperty("grade",is(1.7f))
        );
    }

    @Test
    public void whenFindByStudent_thenReturnStudentAttendsCourseList(){
        assertThat(studentPassedExamRepository.findByStudent(student),hasItem(
                hasProperty("grade",is(1.7f))
        ));
    }

    @Test
    public void whenFindByCourseComponent_thenReturnStudentAttendsCourseList(){
        assertThat(studentPassedExamRepository.findByCourseComponent(courseComponent),hasItem(
                hasProperty("grade",is(1.7f))
        ));
    }

    @Test
    public void whenFindByGrade_thenReturnStudentAttendsCourseList(){
        assertThat(studentPassedExamRepository.findByGrade(1.7f),hasItem(
                hasProperty("id",is(id))
        ));
    }


}
