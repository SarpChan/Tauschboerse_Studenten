package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class AdministratorTest {

    Administrator administrator;

    @Before
    public void setUp(){
        administrator = Administrator.builder()
                .rights(1)
                .build();
    }

    @Test
    public void whenGetRights_thenReturnRights(){
        assertEquals(1,administrator.getRights());
    }

    @Test
    public void whenSetRights_thenSaveRights(){
        administrator.setRights(2);
        assertEquals(2,administrator.getRights());
    }
}
