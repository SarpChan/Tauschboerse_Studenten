package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;



@SpringBootTest
public class UserTest {

    private User user;

    @Before
    public void setUp(){


        user = User.builder()
                .id(17)
                .firstName("Helmut")
                .lastName("Schmidt")
                .password("1234")
                .loginName("Schmitti")
                .build();

    }


    @Test
    public void whenGetId_thanReturnId(){assertEquals(user.getId(), 17); }

    @Test
    public void whenGetFirstName_thenReturnFirstName(){assertEquals(user.getFirstName(), "Helmut");}

    @Test
    public void whenGetLastName_thenReturnFirstName(){assertEquals(user.getLastName(), "Schmidt");}

    @Test
    public void whenGetPassWord_thenReturnPassWord(){assertEquals(user.getPassword(), "1234");}

    @Test
    public void whenGetLoginName_thenReturnLoginName(){assertEquals(user.getLoginName(), "Schmitti");}

    @Test
    public void whenSetId_thanSaveId(){
        user.setId(42);
        assertEquals(user.getId(), 42);
    }

    @Test
    public void whenSetFirstName_thanSaveFirstName(){
        user.setFirstName("Oscar");
        assertEquals(user.getFirstName(), "Oscar");
    }


    @Test
    public void whenSetLastName_thanSaveLastName(){
        user.setLastName("Wilde");
        assertEquals(user.getLastName(), "Wilde");
    }

    @Test
    public void whenSetPassWord_thanSavePassWord(){
        user.setPassword("4321");
        assertEquals(user.getPassword(), "4321");
    }

    @Test
    public void whenSetLoginName_thanSaveLoginName(){
        user.setLoginName("Dumpfbacke");
        assertEquals(user.getLoginName(), "Dumpfbacke");
    }


}
