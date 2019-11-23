package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@SpringBootTest
public class CourseTest {

    private Course course;
    private User user;
    private Term term;
    private Module module;

    @Before
    public void setUp(){
        user = User.builder()
                .firstName("Gustav")
                .lastName("Gans")
                .loginName("glueck001")
                .password("pass")
                .build();

        term = Term.builder()
                .period(2)
                .build();

        module = Module.builder().
                title("erleuchtendes Module").
                build();

        course = Course.builder()
                .owner(user)
                .title("HCI")
                .modules(new HashSet<>(Collections.singletonList(module)))
                .terms(new HashSet<>(Collections.singletonList(term)))
                .build();
    }

    @Test
    public void whenGetTitle_thenReturnTitle(){
        assertEquals("HCI",course.getTitle());
    }

    @Test
    public void whenGetOwner_thenReturnUser(){
        assertEquals("Gustav",course.getOwner().getFirstName());
    }

    @Test
    public void whenGetModules_ReturnModuleList(){
        assertThat(course.getModules(),hasItem(module));
    }

    @Test
    public void whenSetTerm_thenSaveTerm(){
        Term term = Term.builder().period(1).build();
        Set<Term> terms = new HashSet<>();
        terms.add(term);
        course.setTerms(terms);

        assertThat(course.getTerms(),hasItem(term));
    }

    @Test
    public void whenSetTitle_thenSaveTitle(){
        course.setTitle("RIP");
        assertEquals("RIP",course.getTitle());
    }

    @Test
    public void whenSetOwner_thenSaveOwner(){
        User user = User.builder()
                .firstName("Karl")
                .lastName("Karlson")
                .password("wort")
                .loginName("kool")
                .build();

        course.setOwner(user);
        assertEquals("Karlson",course.getOwner().getLastName());
    }

}
