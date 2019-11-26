package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasItem;
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
                .enrolementNumber(5)
                .attendsCourses(new HashSet<>(Collections.singletonList(studentAttendsCourse)))
                .attendsGroups(new HashSet<>(Collections.singletonList(group)))
                .build();
    }

    @Test
    public void whenGetMail_thanReturnMail(){assertEquals(student.getMail(), "homer.simpson@springfield.de");}

    @Test
    public void whenGetUser_thanReturnUser(){assertEquals(student.getUser().getFirstName(), user.getFirstName());}

    @Test
    public void whenGetEnrollmentNumber_thanReturnEnrollmentNumber(){assertEquals(student.getEnrolementNumber(), 5);}

    @Test
    public void whenGetAttendsCourses_thanReturnAttendsCourse(){assertThat(student.getAttendsCourses(), hasItem(studentAttendsCourse));}

    @Test
    public void whenGetAttendsGroups_thanReturnAttendsGroups(){assertThat(student.getAttendsGroups(), hasItem(group));}

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
    public void whenSetEnrollmentNumber_thanSaveEnrollmentNumber(){
        student.setEnrolementNumber(29);
        assertEquals(student.getEnrolementNumber(), 29);
    }

    @Test
    public void whenSetStudentAttendsCourses_thanSaveCourses(){
        StudentAttendsCourse stc = StudentAttendsCourse.builder().id(123).build();
        student.setAttendsCourses(new HashSet<>(Collections.singletonList(stc)));
        assertThat(student.getAttendsCourses(), hasItem(stc));
    }

    @Test
    public void whenSetStudentAttendsGroups_thanSaveGroups(){
        Group groupA = Group.builder().id(42).build();
        student.setAttendsGroups(new HashSet<>(Collections.singletonList(groupA)));
        assertThat(student.getAttendsGroups(), hasItem(groupA));
    }



}
