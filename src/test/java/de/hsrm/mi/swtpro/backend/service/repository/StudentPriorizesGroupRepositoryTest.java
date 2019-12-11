package de.hsrm.mi.swtpro.backend.service.repository;


import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentPrioritizesGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentPriorizesGroupRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudentPrioritizesGroupRepository studentPrioritizesGroupRepository;

    private Student student;
    private Group group;
    private long id;

    @Before
    public void setUp(){
        student = Student.builder().build();
        group = Group.builder().build();

        StudentPrioritizesGroup studentPrioritizesGroup = StudentPrioritizesGroup.builder()
                .group(group)
                .student(student)
                .priority(2)
                .build();

        entityManager.persist(student);
        entityManager.persist(group);
        entityManager.persist(studentPrioritizesGroup);
        id = studentPrioritizesGroup.getId();
    }

    @Test
    public void whenFindAll_thenReturnStudentAttendsCourseList(){
        assertThat(studentPrioritizesGroupRepository.findAll(),hasItem(
               hasProperty("priority",is(2))
        ));
    }

    @Test
    public void whenFindById_thenReturnStudentAttendsCourse(){
        assertTrue(studentPrioritizesGroupRepository.findById(id).isPresent());
        assertThat(studentPrioritizesGroupRepository.findById(id).get(),
                hasProperty("priority",is(2))
        );
    }

    @Test
    public void whenFindByPriority_thenReturnStudentAttendsCourseList(){
        assertThat(studentPrioritizesGroupRepository.findByPriority(2),hasItem(
                hasProperty("id",is(id))
        ));
    }

    @Test
    public void whenFindByStudent_thenReturnStudentAttendsCourseList(){
        assertThat(studentPrioritizesGroupRepository.findByStudent(student),hasItem(
                hasProperty("priority",is(2))
        ));
    }

    @Test
    public void whenFindByGroup_thenReturnStudentAttendsCourseList(){
        assertThat(studentPrioritizesGroupRepository.findByGroup(group),hasItem(
                hasProperty("priority",is(2))
        ));
    }

}
