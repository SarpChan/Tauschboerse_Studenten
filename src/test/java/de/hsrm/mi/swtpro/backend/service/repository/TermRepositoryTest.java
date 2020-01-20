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
import java.time.LocalDate;

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
    public void setUp(){

        Term term = Term.builder()
                .period(2)
                .startDate(Date.valueOf(LocalDate.MIN))
                .endDate(Date.valueOf(LocalDate.MAX))
                .build();

        course = Course.builder()
                .title("Mathe")
                .term(term)
                .build();

        student = Student.builder()
                .enrolmentNumber(152093)
                .enrolmentTerm(term)
                .build();

        group = Group.builder()
                .term(term)
                .build();

        studentAttendsCourse = StudentAttendsCourse.builder()
                .term(term)
                .build();


        entityManager.persist(course);
        entityManager.persist(term);
        entityManager.persist(student);
        entityManager.persist(group);
        entityManager.persist(studentAttendsCourse);
        id = term.getId();
    }

    @Test
    public void whenFindById_thenReturnTerm(){
        assertTrue(termRepository.findById(id).isPresent());
        assertThat(termRepository.findById(id).get(),
                hasProperty("period",is(2)));
    }

    @Test
    public void whenFindAll_thenReturnTermList(){
        assertThat(termRepository.findAll(),hasItem(
                hasProperty("period",is(2))
        ));
    }

    @Test
    public void whenFindByPeriod_thenReturnTermList(){
        assertThat(termRepository.findByPeriod(2), hasItem(hasProperty("period", is(2))));
    }

    @Test
    public void whenFindByCourses_thenReturnTermList(){
        assertThat(termRepository.findByCourses(course), hasItem(hasProperty("period", is(2))));
    }

    @Test
    public void whenFindByStartDate_thenReturnTermList(){
        assertThat(termRepository.findByStartDate(Date.valueOf(LocalDate.MIN)),
                hasItem(hasProperty("startDate", is(Date.valueOf(LocalDate.MIN)))));
    }

    @Test
    public void whenFindByEndDate_thenReturnTermList(){
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
    public void whenFindByGroup_thenReturnTermList(){
        assertThat(termRepository.findByGroups(group),hasItem(
                hasProperty("period",is(2))
        ));
    }

    @Test
    public void whenFindByStudentAttendsCourses_thenReturnTermList(){
        assertThat(termRepository.findByStudentAttendsCourses(studentAttendsCourse),hasItem(
                hasProperty("period",is(2))
        ));
    }
}
