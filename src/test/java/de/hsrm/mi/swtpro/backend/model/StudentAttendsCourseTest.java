package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class StudentAttendsCourseTest {

    private StudentAttendsCourse studentAttendsCourse;

    @Before
    public void setUp(){

        Course course = Course.builder()
                .title("Mathe 5")
                .build();

        Student student = Student.builder()
                .enrollmentNumber(174217)
                .build();

        Term term = Term.builder()
                .id(42)
                .startDate(Date.valueOf(LocalDate.of(2017,6,17)))
                .endDate(Date.valueOf(LocalDate.of(2017,8,17)))
                .build();

        studentAttendsCourse = StudentAttendsCourse.builder()
                .id(17)
                .course(course)
                .student(student)
                .term(term)
                .build();
    }

    @Test
    public void whenGetId_thenReturnId(){
        assertEquals(17,studentAttendsCourse.getId());
    }

    @Test
    public void whenGetStudent_thenReturnStudent(){
        assertEquals(174217,studentAttendsCourse.getStudent().getEnrollmentNumber());
    }

    @Test
    public void whenGetCourse_thenReturnCourse(){
        assertEquals("Mathe 5",studentAttendsCourse.getCourse().getTitle());
    }

    @Test
    public void whenGetTerm_thenReturnTerm(){
        assertEquals(42,studentAttendsCourse.getTerm().getId());
    }

    @Test
    public void whenSetId_thenSaveId(){
        studentAttendsCourse.setId(21);
        assertEquals(21,studentAttendsCourse.getId());
    }

    @Test
    public void whenSetStudent_thenSaveStudent(){
        Student student = Student.builder()
                .enrollmentNumber(151578)
                .build();
        studentAttendsCourse.setStudent(student);

        assertEquals(151578,studentAttendsCourse.getStudent().getEnrollmentNumber());
    }

    @Test
    public void whenSetCourse_thenSaveCourse(){
        Course course = Course.builder()
                .title("Pizza & Bier")
                .build();
        studentAttendsCourse.setCourse(course);

        assertEquals("Pizza & Bier",studentAttendsCourse.getCourse().getTitle());
    }

    @Test
    public void whenSetTerm_thenSaveTerm(){
        Term term = Term.builder()
                .id(69)
                .startDate(Date.valueOf(LocalDate.of(2017,6,17)))
                .endDate(Date.valueOf(LocalDate.of(2017,8,17)))
                .build();
        studentAttendsCourse.setTerm(term);

        assertEquals(69,studentAttendsCourse.getTerm().getId());
    }


}
