package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class ModuleTest {

    private Module module;
    private Curriculum curriculum;
    private Course course;

    @Before
    public void setUp(){
        ExamRegulation examRegulation = new ExamRegulation.Builder(Date.valueOf(LocalDate.of(2017, 10, 14))).build();
        curriculum = new Curriculum.Builder(examRegulation).build();
        course = new Course.Builder("Kurs").build();

        module = new Module.Builder("Analysis")
                .inPeriod(2)
                .inCurriculum(curriculum)
                .hasCreditPoints(10)
                .withCourse(course)
                .build();
    }

    @Test
    public void whenGetTitle_thenReturnTitle(){
        assertEquals("Analysis",module.getTitle());
    }

    @Test
    public void whenGetPeriod_thenReturnPeriod(){
        assertEquals(2,module.getPeriod());
    }

    @Test
    public void whenGetCreditPoints_thenReturnCreditPoints(){
        assertEquals(10,module.getCreditPoints());
    }

    @Test
    public void whenGetCourse_thenReturnCourse(){
        assertThat(module.getCourses(),hasItem(course));
    }

    @Test
    public void whenGetCurriculum_thenReturnCurriculum(){
        assertThat(module.getCurriculums(),hasItem(curriculum));
    }

    @Test
    public void whenSetTitle_thenSaveTitle(){
        module.setTitle("neuer Title");
        assertEquals("neuer Title",module.getTitle());
    }

    @Test
    public void whenSetPeriod_thenSavePeriod(){
        module.setPeriod(1);
        assertEquals(1,module.getPeriod());
    }

    @Test
    public void whenSetCreditPoints_thenSaveCreditPoints(){
        module.setCreditPoints(5);
        assertEquals(5,module.getCreditPoints());
    }

    @Test
    public void whenSetCourse_thenSaveCourse(){
        Set<Course> courseSet = new HashSet<>();
        Course newCourse = new Course.Builder("neuer").build();
        courseSet.add(course);

        module.setCourses(courseSet);
        assertThat(module.getCourses(),hasItem(newCourse));
    }

    @Test
    public void whenSetCurricula_thenSaveCurricula(){
        Set<Curriculum> curricula = new HashSet<>();
        ExamRegulation examRegulation = new ExamRegulation.Builder(
                Date.valueOf(LocalDate.of(2018,10,15)))
                    .build();
        Curriculum newCurriculum = new Curriculum.Builder(examRegulation)
                .build();

        curricula.add(newCurriculum);
        module.setCurriculums(curricula);
        assertThat(module.getCurriculums(),hasItem(newCurriculum));
    }


}
