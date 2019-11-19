package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

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
        Course course = new Course.Builder("curse").build();
        courseComponent = new CourseComponent.Builder(course,CourseType.LECTURE).build();
        lecture = new User("Doc Cheese","Cheesy","cheese","none",true);
        group = new Group.Builder()
                .hasSlots(17)
                .hasLecturer(lecture)
                .withEndTime(LocalTime.MIDNIGHT)
                .withStartTime(LocalTime.of(13,27))
                .onDayOfWeek(DayOfWeek.MONDAY)
                .build(courseComponent,'D');


        student = new Student(
                "Dödel","Werner","dower","1235",false,
                1047892,new Term.Builder().build(),"a@e.de",
                new ExamRegulation.Builder(Date.valueOf(LocalDate.MAX)).build());
        term = new Term.Builder().build();
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
        assertEquals(LocalTime.MIDNIGHT.toNanoOfDay(),group.getStartTime().toNanoOfDay());
    }

    @Test
    public void whenGetCourseComponent_thenReturnCourseComponent(){
        assertEquals(courseComponent.getId(),group.getCourseComponent().getId());
    }
}
