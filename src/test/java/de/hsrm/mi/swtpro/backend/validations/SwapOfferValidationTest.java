package de.hsrm.mi.swtpro.backend.validations;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;;

public class SwapOfferValidationTest {

    private Validator validator;
    private  SwapOffer swapOffer;
    private Timestamp date;
    private Student student;
    private Group group;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        date = new Timestamp(System.currentTimeMillis());
        student = Student.builder()
        .enrolementNumber(0)
        .build();
        group = Group.builder()
        .build();
    }

    @Test
    public void whenDateNotNull_thenNoConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .date(date)
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
        .date(null)
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
        .date(date)
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
        .date(date)
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
        .date(date)
        .student(student)
        .fromGroup(group)
        .toGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenFromGroupNull_thenNoConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .date(date)
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
        .date(date)
        .student(student)
        .fromGroup(group)
        .toGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenToGroupNull_thenNoConstraintViolation() {
        swapOffer = SwapOffer.builder()
        .id(17)
        .date(date)
        .student(student)
        .fromGroup(group)
        .build();
        
        Set<ConstraintViolation<SwapOffer>> violations = validator.validate(swapOffer);
 
        Assert.assertEquals(1, violations.size());
    }
    
}