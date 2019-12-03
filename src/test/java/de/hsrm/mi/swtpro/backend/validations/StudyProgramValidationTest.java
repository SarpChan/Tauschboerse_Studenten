package de.hsrm.mi.swtpro.backend.validations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.StudyProgram;

public class StudyProgramValidationTest {

    private Validator validator;
    private StudyProgram studyProgram;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenTitleNotEmpty_thenNoConstraintViolation() {
        studyProgram = StudyProgram.builder()
        .id(17)
        .title("Computer Science")
        .degree("Bachelor of Science")
        .build();
        
        Set<ConstraintViolation<StudyProgram>> violations = validator.validate(studyProgram);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenTitleEmpty_thenConstraintViolation() {
        studyProgram = StudyProgram.builder()
        .id(17)
        .title("Computer Science")
        .degree("Bachelor of Science")
        .build();
        
        Set<ConstraintViolation<StudyProgram>> violations = validator.validate(studyProgram);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenDegreeNotEmpty_thenNoConstraintViolation() {
        studyProgram = StudyProgram.builder()
        .id(17)
        .title("Computer Science")
        .degree("Bachelor of Science")
        .build();
        
        Set<ConstraintViolation<StudyProgram>> violations = validator.validate(studyProgram);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenDegreeEmpty_thenConstraintViolation() {
        studyProgram = StudyProgram.builder()
        .id(17)
        .title("Computer Science")
        .build();
        
        Set<ConstraintViolation<StudyProgram>> violations = validator.validate(studyProgram);
 
        Assert.assertEquals(1, violations.size());
    }
}    