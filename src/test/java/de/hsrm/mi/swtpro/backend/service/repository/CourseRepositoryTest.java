package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.CourseType;
import de.hsrm.mi.swtpro.backend.model.User;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    CourseRepository courseRepository;

    @Before
    public void setUp(){
        User user = new User("niemand","Nimandson","nie","password",false);
        Course course = new Course.Builder("A")
                .withOwner(user)
                .build();
        CourseComponent courseComponent = new CourseComponent.Builder(course,CourseType.LECTURE).build();
        course.getCourseComponents().add(courseComponent);

        entityManager.persist(user);
    }
}
