package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class StudentPassedExamTest {

    private StudentPassedExam studentPassedExam;

    @Before
    public void setUp(){
        Student student = Student.builder()
                .enrolmentNumber(178642)
                .build();

        CourseComponent courseComponent = CourseComponent.builder()
                .id(17)
                .build();

        studentPassedExam = StudentPassedExam.builder()
                .id(10001)
                .student(student)
                .courseComponent(courseComponent)
                .grade(1.7F)
                .build();
    }

    @Test
    public void whenGetId_thenReturnId(){
        assertEquals(10001,studentPassedExam.getId());
    }

    @Test
    public void whenGetStudent_thenReturnStudent(){
        assertEquals(178642,studentPassedExam.getStudent().getEnrolmentNumber());
    }

    @Test
    public void whenGetCourseComponent_thenReturnCourseComponent(){
        assertEquals(17,studentPassedExam.getCourseComponent().getId());
    }

    @Test
    public void whenSetId_thenSaveId(){
        studentPassedExam.setId(32);
        assertEquals(32,studentPassedExam.getId());
    }

    @Test
    public void whenSetStudent_thenSaveStudent(){
        Student student = Student.builder()
                .enrolmentNumber(222222)
                .build();
        studentPassedExam.setStudent(student);

        assertEquals(222222,studentPassedExam.getStudent().getEnrolmentNumber());
    }

    @Test
    public void whenSetCourseComponent_thenSaveCourseComponent(){
        CourseComponent courseComponent = CourseComponent.builder()
                .id(42)
                .build();
        studentPassedExam.setCourseComponent(courseComponent);

        assertEquals(42,studentPassedExam.getCourseComponent().getId());
    }
}
