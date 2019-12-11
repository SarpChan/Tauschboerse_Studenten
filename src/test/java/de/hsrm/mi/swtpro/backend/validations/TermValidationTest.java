package de.hsrm.mi.swtpro.backend.validations;

import java.sql.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.Term;

public class TermValidationTest {

    private Validator validator;
    private  Term term;
    private Date startDate;
    private Date endDate;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        startDate = new Date(System.currentTimeMillis());
        endDate = new Date(System.currentTimeMillis());
    }

    @Test
    public void whenStartDateNotNull_thenNoConstraintViolation() {
        term = Term.builder()
        .id(17)
        .startDate(startDate)
        .endDate(endDate)
        .period(17)
        .build();
        
        Set<ConstraintViolation<Term>> violations = validator.validate(term);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenStartDateNull_thenConstraintViolation() {
        term = Term.builder()
        .id(17)
        .startDate(null)
        .endDate(endDate)
        .period(17)
        .build();
        
        Set<ConstraintViolation<Term>> violations = validator.validate(term);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenEndDateNotNull_thenNoConstraintViolation() {
        term = Term.builder()
        .id(17)
        .startDate(startDate)
        .endDate(endDate)
        .period(17)
        .build();
        
        Set<ConstraintViolation<Term>> violations = validator.validate(term);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenEndDateNull_thenConstraintViolation() {
        term = Term.builder()
        .id(17)
        .startDate(startDate)
        .endDate(null)
        .period(17)
        .build();
        
        Set<ConstraintViolation<Term>> violations = validator.validate(term);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenPeriodNotNull_thenNoConstraintViolation() {
        term = Term.builder()
        .id(17)
        .startDate(startDate)
        .endDate(endDate)
        .period(17)
        .build();
        
        Set<ConstraintViolation<Term>> violations = validator.validate(term);
 
        Assert.assertEquals(0, violations.size());
    }
}