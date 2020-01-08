package de.hsrm.mi.swtpro.backend.validations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentPrioritizesGroup;

public class StudentPrioritizesGroupValidationTest {

    private Validator validator;
    private  StudentPrioritizesGroup studentPriorizesGroup;
    private Student student;
    private Group group;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        student = Student.builder()
        //.enrolementNumber(0)
        .build();
        group = Group.builder()
        .build();
    }

    @Test
    public void whenPriorityNotNull_thenNoConstraintViolation() {
        studentPriorizesGroup = StudentPrioritizesGroup.builder()
        .id(17)
        .priority(17)
        .student(student)
        .group(group)
        .build();
        
        Set<ConstraintViolation<StudentPrioritizesGroup>> violations = validator.validate(studentPriorizesGroup);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenStudentNotNull_thenNoConstraintViolation() {
        studentPriorizesGroup = StudentPrioritizesGroup.builder()
        .id(17)
        .priority(17)
        .student(student)
        .group(group)
        .build();
        
        Set<ConstraintViolation<StudentPrioritizesGroup>> violations = validator.validate(studentPriorizesGroup);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenStudentNull_thenConstraintViolation() {
        studentPriorizesGroup = StudentPrioritizesGroup.builder()
        .id(17)
        .priority(17)
        .student(null)
        .group(group)
        .build();
        
        Set<ConstraintViolation<StudentPrioritizesGroup>> violations = validator.validate(studentPriorizesGroup);
 
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenGroupNotNull_thenNoConstraintViolation() {
        studentPriorizesGroup = StudentPrioritizesGroup.builder()
        .id(17)
        .priority(17)
        .student(student)
        .group(group)
        .build();
        
        Set<ConstraintViolation<StudentPrioritizesGroup>> violations = validator.validate(studentPriorizesGroup);
 
        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenGroupNull_thenConstraintViolation() {
        studentPriorizesGroup = StudentPrioritizesGroup.builder()
        .id(17)
        .priority(17)
        .student(student)
        .group(null)
        .build();
        
        Set<ConstraintViolation<StudentPrioritizesGroup>> violations = validator.validate(studentPriorizesGroup);
 
        Assert.assertEquals(1, violations.size());
    }
}
