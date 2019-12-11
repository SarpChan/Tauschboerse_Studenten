package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class ModuleInCurriculumTest {

   private ModuleInCurriculum moduleInCurriculum;

    @Before
    public void setUp(){

        Curriculum curriculum = Curriculum.builder()
                .id(23L)
                .build();

        Module module = Module.builder()
                .id(45L)
                .build();

        moduleInCurriculum = ModuleInCurriculum.builder()
                .id(17L)
                .termPeriod(2)
                .curriculum(curriculum)
                .module(module)
                .build();
    }

    @Test
    public void whenGetId_thenReturnId(){
        assertEquals(17L,moduleInCurriculum.getId());
    }

    @Test
    public void whenGetTermPeriod_thenReturnTermPeriod(){
        assertEquals(2,moduleInCurriculum.getTermPeriod());
    }

    @Test
    public void whenGetCurriculum_thenReturnCurriculum(){
        assertThat(moduleInCurriculum.getCurriculum(),
                hasProperty("id",is(23L)));
    }

    @Test
    public void whenGetModule_thenReturnModule(){
        assertThat(moduleInCurriculum.getModule(),
                hasProperty("id",is(45L)));
    }

    @Test
    public void whenSetId_thenSaveId(){
        moduleInCurriculum.setId(65L);
        assertEquals(65L,moduleInCurriculum.getId());
    }

    @Test
    public void whenSetTermPeriod_thenSaveTermPeriod(){
        moduleInCurriculum.setTermPeriod(1);
        assertEquals(1,moduleInCurriculum.getTermPeriod());
    }

    @Test
    public void whenSetModule_thenSaveModule(){
        Module module = Module.builder()
                .id(69L)
                .build();
        moduleInCurriculum.setModule(module);
        assertThat(moduleInCurriculum.getModule(),
                hasProperty("id",is(69L)));
    }

    @Test
    public void whenSetCurriculum_thenSaveCurriculum(){
        Curriculum curriculum = Curriculum.builder()
                .id(13L)
                .build();
        moduleInCurriculum.setCurriculum(curriculum);
        assertThat(moduleInCurriculum.getCurriculum(),
                hasProperty("id",is(13L))
        );
    }
}
