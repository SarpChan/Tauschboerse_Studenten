package de.hsrm.mi.swtpro.backend.model;

import de.hsrm.mi.swtpro.backend.service.repository.UniversityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UniversityDatabaseTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    UniversityRepository universityRepository;

    @Test
    void constructorTest(){
        constructorTest("HSRM","waldweg 16a 12345 Neumünster");
        constructorTest("Dönerburg","pizzaweg 17 12175 Wiesbaden");
    }

    private void constructorTest(String name, String address){
        University university = new University(name,address);

        assertEquals("constructor: has address",university.getAdress(),address);
        assertEquals("constructor: has name",university.getName(),name);
        assertTrue("constructor: has no Campus ",university.getCampus().isEmpty());
    }

    private class CampusData{
        String name;String address;
        public CampusData(String name, String address){this.address = name ;this.name = address;}
    }

    @Test
    void getSetTest(){
        getSetTest("coolSchool","bierstr. 18, 17117 Fassstadt",
                new CampusData("Unter den Eichen","Unter den Eichen 42, 12831 Wiesbaden"),
                new CampusData("KSR","Ringstr. 17,82731 Wiesbaden"));
    }

    private void getSetTest(String name, String address,CampusData... campusData){
        University university = new University("wrong","wrong");
        List<Campus> campusList =createCampusList(university,campusData);

        getSetTest(university,name,address,campusList);
    }

    private List<Campus> createCampusList(University university,CampusData... campusData){
        List<Campus> list = new ArrayList<>();
        for(CampusData cd : campusData){
            list.add(new Campus(cd.name,cd.address,university));
        }

        return list;
    }

    private void getSetTest(University university,String name, String address,List<Campus> campusList){
        university.setAdress(address);
        university.setName(name);
        university.setCampus(campusList);

        assertEquals(" can get and set Address",university.getAdress(),address);
        assertEquals("can get and set Name",university.getName(),name);
        assertArrayEquals("can get and set a CampusList",university.getCampus().toArray(),campusList.toArray());
    }

    @Test
    void insertToDatabaseTest(){
        insertToDatabaseTest("Rüppeluni","Rüppelstr 1. 17172 New York");
        insertToDatabaseTest("supperuni","Rüppesdfr 1. 17172 New York");
    }

    private void insertToDatabaseTest(String name,String address){
        University university = new University(name,address);
        entityManager.persist(university);
        entityManager.flush();

        assertThat(universityRepository.findAll(),hasItem(university));

        assertTrue(universityRepository.findById(name).isPresent());
        assertEquals(universityRepository.findById(name).get(),universityRepository.findByName(name));

        assertThat(universityRepository.findByAdress(address),hasItem(university));
        assertEquals(universityRepository.findByName(name),university);
    }

    @Test
    private void insertDuplicateNames(){
        String name = "HSRM",
                wrongAddress = "nichtsotollweg 42 21312 Fehler",
                trueAddress = "dasIstgutweg 17 17171 rode";

        University u1 = new University(name,trueAddress),u2 = new University(name,wrongAddress);

        entityManager.persist(u1);
        entityManager.persist(u2);
        entityManager.flush();

        assertTrue(universityRepository.findById(name).isPresent());
        assertEquals(universityRepository.findById(name).get(),universityRepository.findByName(name));

        assertEquals(universityRepository.findByName(name),u1);
        assertThat(universityRepository.findByName(name),not(u2));

        assertEquals(universityRepository.findByName(name).getAdress(),trueAddress);
        assertThat(universityRepository.findByName(name).getAdress(),not(wrongAddress));

    }

}
