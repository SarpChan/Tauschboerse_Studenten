package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class AdministratorTest {

    private Administrator administrator;

    @Before
    public void setUp(){
        administrator = Administrator.builder()
                .priviledge(1)
                .build();
    }

    @Test
    public void whenGetPriviledge_thenReturnRights(){
        assertEquals(1,administrator.getPriviledge());
    }

    @Test
    public void whenSetRights_thenSaveRights(){
        administrator.setPriviledge(2);
        assertEquals(2,administrator.getPriviledge());
    }
}
