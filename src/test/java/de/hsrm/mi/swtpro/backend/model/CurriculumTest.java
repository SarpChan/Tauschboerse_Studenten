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
public class CurriculumTest {

    private Curriculum curriculum;
    private ExamRegulation examRegulation;
    private Set<Module> moduleList;
    private Module module;

    @Before
    public void setUp(){
        examRegulation = new ExamRegulation.Builder(Date.valueOf(LocalDate.MAX)).build();
        module = new Module.Builder("Mathe 3").build();
        curriculum = new Curriculum.Builder(examRegulation)
                .hasModule(module)
                .build();

        moduleList = new HashSet<>();
    }

    @Test
    public void whenGetExamRegulation_thenReturnExamRegulation(){
        assertEquals(Date.valueOf(LocalDate.MAX),curriculum.getExamRegulation().getDate());
    }

    @Test
    public void whenGetModules_thenReturnModules(){
        assertThat(curriculum.getModules(),hasItem(module));
    }

    @Test
    public void whenSetId_thenSaveId(){
        curriculum.setId(17);
        assertEquals(17,curriculum.getId());
    }

    @Test
    public void whenSetModules_thenSaveModules(){
        module = new Module.Builder("Mathe 4").build();

        moduleList.add(module);
        curriculum.setModules(moduleList);
        assertThat(curriculum.getModules(),hasItem(module));
    }
}
