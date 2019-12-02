package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.Term;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static  org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TermRepositoryTest {



    @Autowired
    EntityManager entityManager;
    @Autowired
    TermRepository termRepository;

    private Course course;

    @Before
    public void setUp(){

        Course course = Course.builder()
                .title("Mathe")
                .build();

        Term term = Term.builder()
                .period(2)
                .startDate(Date.valueOf(LocalDate.MIN))
                .endDate(Date.valueOf(LocalDate.MAX))
                .course(course)
                .build();

        entityManager.persist(course);
        entityManager.persist(term);
    }

    @Test
    public void whenFindByPeriod_thenReturnTermList(){
        assertThat(termRepository.findByPeriod(2), hasItem(hasProperty("period", is(2))));
    }

    @Test
    public void whenFindByCourses_thenReturnTermList(){
        assertThat(termRepository.findByCourses(course), hasItem(hasProperty("title", is("Mathe"))));
    }

    @Test
    public void whenFindByStartDate_thenReturnTermList(){

    }

    @Test
    public void whenFindByEndDate_thenReturnTermList(){

    }

}
