package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudentRepository studentRepository;

    private Student student;

    @Before
    public void setUp(){

        student = Student.builder()
                .enrolementNumber(1742).build();

        entityManager.persist(student);

    }


    @Test
    public void whenFindByEnrolementNumber_thanReturnStudent(){
        assertTrue(studentRepository.findByEnrolementNumber(1742).isPresent());
        assertThat(studentRepository.findByEnrolementNumber(1742).get(), hasProperty("enrolementNumber", is(1742)));
    }

    @Test
    public void whenDeleteStudent_thanRemoveStudent(){
        studentRepository.delete(student);
        assertEquals(studentRepository.findAll().size(), 0);
    }

}
