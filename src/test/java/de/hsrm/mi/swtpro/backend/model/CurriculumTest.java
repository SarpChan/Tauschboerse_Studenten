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
public class CurriculumTest {

    private Curriculum curriculum;
    private Set<Module> moduleList;
    private Module module;

    @Before
    public void setUp(){
        ExamRegulation examRegulation = ExamRegulation.builder()
                .date(Date.valueOf(LocalDate.MAX))
                .build();

        module = Module.builder()
                .title("Mathe 3")
                .build();

        ModuleInCurriculum moduleInCurriculum = ModuleInCurriculum.builder()
                .id(65)
                .build();

        curriculum = Curriculum.builder()
                .examRegulation(examRegulation)
                .moduleInCurriculum(moduleInCurriculum)
                .build();

        moduleList = new HashSet<>();
    }

    @Test
    public void whenGetExamRegulation_thenReturnExamRegulation(){
        assertEquals(Date.valueOf(LocalDate.MAX),curriculum.getExamRegulation().getDate());
    }

    @Test
    public void whenGetModulesInCurriculum_thenReturnModules(){
        assertThat(curriculum.getModulesInCurriculum(),hasItem(
                hasProperty("id",is(65L))
        ));
    }

    @Test
    public void whenSetId_thenSaveId(){
        curriculum.setId(17);
        assertEquals(17,curriculum.getId());
    }

    @Test
    public void whenSetC_thenSaveModules(){
        ModuleInCurriculum moduleIncurriculum = ModuleInCurriculum.builder()
                .id(34)
                .build();

        curriculum.setModulesInCurriculum(new HashSet<>(Collections.singletonList(moduleIncurriculum)));
        assertThat(curriculum.getModulesInCurriculum(),hasItem(
                hasProperty("id",is(34L))
        ));
    }
}
