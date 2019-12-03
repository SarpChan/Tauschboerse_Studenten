
package de.hsrm.mi.swtpro.backend.validations;

import static org.junit.Assert.assertFalse;

import java.sql.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.StudyProgram;


public class ExamRegulationValidationTest {
    private Validator validator;
    private StudyProgram studyProgram;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        studyProgram = StudyProgram.builder()
        .id(10)
        .title("title")
        .degree("degree")
        .build();
    }

    @Test
    public void whenDateNull() {
        ExamRegulation examRegulation = ExamRegulation.builder()
        .id(10)
        .rule(1)
        .studyProgram(studyProgram)
        .build();

        Set<ConstraintViolation<ExamRegulation>> violations = validator.validate(examRegulation);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenStudyProgrammNull() {
        Date date = new Date(0);
        ExamRegulation examRegulation = ExamRegulation.builder()
        .id(10)
        .rule(1)
        .date(date)
        .build();

        Set<ConstraintViolation<ExamRegulation>> violations = validator.validate(examRegulation);
        assertFalse(violations.isEmpty());
    }
}