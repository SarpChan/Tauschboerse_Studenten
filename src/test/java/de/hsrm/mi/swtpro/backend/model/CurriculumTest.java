package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class CurriculumTest {

    private Curriculum curriculum;

    @Before
    public void setUp(){
        ExamRegulation examRegulation = ExamRegulation.builder()
                .date(Date.valueOf(LocalDate.MAX))
                .build();

        ModuleInCurriculum moduleInCurriculum = ModuleInCurriculum.builder()
                .id(65)
                .build();

        curriculum = Curriculum.builder()
                .examRegulation(examRegulation)
                .moduleInCurriculum(moduleInCurriculum)
                .maxTerms(2)
                .build();
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
    public void whenGetMaxTerms_thenReturnMaxTerms(){
        assertEquals(2,curriculum.getMaxTerms());
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

    @Test
    public void whenSetMaxTerm_thenSaveMaxTerms(){
        curriculum.setMaxTerms(1);
        assertEquals(1, curriculum.getMaxTerms());
    }
}
