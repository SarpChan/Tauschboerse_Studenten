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
    private  Building building;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();


        University university = University.builder()
        .id(20)
        .name("Name")
        .address("55555 Wiesbaden Strasse 17")
        .build();

        Campus campus =  Campus.builder()
        .id(10)
        .name("name")
        .address("55555 Wiesbaden Strasse 17")
        .university(university)
        .build();



        building =  Building.builder()
        .id(10)
        .name("Name")
        .campus(campus)
        .build();
    }

    @Test
    public void testContactSuccess() {
       
        Set<ConstraintViolation<Building>> violations = validator.validate(building);
        assertFalse(violations.isEmpty());
    }
}