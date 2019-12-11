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
import de.hsrm.mi.swtpro.backend.model.University;

public class CampusValidationTest {
    private Validator validator;
    private  University university;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        university = University.builder()
        .id(20)
        .name("Name")
        .address("55555 Wiesbaden Strasse 17")
        .build();
    }

    @Test
    public void whenNameNull_thenConstraintViolation() {
        Campus campus =  Campus.builder()
        .id(10)
        .address("55555 Wiesbaden Strasse 17")
        .university(university)
        .build();
    
        Set<ConstraintViolation<Campus>> violations = validator.validate(campus);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenAdresseEmpty_thenConstraintViolation() {
        Campus campus =  Campus.builder()
        .id(10)
        .name("Name")
        .university(university)
        .build();
        
        Set<ConstraintViolation<Campus>> violations = validator.validate(campus);
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