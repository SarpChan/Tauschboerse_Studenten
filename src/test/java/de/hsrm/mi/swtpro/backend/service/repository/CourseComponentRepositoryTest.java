package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.CourseType;
import de.hsrm.mi.swtpro.backend.model.Group;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseComponentRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    CourseComponentRepository courseComponentRepository;

    private Group group;

    @Before
    public void setUp(){
        Course course  = Course.builder()
                .title("Analysis")
                .build();

        CourseComponent courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .exam("Test")
                .creditPoints(10)
                .build();

        group = Group.builder()
                .courseComponent(courseComponent)
                .group('D')
                .build();

        courseComponent.addGroup(group);
        courseComponent.setId(17);

        entityManager.persist(course);
        entityManager.persist(courseComponent);
        entityManager.persist(group);
    }

    @Test
    public void whenFindAll_thenReturnCourseComponentList(){
        assertEquals(1,courseComponentRepository.findAll().size());
    }

    @Test
    public void whenFindById_theReturnCourseComponent(){
        assertEquals("Test",courseComponentRepository.findById(17).getExam());
    }

    @Test
    public void whenFindByType_thenReturnCourseComponentList(){
        assertEquals(17,courseComponentRepository.findByType(CourseType.LECTURE).get(0).getId());
    }

    @Test
    public void whenFindByCreditPoints_thenReturnCourseComponentList(){
        assertEquals(17,courseComponentRepository.findByCreditPoints(10).get(0).getId());
    }

    @Test
    public void whenFindByGroup_thenReturnCourseComponentList(){
        assertEquals(17,courseComponentRepository.findByGroups(group).get(0).getId());
    }

    @Test
    public void whenFindByExam_thenReturnCourseComponentList(){
        assertEquals(17,courseComponentRepository.findByExam("Test").get(0).getId());
    }
}
