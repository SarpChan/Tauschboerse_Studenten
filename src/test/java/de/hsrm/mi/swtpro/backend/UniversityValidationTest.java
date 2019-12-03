package de.hsrm.mi.swtpro.backend;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.University;

public class UniversityValidationTest {

    private Validator validator;
    private  University university;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenNameNotEmpty_thenNoConstraintViolation() {
        university = University.builder()
        .id(17)
        .name("Hochschule RheinMain")
        .address("65183 Wiesbaden, Unter den Eichen 3")
        .build();
        
        Set<ConstraintViolation<University>> violations = validator.validate(university);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenNameEmpty_thenConstraintViolation() {
        university = University.builder()
        .id(17)
        .address("65183 Wiesbaden, Unter den Eichen 3")
        .build();
        
        Set<ConstraintViolation<University>> violations = validator.validate(university);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenAddressNotEmpty_thenNoConstraintViolation() {
        university = University.builder()
        .id(17)
        .name("Hochschule RheinMain")
        .address("65183 Wiesbaden, Unter den Eichen 3")
        .build();
        
        Set<ConstraintViolation<University>> violations = validator.validate(university);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenAddressmpty_thenConstraintViolation() {
        university = University.builder()
        .id(17)
        .name("Hochschule RheinMain")
        .build();
        
        Set<ConstraintViolation<University>> violations = validator.validate(university);
 
        Assert.assertEquals(1, violations.size());
    }
}