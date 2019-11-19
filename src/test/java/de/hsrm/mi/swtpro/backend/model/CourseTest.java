package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseTest {

    Course course;
    User user;

    @Before
    public void setUp(){
        User user;

        course = new Course.Builder("HCI").build();
    }
}
