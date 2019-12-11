package de.hsrm.mi.swtpro.backend.validations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentAttendsCourse;
import de.hsrm.mi.swtpro.backend.model.Term;;

public class StudentAttendsCourseValidationTest {

    private Validator validator;
    private  StudentAttendsCourse studentAttendsCourse;
    private Student student;
    private Course course;
    private Term term;


    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        student = Student.builder()
        .enrolementNumber(0)
        .build();
        course = Course.builder()
        .build();
        term = Term.builder()
        .build();
    }

    @Test
    public void whenStudentNotNull_thenNoConstraintViolation() {
        studentAttendsCourse = StudentAttendsCourse.builder()
        .id(17)
        .student(student)
        .course(course)
        .term(term)
        .build();
        
        Set<ConstraintViolation<StudentAttendsCourse>> violations = validator.validate(studentAttendsCourse);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenStudentNull_thenConstraintViolation() {
        studentAttendsCourse = StudentAttendsCourse.builder()
        .id(17)
        .student(null)
        .course(course)
        .term(term)
        .build();
        
        Set<ConstraintViolation<StudentAttendsCourse>> violations = validator.validate(studentAttendsCourse);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenCourseNotNull_thenNoConstraintViolation() {
        studentAttendsCourse = StudentAttendsCourse.builder()
        .id(17)
        .student(student)
        .course(course)
        .term(term)
        .build();
        
        Set<ConstraintViolation<StudentAttendsCourse>> violations = validator.validate(studentAttendsCourse);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenCourseNull_thenConstraintViolation() {
        studentAttendsCourse = StudentAttendsCourse.builder()
        .id(17)
        .student(student)
        .course(null)
        .term(term)
        .build();
        
        Set<ConstraintViolation<StudentAttendsCourse>> violations = validator.validate(studentAttendsCourse);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenTermNotNull_thenNoConstraintViolation() {
        studentAttendsCourse = StudentAttendsCourse.builder()
        .id(17)
        .student(student)
        .course(course)
        .term(term)
        .build();
        
        Set<ConstraintViolation<StudentAttendsCourse>> violations = validator.validate(studentAttendsCourse);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenTermNull_thenConstraintViolation() {
        studentAttendsCourse = StudentAttendsCourse.builder()
        .id(17)
        .student(student)
        .course(course)
        .term(null)
        .build();
        
        Set<ConstraintViolation<StudentAttendsCourse>> violations = validator.validate(studentAttendsCourse);
 
        Assert.assertEquals(1, violations.size());
    }
}    