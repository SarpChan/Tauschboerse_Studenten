package de.hsrm.mi.swtpro.backend.validations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.Term;

public class StudentValidationTest {

    private Validator validator;
    private Student student;
    private ExamRegulation examRegulation;
    private Term term;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        examRegulation = ExamRegulation.builder()
        .build();
        term = Term.builder()
        .build();

    }

    @Test
    public void whenEnrollmentNumberNotNullAndInRegex_thenNoConstraintViolation() {
        student = Student.builder()
        .enrollmentNumber(36)
        .mail("vorname.nachname@student.hs-rm.de")
        .examRegulation(examRegulation)
        .enrolmentTerm(term)
        .build();
        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenMailNotNullAndInRegex_thenNoConstraintViolation() {
        student = Student.builder()
        .enrollmentNumber(13)
        .mail("vorname.nachname@student.hs-rm.de")
        .examRegulation(examRegulation)
        .enrolmentTerm(term)
        .build();
        
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenMailNull_thenConstraintViolation() {
        student = Student.builder()
        .enrollmentNumber(24)
        .mail(null)
        .examRegulation(examRegulation)
        .enrolmentTerm(term)
        .build();
        
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
 
        Assert.assertEquals(1, violations.size());
    }
   /* @Test
    public void whenMailNotInRegex_thenConstraintViolation() {
        student = Student.builder()
        .enrollmentNumber(0)
        .mail("ibinkeinemail")
        .examRegulation(examRegulation)
        .enrolmentTerm(term)
        .build();
        
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
 
        Assert.assertEquals(1, violations.size());
    }
    */
    @Test
    public void whenExamRegulationNotNull_thenNoConstraintViolation() {
        student = Student.builder()
        .enrollmentNumber(12)
        .mail("vorname.nachname@student.hs-rm.de")
        .examRegulation(examRegulation)
        .enrolmentTerm(term)
        .build();
        
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenExamRegulationNull_thenConstraintViolation() {
        student = Student.builder()
        .enrollmentNumber(33)
        .mail("vorname.nachname@student.hs-rm.de")
        .examRegulation(null)
        .enrolmentTerm(term)
        .build();
        
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenTermNotNull_thenNoConstraintViolation() {
        student = Student.builder()
        .enrollmentNumber(55)
        .mail("vorname.nachname@student.hs-rm.de")
        .examRegulation(examRegulation)
        .enrolmentTerm(term)
        .build();
        
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenTermNull_thenConstraintViolation() {
        student = Student.builder()
        .enrollmentNumber(101)
        .mail("vorname.nachname@student.hs-rm.de")
        .examRegulation(examRegulation)
        .enrolmentTerm(null)
        .build();
        
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
 
        Assert.assertEquals(1, violations.size());
    }
}    