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
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudentRepository studentRepository;

    private Student student;
    private Term term;
    private Group group;
    private StudentPrioritizesGroup studentPrioritizesGroup;
    private StudentPassedExam passedExam;
    private StudentAttendsCourse studentAttendsCourse;
    private ExamRegulation examRegulation;
    private User user;

    private long id;

    @Before
    public void setUp(){

        term = Term.builder().build();

        studentAttendsCourse = StudentAttendsCourse.builder().build();
        examRegulation = ExamRegulation.builder().build();
        user = User.builder().build();

        Student student = Student.builder()
                .mail("1234@gut.de")
                .enrolmentTerm(term)
                .prioritizeGroup(studentPrioritizesGroup)
                .passedExam(passedExam)
                .examRegulation(examRegulation)
                .attendCourse(studentAttendsCourse)
                .user(user)
                .enrolmentNumber(1742).build();

        group = Group.builder()
                .student(student)
                .build();

        studentPrioritizesGroup = StudentPrioritizesGroup.builder()
                .student(student)
                .build();

        passedExam = StudentPassedExam.builder()
                .student(student)
                .build();

        studentAttendsCourse = StudentAttendsCourse.builder()
                .student(student)
                .build();

        entityManager.persist(student);
        entityManager.persist(term);
        entityManager.persist(group);
        entityManager.persist(studentPrioritizesGroup);
        entityManager.persist(passedExam);
        entityManager.persist(studentAttendsCourse);
        entityManager.persist(examRegulation);
        entityManager.persist(user);

        id = student.getId();
    }

    @Test
    public void whenFindById_thenReturnStudent(){
        assertTrue(studentRepository.findById(id).isPresent());
        assertThat(studentRepository.findById(id).get(),
                hasProperty("mail",is("1234@gut.de")));
    }

    @Test
    public void whenFindAll_thenReturnStudentList(){
        assertThat(studentRepository.findAll(),hasItem(
                hasProperty("mail",is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByPrioritizeGroup_thenReturnStudentList(){
        assertThat(studentRepository.findByPrioritizeGroups(studentPrioritizesGroup),hasItem(
                hasProperty("mail",is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByPassedExams_thenReturnStudentList(){
        assertThat(studentRepository.findByPassedExams(passedExam),hasItem(
                hasProperty("mail",is("1234@gut.de"))
        ));
    }


    @Test
    public void whenFindByGroups_thenReturnStudentList(){
        assertThat(studentRepository.findByGroups(group),hasItem(
                hasProperty("mail",is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByAttendsCourse_thenReturnStudentList(){
        assertThat(studentRepository.findByAttendCourses(studentAttendsCourse),hasItem(
                hasProperty("mail",is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByExamRegulation_thenReturnStudentList(){
        assertThat(studentRepository.findByExamRegulation(examRegulation),hasItem(
                hasProperty("mail",is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByUser_thenReturnStudentList(){
        assertTrue(studentRepository.findByUser(user).isPresent());
        assertThat(studentRepository.findByUser(user).get(),
                hasProperty("mail",is("1234@gut.de"))
        );
    }

    @Test
    public void whenFindByEnrolmentTerm_thenReturnStudentList(){
        assertThat(studentRepository.findByEnrolmentTerm(term),hasItem(
                hasProperty("mail",is("1234@gut.de"))
        ));
    }

    @Test
    public void whenFindByMail_thenReturnStudentList(){
        assertThat(studentRepository.findByMail("1234@gut.de"),hasItem(
                hasProperty("id",is(id))
        ));
    }

    @Test
    public void whenFindByEnrolementNumber_thanReturnStudent(){
        assertTrue(studentRepository.findByEnrolmentNumber(1742).isPresent());
        assertThat(studentRepository.findByEnrolmentNumber(1742).get(), hasProperty("enrolementNumber", is(1742)));
    }

}
