package de.hsrm.mi.swtpro.backend.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class UserTest {

    private String[] validTestNames = {"Günter","Tobi","Lukas","Niemand"};
    private String validPassword = "password";

    @Test
    public void constructorTest(){
        ConstructorTest("walter","willig","ww001","password",false);
        ConstructorTest("wsdndf","sjkdbf","2736%3","pass89123sdskjd",true);
    }

    void ConstructorTest(String firstName, String lastName, String loginName, String password, boolean admin){

        User testUser = new User(firstName,lastName,loginName,password,admin);
        assertEquals(testUser.getFirstName(),firstName);
        assertEquals(testUser.getLastName(),lastName);
        assertEquals(testUser.getLoginName(),loginName);
        assertEquals(testUser.getPassword(),password);
        assertEquals(testUser.isAdmin(),admin);
    }

    @Test
    void getSetTest(){
        getSetTest("walter","ewew","idjsfio","ajdnsd§§2837",false);
        getSetTest("wsadas","edf","893475","s§§28sd37",true);
    }

    private void getSetTest(String firstName, String lastName, String loginName, String password, boolean admin){
        User testUser = new User("wrong","wrong","wrong","wrong",true);

        testUser.setFirstName(firstName);
        testUser.setLastName(lastName);
        testUser.setLoginName(loginName);
        testUser.setPassword(password);
        testUser.setAdmin(admin);

        assertEquals(testUser.getFirstName(),firstName);
        assertEquals(testUser.getLastName(),lastName);
        assertEquals(testUser.getLoginName(),loginName);
        assertEquals(testUser.getPassword(),password);
        assertEquals(testUser.isAdmin(),admin);
    }
}
