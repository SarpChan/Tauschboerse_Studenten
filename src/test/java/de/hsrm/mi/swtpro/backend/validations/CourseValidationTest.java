package de.hsrm.mi.swtpro.backend.validations;

import static org.junit.Assert.assertFalse;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import de.hsrm.mi.swtpro.backend.model.Campus;
import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.User;

public class CourseValidationTest {
    private Validator validator;
    private  User user;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        user = User.builder()
        .id(20)
        .firstName("name")
        .lastName("lastName")
        .loginName("loginName")
        .password("password")
        .build();
    }

    @Test
    public void whenTitleEmpty_thenConstraintViolation() {
        Course course =  Course.builder()
        .id(10)
        .owner(user)
        .build();
    
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenOwnerNull_thenConstraintViolation() {
        Course course =  Course.builder()
        .id(10)
        .title("title")
        .build();
        
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        assertFalse(violations.isEmpty());
    }

    public void whenUniversityNull_thenConstraintViolation() {
        Campus campus =  Campus.builder()
        .id(10)
        .name("Name")
        .build();
        
        Set<ConstraintViolation<Campus>> violations = validator.validate(campus);
        assertFalse(violations.isEmpty());
    }
}