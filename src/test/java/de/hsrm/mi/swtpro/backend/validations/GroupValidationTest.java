
package de.hsrm.mi.swtpro.backend.validations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.model.Campus;
import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.CourseType;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Lecturer;
import de.hsrm.mi.swtpro.backend.model.Room;
import de.hsrm.mi.swtpro.backend.model.Term;
import de.hsrm.mi.swtpro.backend.model.University;
import de.hsrm.mi.swtpro.backend.model.User;

public class GroupValidationTest {
    private Validator validator;
    private Term term;
    private CourseComponent courseComponent;
    private Course course;
    private User owner;
    private Lecturer lecturer;
    private Room room;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Date date = new Date(0);
        CourseType courseType = CourseType.LECTURE;
        term = Term.builder().id(20).startDate(date).endDate(date).period(1).build();
        
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

        Building building =  Building.builder()
        .id(10)
        .campus(campus)
        .name("name")
        .build();

        room = Room.builder()
        .id(5)
        .number(5)
        .seats(20)
        .building(building)
        .build();

        lecturer = Lecturer.builder()
        .priviledge(4)
        .build();

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

		courseComponent = CourseComponent.builder()
            .id(1)
            .type(courseType)
            .creditPoints(5)
            .exam("exam")
            .course(course)
            .build();
    }

    @Test
    public void whenGroupCharNotNull_thenConstraintViolation() {
        LocalDate localDate = LocalDate.of(1947, Month.AUGUST, 15); 
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate); 
        LocalTime startTime = LocalTime.now();;
        
		Group group = Group.builder()
        .id(10)
        .slots(1)
        .dayOfWeek(dayOfWeek)
        .startTime(startTime)
        .endTime(startTime)
        .term(term)
        .courseComponent(courseComponent)
        .lecturer(lecturer)
        .room(room)
        .build();

        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void whenSlotsNotNull_thenConstraintViolation() {
        LocalDate localDate = LocalDate.of(1947, Month.AUGUST, 15); 
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate); 
        LocalTime startTime = LocalTime.now();;
        
        Group group = Group.builder()
        .id(10)
        .groupChar('A')
        .slots(6)
        .dayOfWeek(dayOfWeek)
        .startTime(startTime)
        .endTime(startTime)
        .term(term)
        .courseComponent(courseComponent)
        .lecturer(lecturer)
        .room(room)
        .build();

        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void whenDayofWeekNull_thenConstraintViolation() {
        LocalTime startTime = LocalTime.now();
        
        Group group = Group.builder()
        .id(10)
        .groupChar('A')
        .slots(1)
        .startTime(startTime)
        .endTime(startTime)
        .term(term)
        .courseComponent(courseComponent)
        .lecturer(lecturer)
        .room(room)
        .build();

        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertFalse(violations.isEmpty());
    }
    @Test
    public void whenStartTimeNull_thenConstraintViolation() {
        LocalDate localDate = LocalDate.of(1947, Month.AUGUST, 15); 
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate); 
        LocalTime startTime = LocalTime.now();;
        
        Group group = Group.builder()
        .id(10)
        .groupChar('A')
        .slots(1)
        .dayOfWeek(dayOfWeek)
        .endTime(startTime)
        .term(term)
        .courseComponent(courseComponent)
        .lecturer(lecturer)
        .room(room)
        .build();

        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertFalse(violations.isEmpty());
    }
    @Test
    public void whenEndTimeNull_thenConstraintViolation() {
        LocalDate localDate = LocalDate.of(1947, Month.AUGUST, 15); 
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate); 
        LocalTime startTime = LocalTime.now();
        
        Group group = Group.builder()
        .id(10)
        .groupChar('A')
        .slots(1)
        .dayOfWeek(dayOfWeek)
        .startTime(startTime)
        .term(term)
        .courseComponent(courseComponent)
        .lecturer(lecturer)
        .room(room)
        .build();

        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenTermNull_thenConstraintViolation() {
        LocalDate localDate = LocalDate.of(1947, Month.AUGUST, 15); 
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);  
        LocalTime startTime = LocalTime.now();;
        
        Group group = Group.builder()
        .id(10)
        .groupChar('A')
        .slots(1)
        .dayOfWeek(dayOfWeek)
        .startTime(startTime)
        .endTime(startTime)
        .courseComponent(courseComponent)
        .lecturer(lecturer)
        .room(room)
        .build();

        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertFalse(violations.isEmpty());
    }
    @Test
    public void whenCourseComponentNull_thenConstraintViolation() {
        LocalDate localDate = LocalDate.of(1947, Month.AUGUST, 15); 
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate); 
        LocalTime startTime = LocalTime.now();;
        
        Group group = Group.builder()
        .id(10)
        .groupChar('A')
        .slots(1)
        .dayOfWeek(dayOfWeek)
        .startTime(startTime)
        .endTime(startTime)
        .term(term)
        .lecturer(lecturer)
        .room(room)
        .build();

        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenLectureNull_thenConstraintViolation() {
        LocalDate localDate = LocalDate.of(1947, Month.AUGUST, 15); 
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate); 
        LocalTime startTime = LocalTime.now();;
        
        Group group = Group.builder()
        .id(10)
        .groupChar('A')
        .slots(1)
        .dayOfWeek(dayOfWeek)
        .startTime(startTime)
        .endTime(startTime)
        .term(term)
        .courseComponent(courseComponent)
        .room(room)
        .build();

        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenRoomNull_thenConstraintViolation() {
        LocalDate localDate = LocalDate.of(1947, Month.AUGUST, 15); 
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate); 
        LocalTime startTime = LocalTime.now();;
        
        Group group = Group.builder()
        .id(10)
        .groupChar('A')
        .slots(1)
        .dayOfWeek(dayOfWeek)
        .startTime(startTime)
        .endTime(startTime)
        .term(term)
        .courseComponent(courseComponent)
        .lecturer(lecturer)
        .build();

        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertFalse(violations.isEmpty());
    }
}