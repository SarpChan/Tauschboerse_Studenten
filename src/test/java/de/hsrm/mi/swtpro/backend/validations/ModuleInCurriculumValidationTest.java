package de.hsrm.mi.swtpro.backend.validations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.ModuleInCurriculum;



public class ModuleInCurriculumValidationTest {

    private Validator validator;
    private ModuleInCurriculum moduleInCurriculum;
    private Curriculum curriculum;
    private Module module;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        curriculum = Curriculum.builder()
        .build();
        module = Module.builder()
        .build();
    }

    @Test
    public void whenTermPeriodNotNull_thenNoConstraintViolation() {
        moduleInCurriculum = ModuleInCurriculum.builder()
        .id(17)
        .termPeriod(2)
        .curriculum(curriculum)
        .module(module)
        .build();
        Set<ConstraintViolation<ModuleInCurriculum>> violations = validator.validate(moduleInCurriculum);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenCurriculumNotNull_thenNoConstraintViolation() {
        moduleInCurriculum = ModuleInCurriculum.builder()
        .id(17)
        .termPeriod(2)
        .curriculum(curriculum)
        .module(module)
        .build();
        Set<ConstraintViolation<ModuleInCurriculum>> violations = validator.validate(moduleInCurriculum);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenCurriculumNull_thenConstraintViolation() {
        moduleInCurriculum = ModuleInCurriculum.builder()
        .id(17)
        .termPeriod(2)
        .curriculum(null)
        .module(module)
        .build();
        Set<ConstraintViolation<ModuleInCurriculum>> violations = validator.validate(moduleInCurriculum);

        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenModuleNotNull_thenNoConstraintViolation() {
        moduleInCurriculum = ModuleInCurriculum.builder()
        .id(17)
        .termPeriod(2)
        .curriculum(curriculum)
        .module(module)
        .build();
        Set<ConstraintViolation<ModuleInCurriculum>> violations = validator.validate(moduleInCurriculum);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenModuleNull_thenConstraintViolation() {
        moduleInCurriculum = ModuleInCurriculum.builder()
        .id(17)
        .termPeriod(2)
        .curriculum(curriculum)
        .module(null)
        .build();
        Set<ConstraintViolation<ModuleInCurriculum>> violations = validator.validate(moduleInCurriculum);

        Assert.assertEquals(1, violations.size());
    }
}
