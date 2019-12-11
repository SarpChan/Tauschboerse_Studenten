package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class LectureTest {

    private Lecturer lecturer;

    @Before
    public void setUp(){
        lecturer = Lecturer.builder()
                .priviledge(1)
                .build();
    }

    @Test
    public void whenGetPriviledge_theReturnPriviledge(){
        assertEquals(1,lecturer.getPriviledge());
    }

    @Test
    public void whenSetPriviledge_theSavePriviledge(){
        lecturer.setPriviledge(2);
        assertEquals(2, lecturer.getPriviledge());
    }
}
