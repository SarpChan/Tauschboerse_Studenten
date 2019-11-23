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
        User user = User.builder()
                .firstName("niemand")
                .lastName("Nimandson")
                .loginName("nie")
                .password("password")
                .build();

        Course course = Course.builder()
                .title("A")
                .owner(user)
                .build();

        CourseComponent courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .build();

        course.getCourseComponents().add(courseComponent);
        entityManager.persist(user);
    }
}
