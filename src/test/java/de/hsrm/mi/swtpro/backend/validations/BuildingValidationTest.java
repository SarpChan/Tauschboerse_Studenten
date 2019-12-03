package de.hsrm.mi.swtpro.backend.validations;

import static org.junit.Assert.assertFalse;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.model.Campus;
import de.hsrm.mi.swtpro.backend.model.University;


public class BuildingValidationTest {

    private Validator validator;
    private  Campus campus;


    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        University university = University.builder()
        .id(20)
        .name("Name")
        .address("55555 Wiesbaden Strasse 17")
        .build();

        campus =  Campus.builder()
        .id(10)
        .name("name")
        .address("55555 Wiesbaden Strasse 17")
        .university(university)
        .build();
    }
    
    @Test
    public void whenCampusNull_thenConstraintViolation() {
        Building building =  Building.builder()
        .id(10)
        .name("Name")
        .build();
    
        Set<ConstraintViolation<Building>> violations = validator.validate(building);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenNameEmpty_thenConstraintViolation() {
        Building building =  Building.builder()
        .id(10)
        .campus(campus)
        .build();
    
        Set<ConstraintViolation<Building>> violations = validator.validate(building);
        assertFalse(violations.isEmpty());
    }
}