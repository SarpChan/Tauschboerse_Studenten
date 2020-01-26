package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashSet;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
@SpringBootTest
public class StudentTest {

    private Student student;
    private User user;
    private  StudentAttendsCourse studentAttendsCourse;
    private Group group;

    @Before
    public void setUp() {

        studentAttendsCourse = StudentAttendsCourse.builder().id(17).build();
        group = Group.builder().id(42).build();


        user = User.builder()
                .firstName("Homer")
                .lastName("Simpson")
                .build();

        student = Student.builder()
                .mail("homer.simpson@springfield.de")
                .user(user)
                .enrolmentNumber(5)
                .attendCourse(studentAttendsCourse)
                .group(group)
                .build();
    }

    @Test
    public void whenGetMail_thanReturnMail(){assertEquals(student.getMail(), "homer.simpson@springfield.de");}

    @Test
    public void whenGetUser_thanReturnUser(){assertEquals(student.getUser().getFirstName(), user.getFirstName());}

    @Test
    public void whenGetEnrolmentNumber_thanReturnEnrolmentNumber(){assertEquals(student.getEnrolmentNumber(), 5);}

    @Test
    public void whenGetAttendsCourses_thanReturnAttendsCourse(){assertThat(student.getAttendCourses(), hasItem(studentAttendsCourse));}

    @Test
    public void whenGetAttendsGroups_thanReturnAttendsGroups(){assertThat(student.getGroups(), hasItem(group));}

    @Test
    public void whenSetMail_thanSaveMail(){
        student.setMail("bart.simpson@springfield.de");
        assertEquals(student.getMail(), "bart.simpson@springfield.de");
    }

    @Test
    public void whenSetUser_thanSaveUser(){
        user = User.builder()
                .firstName("Bart")
                .lastName("Simpson")
                .build();
        student.setUser(user);
        assertEquals(student.getUser().getFirstName(), user.getFirstName());

    }

    @Test
    public void whenSetEnrolmentNumber_thanSaveEnrolmentNumber(){
        student.setEnrolmentNumber(29);
        assertEquals(student.getEnrolmentNumber(), 29);
    }

    @Test
    public void whenSetStudentAttendsCourses_thanSaveCourses(){
        StudentAttendsCourse stc = StudentAttendsCourse.builder().id(123).build();
        student.setAttendCourses(new HashSet<>(Collections.singletonList(stc)));
        assertThat(student.getAttendCourses(), hasItem(stc));
    }

    @Test
    public void whenSetStudentAttendsGroups_thanSaveGroups(){
        Group groupA = Group.builder().id(42).build();
        student.setGroups(new HashSet<>(Collections.singletonList(groupA)));
        assertThat(student.getGroups(), hasItem(groupA));
    }



}
