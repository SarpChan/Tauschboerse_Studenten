package de.hsrm.mi.swtpro.backend.validations;

import java.sql.Timestamp;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
;

public class SwapOfferValidationTest {

    private Validator validator;
    private SwapOffer swapOffer;
    private Timestamp date;
    private Student student;
    private Group group;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        date = new Timestamp(System.currentTimeMillis());
        student = Student.builder()
        .enrollmentNumber(0)
        .build();
        group = Group.builder()
        .build();
    }

    @Test
    public void whenDateNotNull_thenNoConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .timestamp(date)
        .student(student)
        .fromGroup(group)
        .toGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenDateNull_thenConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .timestamp(null)
        .student(student)
        .fromGroup(group)
        .toGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenStudentNotNull_thenNoConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .timestamp(date)
        .student(student)
        .fromGroup(group)
        .toGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenStudentNull_thenConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .timestamp(date)
        .fromGroup(group)
        .toGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenFromGroupNotNull_thenNoConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .timestamp(date)
        .student(student)
        .fromGroup(group)
        .toGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenFromGroupNull_thenConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .timestamp(date)
        .student(student)
        .toGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenToGroupNotNull_thenNoConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .timestamp(date)
        .student(student)
        .fromGroup(group)
        .toGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenToGroupNull_thenConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .timestamp(date)
        .student(student)
        .fromGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(1, violations.size());
    }
    
}