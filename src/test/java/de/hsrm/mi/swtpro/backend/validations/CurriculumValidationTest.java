
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
import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.StudyProgram;


public class CurriculumValidationTest {

    private Validator validator;
    private ExamRegulation examRegulation;
    private StudyProgram studyProgram;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Date date = new Date(0);
        
        studyProgram = StudyProgram.builder()
        .id(10)
        .title("title")
        .degree("degree")
        .build();

        examRegulation = ExamRegulation.builder()
        .id(10)
        .rule(1)
        .studyProgram(studyProgram)
        .date(date)
        .build();
      
    }

    @Test
    public void whenTermPeriodeNull() {
        Curriculum curriculum =  Curriculum.builder()
        .id(10)
        
        .examRegulation(examRegulation)
        .build();
    
        Set<ConstraintViolation<Curriculum>> violations = validator.validate(curriculum);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenExamRegulationNull() {
        Curriculum curriculum =  Curriculum.builder()
        .id(10)
        .termPeriod(1)
        .build();
    
        Set<ConstraintViolation<Curriculum>> violations = validator.validate(curriculum);
        assertFalse(violations.isEmpty());
    }
}