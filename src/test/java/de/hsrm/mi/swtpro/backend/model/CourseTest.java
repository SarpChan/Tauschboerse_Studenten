package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@SpringBootTest
public class CourseTest {

    private Course course;
    private User user;
    private Module module;
    private Set<Module> modules;

    @Before
    public void setUp(){
        user = new User("Gustav","Gans","glueck001","pass",true);
        course = new Course.Builder("HCI")
                .withOwner(user)
                .build();
        modules = new HashSet<>();
        module = new Module.Builder("erleuchtendes Module").build();
        modules.add(module);
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
    public void whenSetTerm_thenSaveTerm(){
        Term term = new Term.Builder().inPeriod(1).build();
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
        User user = new User("Karl","Karlson","kool","wort",false);
        course.setOwner(user);
        assertEquals("Karlson",course.getOwner().getLastName());
    }

    @Test
    public void whenSetModule_thenSaveModule(){
        course.setModules(modules);
        assertThat(course.getModules(),hasItem(module));
    }

}
