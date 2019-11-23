package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class GroupTest {

    private Group group;
    private Term term;
    private Student student;
    private User lecture;
    private CourseComponent courseComponent;


    @Before
    public void setUp(){
        Course course =  Course.builder()
                .title("kurs")
                .build();

        courseComponent = CourseComponent.builder()
                .course(course)
                .creditPoints(17)
                .build();

        lecture = User.builder()
                .firstName("Doc Cheese")
                .lastName("Cheesy")
                .loginName("cheese")
                .password("none")
                .build();

        student = Student.builder()
                .mail("a@e.de")
                .build();

        group = Group.builder()
                .slots(17)
                .lecturer(lecture)
                .dayOfWeek(DayOfWeek.MONDAY)
                .endTime(LocalTime.MIDNIGHT)
                .startTime(LocalTime.of(13,27))
                .courseComponent(courseComponent)
                .students(new HashSet<>(Collections.singletonList(student)))
                .group('D')
                .build();
    }

    @Test
    public void whenGetGroup_thenReturnGroupSign(){
        assertEquals('D',group.getGroup());
    }

    @Test
    public void whenGetSlots_thenReturnSlots(){
        assertEquals(17,group.getSlots());
    }

    @Test
    public void whenGetDayOfWeek_thenReturnDayOfWeek(){
        assertEquals(DayOfWeek.MONDAY,group.getDayOfWeek());
    }

    @Test
    public void whenGetStartTime_thenReturnStartTime(){
        assertEquals(LocalTime.of(13,27).toNanoOfDay(),group.getStartTime().toNanoOfDay());
    }

    @Test
    public void whenGetEndTime_thenReturnEndTime(){
        assertEquals(LocalTime.MIDNIGHT.toNanoOfDay(),group.getEndTime().toNanoOfDay());
    }

    @Test
    public void whenGetCourseComponent_thenReturnCourseComponent(){
        assertEquals(courseComponent.getId(),group.getCourseComponent().getId());
    }
}
