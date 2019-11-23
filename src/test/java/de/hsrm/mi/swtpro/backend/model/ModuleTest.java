package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class ModuleTest {

    private Module module;
    private Course course;

    @Before
    public void setUp(){
        ExamRegulation examRegulation = ExamRegulation.builder()
                .date(Date.valueOf(LocalDate.of(2017, 10, 14)))
                .build();

        Curriculum curriculum = Curriculum.builder()
                .id(17)
                .examRegulation(examRegulation)
                .build();

        course = Course.builder()
                .title("Kurs")
                .build();

        module = Module.builder()
                .title("Analysis")
                .period(2)
                .curriculums(new HashSet<>(Collections.singleton(curriculum)))
                .creditPoints(10)
                .courses(new HashSet<>(Collections.singletonList(course)))
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
        assertThat(module.getCurriculums(),hasItem(
                hasProperty("id",is(Integer.toUnsignedLong(17)))
        ));
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
        Course course = Course.builder()
                .title("neuer")
                .build();

        courseSet.add(course);
        module.setCourses(courseSet);
        assertThat(module.getCourses(),hasItem(
                hasProperty("title",is("neuer"))
        ));
    }

    @Test
    public void whenSetCurricula_thenSaveCurricula(){
        Set<Curriculum> curricula = new HashSet<>();

        ExamRegulation examRegulation = ExamRegulation.builder()
                .date(Date.valueOf(LocalDate.of(2018,10,15)))
                .build();

        Curriculum newCurriculum = Curriculum.builder()
                .id(421)
                .examRegulation(examRegulation)
                .build();

        curricula.add(newCurriculum);
        module.setCurriculums(curricula);

        assertThat(module.getCurriculums(),hasItem(
                hasProperty("id",is(Integer.toUnsignedLong(421)))
        ));
    }


}
