package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class CourseComponentTest {

    Course course;
    CourseComponent courseComponent;

    @Before
    public void setUp(){
        course = new Course.Builder("SuperCourse").build();
        courseComponent = new CourseComponent.Builder(course,CourseType.LECTURE)
                .hasExam("Klausur")
                .hasCreditPoints(17)
                .build();
    }

    @Test
    public void whenGetCourse_thenReturnCourse(){
        assertEquals("SuperCourse",courseComponent.getCourse().getTitle());
    }

    @Test
    public void whenGetType_thenReturnCourseType(){
        assertEquals(CourseType.LECTURE,courseComponent.getType());
    }

    @Test
    public void whenGetExam_thenReturnExam(){
        assertEquals("Klausur",courseComponent.getExam());
    }

    @Test
    public void whenGetCreditPoints_thenReturnCreditPoints(){
        assertEquals(17,courseComponent.getCreditPoints());
    }

    @Test
    public void whenSetId_thenSaveId(){
        courseComponent.setId(6007);
        assertEquals(6007,courseComponent.getId());
    }

    @Test
    public  void whenSetCourse_thenSaveCourse(){
        course = new Course.Builder("Anderer").build();
        courseComponent.setCourse(course);
        assertEquals("Anderer",courseComponent.getCourse().getTitle());
    }

    @Test
    public void whenSetType_thenSaveCourseType(){
        courseComponent.setType(CourseType.PRACTICE);
        assertEquals(CourseType.PRACTICE,courseComponent.getType());
    }

    @Test
    public void whenSetExam_thenSaveExam(){
        courseComponent.setExam("Booklet");
        assertEquals("Booklet",courseComponent.getExam());
    }

    @Test
    public void whenSetCreditPoints_thenSaveCreditPoints(){
        courseComponent.setCreditPoints(5);
        assertEquals(5,courseComponent.getCreditPoints());
    }

}
