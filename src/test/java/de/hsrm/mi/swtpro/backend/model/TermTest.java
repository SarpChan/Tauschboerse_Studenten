package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class TermTest {

    private Term term;

    @Before
    public void setUp(){

        Set<Course> courses = new HashSet<>();
        courses.add(Course.builder().title("Mathe").build());
        courses.add(Course.builder().title("Stricken").build());
        courses.add(Course.builder().title("Zaubertränke").build());

        Set<StudentAttendsCourse> studentAttendsCourses = new HashSet<>();
        studentAttendsCourses.add(StudentAttendsCourse.builder().id(17).build());
        studentAttendsCourses.add(StudentAttendsCourse.builder().id(18).build());
        studentAttendsCourses.add(StudentAttendsCourse.builder().id(19).build());


        term = Term.builder()
                .id(17)
                .period(2)
                .startDate(Date.valueOf(LocalDate.MAX))
                .endDate(Date.valueOf(LocalDate.MIN))
                .courses(courses)
                .studentsAttendCourses(studentAttendsCourses)
                .build();

    }

    @Test
    public void whenGetId_thanReturnId(){assertEquals(term.getId(), 17); }

    @Test
    public void whenGetPeriod_thanReturnPeriod(){assertEquals(term.getPeriod(), 2);}

    @Test
    public void whenGetStartDate_thanReturnStartDate(){assertEquals(term.getStartDate(), Date.valueOf(LocalDate.MAX));}

    @Test
    public void whenGetEndDate_thanReturnEndDate(){assertEquals(term.getEndDate(), Date.valueOf(LocalDate.MIN));}

    @Test
    public void whenGetCourses_thanReturnCourses(){
        assertThat(term.getCourses(), containsInAnyOrder(
                hasProperty("title", is("Mathe")),
                hasProperty("title", is("Stricken")),
                hasProperty("title", is("Zaubertränke"))
        ));
    }

    @Test
    public void whenSetId_thanSaveId(){
        term.setId(21);
        assertEquals(term.getId(), 21);
    }

    @Test
    public void whenSetPeriod_thanSavePeriod(){
        term.setPeriod(1);
        assertEquals(term.getPeriod(),1);
    }

    public void whenSetStartDate_thanSaveStartDate(){
        term.setStartDate(Date.valueOf(LocalDate.MIN));
        assertEquals(term.getStartDate(), Date.valueOf(LocalDate.MIN));
    }

    public void whenSetEndDate_thanSaveEndDate(){
        term.setStartDate(Date.valueOf(LocalDate.MAX));
        assertEquals(term.getStartDate(), Date.valueOf(LocalDate.MAX));
    }

    public void whenAddCourse_thanSaveCourse(){
        term.addCourse(Course.builder().id(1717).build());
        assertThat(term.getCourses(), hasItem(hasProperty("id", is(1717))));
    }



    /*
    //TODO: Fehler in Zeile 71 finden
    @Test
    public void whenGetStudentAttendCourses_thanReturnStudentsAttendCourses(){
        assertThat(term.getStudentsAttendCourses(), hasItems(
                hasProperty("id", is(17)),
                hasProperty("id", is(18)),
                hasProperty("id", is(19))
        ));

    }

    */





}
