package de.hsrm.mi.swtpro.backend.validations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentPassedExam;

public class StudentPassedExamValidationTest {

    private Validator validator;
    private  StudentPassedExam studentPassedExam;
    private Student student;
    private CourseComponent courseComponent;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        student = Student.builder()
        .enrolementNumber(0)
        .build();
        courseComponent = CourseComponent.builder()
        .build();
    }

    @Test
    public void whenGradeNotNull_thenNoConstraintViolation() {
        studentPassedExam = StudentPassedExam.builder()
        .id(17)
        .grade(2)
        .student(student)
        .courseComponent(courseComponent)
        .build();
        
        Set<ConstraintViolation<StudentPassedExam>> violations = validator.validate(studentPassedExam);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenStudentNotNull_thenNoConstraintViolation() {
        studentPassedExam = StudentPassedExam.builder()
        .id(17)
        .grade(2)
        .student(student)
        .courseComponent(courseComponent)
        .build();
        
        Set<ConstraintViolation<StudentPassedExam>> violations = validator.validate(studentPassedExam);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenStudentNull_thenConstraintViolation() {
        studentPassedExam = StudentPassedExam.builder()
        .id(17)
        .grade(2)
        .student(null)
        .courseComponent(courseComponent)
        .build();
        
        Set<ConstraintViolation<StudentPassedExam>> violations = validator.validate(studentPassedExam);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenCourseComponentNotNull_thenNoConstraintViolation() {
        studentPassedExam = StudentPassedExam.builder()
        .id(17)
        .grade(2)
        .student(student)
        .courseComponent(courseComponent)
        .build();
        
        Set<ConstraintViolation<StudentPassedExam>> violations = validator.validate(studentPassedExam);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenCourseComponentNull_thenConstraintViolation() {
        studentPassedExam = StudentPassedExam.builder()
        .id(17)
        .grade(2)
        .student(student)
        .courseComponent(null)
        .build();
        
        Set<ConstraintViolation<StudentPassedExam>> violations = validator.validate(studentPassedExam);
 
        Assert.assertEquals(1, violations.size());
    }
}