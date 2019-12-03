
package de.hsrm.mi.swtpro.backend.validations;

import static org.junit.Assert.assertFalse;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.CourseType;
import de.hsrm.mi.swtpro.backend.model.User;

public class CourseComponentValidationTest {

    private Validator validator;
    
    private User owner;
    private Course course;
    private CourseType courseType;


    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        courseType = CourseType.LECTURE;

        owner = User.builder()
        .id(5)
        .firstName("firstName")
        .lastName("lastName")
        .loginName("loginName")
        .password("password")
        .build();

        course = Course.builder()
        .id(4)
        .title("title")
        .owner(owner)
        .build();
    }

    @Test
    public void whenCourseTypeNull() {
        CourseComponent courseComponent = CourseComponent.builder()
            .id(1)
            .creditPoints(5)
            .exam("exam")
            .course(course)
            .build();
    
        Set<ConstraintViolation<CourseComponent>> violations = validator.validate(courseComponent);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenCreditPointsSmallerThenZero() {
        CourseComponent courseComponent = CourseComponent.builder()
            .id(1)
            .type(courseType)
            .creditPoints(-1)
            .exam("exam")
            .course(course)
            .build();
    
        Set<ConstraintViolation<CourseComponent>> violations = validator.validate(courseComponent);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenExamEmpty() {
        CourseComponent courseComponent = CourseComponent.builder()
            .id(1)
            .type(courseType)
            .creditPoints(5)
            .exam("")
            .course(course)
            .build();
    
        Set<ConstraintViolation<CourseComponent>> violations = validator.validate(courseComponent);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenCourseNull() {
        CourseComponent courseComponent = CourseComponent.builder()
            .id(1)
            .type(courseType)
            .creditPoints(5)
            .exam("exam")
            .build();
    
        Set<ConstraintViolation<CourseComponent>> violations = validator.validate(courseComponent);
        assertFalse(violations.isEmpty());
    }

}