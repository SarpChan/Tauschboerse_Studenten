package de.hsrm.mi.swtpro.backend.validations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.User;

public class UserValidationTest {

    private Validator validator;
    private  User user;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenFirstNameNotEmpty_thenNoConstraintViolation() {
        user = User.builder()
        .id(17)
        .firstName("Joendhard")
        .lastName("Biffel")
        .loginName("joen001")
        .password("ibinpasswort")
        .build();
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenFirstNameEmpty_thenConstraintViolation() {
        user = User.builder()
        .id(17)
        .lastName("Biffel")
        .loginName("joen001")
        .password("ibinpasswort")
        .build();
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenLastNameNotEmpty_thenNoConstraintViolation() {
        user = User.builder()
        .id(17)
        .firstName("Joendhard")
        .lastName("Biffel")
        .loginName("joen001")
        .password("ibinpasswort")
        .build();
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenLastNameEmpty_thenConstraintViolation() {
        user = User.builder()
        .id(17)
        .firstName("Joendhard")
        .loginName("joen001")
        .password("ibinpasswort")
        .build();
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenLoginNameNotEmpty_thenNoConstraintViolation() {
        user = User.builder()
        .id(17)
        .firstName("Joendhard")
        .lastName("Biffel")
        .loginName("joen001")
        .password("ibinpasswort")
        .build();
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenLoginNameEmpty_thenConstraintViolation() {
        user = User.builder()
        .id(17)
        .firstName("Joendhard")
        .lastName("Biffel")
        .password("ibinpasswort")
        .build();
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenPasswordNotEmpty_thenNoConstraintViolation() {
        user = User.builder()
        .id(17)
        .firstName("Joendhard")
        .lastName("Biffel")
        .loginName("joen001")
        .password("ibinpasswort")
        .build();
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenPasswordEmpty_thenConstraintViolation() {
        user = User.builder()
        .id(17)
        .firstName("Joendhard")
        .lastName("Biffel")
        .loginName("joen001")
        .build();
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);
 
        Assert.assertEquals(1, violations.size());
    }
    
    
}