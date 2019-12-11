package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseComponentRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    CourseComponentRepository courseComponentRepository;

    private Group group;
    private long id;
    private Course course;
    private StudentPassedExam passedExam;

    @Before
    public void setUp(){
        course  = Course.builder()
                .title("Analysis")
                .build();

        passedExam = StudentPassedExam.builder()
                .grade(17f)
                .build();

        CourseComponent courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .exam("Test")
                .studentPassedExam(passedExam)
                .creditPoints(10)
                .build();

        group = Group.builder()
                .groupChar('D')
                .courseComponent(courseComponent)
                .build();

        entityManager.persist(course);
        entityManager.persist(group);
        entityManager.persist(courseComponent);
        entityManager.persist(passedExam);
        id = courseComponent.getId();
    }

    @Test
    public void whenFindAll_thenReturnCourseComponentList(){
        assertEquals(1,courseComponentRepository.findAll().size());
    }

    @Test
    public void whenFindById_theReturnCourseComponent(){
        assertTrue(courseComponentRepository.findById(id).isPresent());
        assertEquals("Test",courseComponentRepository.findById(id).get().getExam());
    }

    @Test
    public void whenFindByType_thenReturnCourseComponentList(){
        assertEquals(id,courseComponentRepository.findByType(CourseType.LECTURE).get(0).getId());
    }

    @Test
    public void whenFindByCreditPoints_thenReturnCourseComponentList(){
        assertThat(courseComponentRepository.findByCreditPoints(10),
                hasItem(hasProperty("exam",is("Test"))));
    }

    @Test
    public void whenFindByGroup_thenReturnCourseComponentList(){
        assertThat(courseComponentRepository.findByGroups(group),
                hasItem(hasProperty("creditPoints",is(10))));
    }

    @Test
    public void whenFindByExam_thenReturnCourseComponentList(){
        assertThat(courseComponentRepository.findByExam("Test"),
                hasItem(hasProperty("creditPoints",is(10))));
    }

    @Test
    public void whenFindByCourse_thenReturnCourseComponentList(){
        assertThat(courseComponentRepository.findByCourse(course),hasItem(
                hasProperty("creditPoints",is(10))
        ));
    }

    @Test
    public void whenFindByStudentPassedExam_thenReturnCourseComponentList(){
        assertThat(courseComponentRepository.findByStudentPassedExam(passedExam), hasItem(
                hasProperty("creditPoints",is(10))
        ));
    }
}
