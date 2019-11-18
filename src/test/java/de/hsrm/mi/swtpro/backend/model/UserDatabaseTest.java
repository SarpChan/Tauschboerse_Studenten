package de.hsrm.mi.swtpro.backend.model;

import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserDatabaseTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void constructorTest(){
        ConstructorTest("walter","willig","ww001","password",false);
        ConstructorTest("wsdndf","sjkdbf","2736%3","pass89123sdskjd",true);
    }

    private void ConstructorTest(String firstName, String lastName, String loginName, String password, boolean admin){

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

    @Test
    private void insertToDatabaseTest(){
        insertToDatabaseTest("walter","ewew","idjsfio","ajdnsd§§2837",false);
        insertToDatabaseTest("walter","sdfj","sdkjf","adsf",true);

    }
    private void insertToDatabaseTest(String firstName, String lastName, String loginName, String password, boolean admin){
        User testUser = new User(firstName,lastName,loginName,password,admin);

        entityManager.persist(testUser);
        entityManager.flush();

        assertThat(userRepository.findAll(), hasItem(testUser));
        assertThat(userRepository.findByFirstName(firstName), hasItem(testUser));
        assertThat(userRepository.findByLastName(lastName),hasItem(testUser));
        assertThat(userRepository.findByAdmin(admin),hasItem(testUser));

        assertEquals(userRepository.findByLoginName(loginName),testUser);
    }

}
