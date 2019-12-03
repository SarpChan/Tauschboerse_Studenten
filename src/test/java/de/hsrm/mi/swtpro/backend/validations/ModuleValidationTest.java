package de.hsrm.mi.swtpro.backend.validations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.Module;



public class ModuleValidationTest {

    private Validator validator;
    private Module module;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenTitleNotEmpty_thenNoConstraintViolation() {
        module = Module.builder()
        .id(17)
        .title("Modulname")
        .creditPoints(0)
        .period(2)
        .build();
        Set<ConstraintViolation<Module>> violations = validator.validate(module);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenTitleEmpty_thenConstraintViolation() {
        module = Module.builder()
        .id(17)
        .creditPoints(0)
        .period(2)
        .build();
        Set<ConstraintViolation<Module>> violations = validator.validate(module);

        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenCreditPointsMinNull_thenNoConstraintViolation() {
        module = Module.builder()
        .id(17)
        .title("Modulname")
        .creditPoints(0)
        .period(2)
        .build();
        Set<ConstraintViolation<Module>> violations = validator.validate(module);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenCreditPointsMoreThanNull_thenNoConstraintViolation() {
        module = Module.builder()
        .id(17)
        .title("Modulname")
        .creditPoints(10)
        .period(2)
        .build();
        Set<ConstraintViolation<Module>> violations = validator.validate(module);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenCreditPointsNotMinNull_thenConstraintViolation() {
        module = Module.builder()
        .id(17)
        .title("Modulname")
        .creditPoints(-10)
        .period(2)
        .build();
        Set<ConstraintViolation<Module>> violations = validator.validate(module);

        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenPeriodNotNull_thenNoConstraintViolation() {
        module = Module.builder()
        .id(17)
        .title("Modulname")
        .creditPoints(0)
        .period(2)
        .build();
        Set<ConstraintViolation<Module>> violations = validator.validate(module);

        Assert.assertEquals(0, violations.size());
    }
    
}
