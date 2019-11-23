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
        examRegulation = ExamRegulation.builder()
                .date(Date.valueOf(LocalDate.MAX))
                .build();

        module = Module.builder()
                .title("Mathe 3")
                .build();

        curriculum = Curriculum.builder()
                .examRegulation(examRegulation)
                .modules(new HashSet<>(Collections.singletonList(module)))
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
        module = Module.builder()
                .title("Mathe 4")
                .build();

        moduleList.add(module);
        curriculum.setModules(moduleList);
        assertThat(curriculum.getModules(),hasItem(module));
    }
}
