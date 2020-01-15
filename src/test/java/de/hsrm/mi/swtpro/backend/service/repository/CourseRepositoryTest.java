package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    CourseRepository courseRepository;

    private User user;
    private Module module;
    private Term term;
    private CourseComponent courseComponent;
    private StudentAttendsCourse studentAttendsCourse;


    private long id;
    @Before
    public void setUp(){
        user = User.builder()
                .firstName("niemand")
                .lastName("Nimandson")
                .loginName("nie")
                .password("password")
                .build();

        term = Term.builder()
                .period(2)
                .startDate(Date.valueOf(LocalDate.of(2017,10,11)))
                .endDate(Date.valueOf(LocalDate.of(2016,10,11)))
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
                .enrollmentNumber(691742)
                .mail("ab@a.com")
                .examRegulation(examReg)
                .enrolmentTerm(term)
                .build();

        module = Module.builder()
                .title("Nettes Module ")
                .build();


        Course course = Course.builder()
                .title("Netter Kurs")
                .owner(user)
                .module(module)
                .term(term)
                .build();

        studentAttendsCourse = StudentAttendsCourse.builder()
                .course(course)
                .term(term)
                .student(student)
                .build();

        courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .exam("Test")
                .build();


        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(user);
        entityManager.persist(student);
        entityManager.persist(term);
        entityManager.persist(course);
        entityManager.persist(module);
        entityManager.persist(courseComponent);
        entityManager.persist(studentAttendsCourse);
        id = course.getId();
    }

    @Test
    public void whenFindAll_thenReturnCourseList(){
        assertThat(courseRepository.findAll(),hasItem(
                hasProperty("title",is("Netter Kurs"))
        ));
    }

    @Test
    public void whenFindById_thenReturnCourse(){
        assertTrue(courseRepository.findById(id).isPresent());
        assertEquals(courseRepository.findById(id).get().getTitle(),"Netter Kurs");
    }

    @Test
    public void whenFindByTitle_thenReturnCourseList(){
        assertThat(courseRepository.findByTitle("Netter Kurs"),
                hasItem(hasProperty("owner",
                        is(hasProperty("loginName",is("nie")))
        )));
    }

    @Test
    public void whenFindByOwner_thenReturnCourseList(){
        assertThat(courseRepository.findByOwner(user),hasItem(
                hasProperty("title",is("Netter Kurs"))
        ));
    }

    @Test
    public void whenFindByModule_thenReturnCourseList(){
        assertThat(courseRepository.findByModules(module),hasItem(
                hasProperty("title", is("Netter Kurs"))
        ));
    }

    @Test
    public void whenFindByTerm_thenReturnCourseList(){
        assertThat(courseRepository.findByTerms(term),hasItem(
                hasProperty("title",is("Netter Kurs"))
        ));
    }

    @Test
    public void whenFindByCourseComponent_thenReturnCourseList(){
        assertThat(courseRepository.findByCourseComponents(courseComponent),hasItem(
                hasProperty("title",is("Netter Kurs"))
        ));
    }

    @Test
    public void whenFindByStudentAttendsCourse_thenReturnCourseList(){
        assertThat(courseRepository.findByStudentAttendsCourses(studentAttendsCourse),hasItem(
                hasProperty("title",is("Netter Kurs"))
        ));
    }

}
