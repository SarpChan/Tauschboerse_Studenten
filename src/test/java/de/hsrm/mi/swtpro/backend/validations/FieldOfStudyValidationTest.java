
package de.hsrm.mi.swtpro.backend.validations;

import static org.junit.Assert.assertFalse;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import de.hsrm.mi.swtpro.backend.model.FieldOfStudy;
import de.hsrm.mi.swtpro.backend.model.University;


public class FieldOfStudyValidationTest {
    private Validator validator;
    private University university;

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
    public void whenTitleEmpty() {
        FieldOfStudy fieldOfStudy = FieldOfStudy.builder()
        .id(10)
        .university(university)
        .build();

        Set<ConstraintViolation<FieldOfStudy>> violations = validator.validate(fieldOfStudy);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenUniversityNull() {
        FieldOfStudy fieldOfStudy = FieldOfStudy.builder()
        .id(10)
        .title("title")
        .build();

        Set<ConstraintViolation<FieldOfStudy>> violations = validator.validate(fieldOfStudy);
        assertFalse(violations.isEmpty());
    }
}