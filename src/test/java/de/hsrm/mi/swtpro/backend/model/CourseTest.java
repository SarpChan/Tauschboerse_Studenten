package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@SpringBootTest
public class CourseTest {

    private Course course;
    private User user;

    @Before
    public void setUp(){
        user = new User("Gustav","Gans","glueck001","pass",true);
        course = new Course.Builder("HCI")
                .withOwner(user)
                .build();
    }

    @Test
    public void whenGetTitle_thenReturnTitle(){
        assertEquals("HCI",course.getTitle());
    }

    @Test
    public void whenGetUser_thenReturnUser(){
        assertEquals("Gustav",course.getOwner().getFirstName());
    }

    @Test
    public void whenSetTerm_thenSaveTerm(){
        Term term = new Term.Builder().inPeriod(1).build();
        assertThat(course.getTerms(),hasItem(term));
    }


}
