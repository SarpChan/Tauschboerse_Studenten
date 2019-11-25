package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class UniversityTest {

    private University university;

    @Before
    public void setUp(){

        university = University.builder()
                .name("Hochschule Geisenheim")
                .adress("Von-Lade-Straße 1, 65366 Geisenheim")
                .id(17)
                .build();
    }

    @Test
    public void whenGetId_ReturnId(){assertEquals(university.getId(),17);}

    @Test
    public void whenGetName_ReturnName(){assertEquals(university.getName(), "Hochschule Geisenheim");}

    @Test
    public void whenGetAdress_ReturnAdress(){assertEquals(university.getAdress(), "Von-Lade-Straße 1, 65366 Geisenheim");}

    @Test
    public void whenSetId_SaveId(){
        university.setId(18);
        assertEquals(university.getId(), 18);
    }

    @Test
    public void whenSetName_SaveName(){
        university.setName("Goethe Universität");
        assertEquals(university.getName(), "Goethe Universität");
    }

    @Test
    public void whenSetAdress_SaveAdress(){
        university.setAdress("Flachwitzweg 17, 0815 Bielefeld");
        assertEquals(university.getAdress(),"Flachwitzweg 17, 0815 Bielefeld");
    }

    @Test
    public void whenSetCampus_SaveCampus(){
        Set<Campus> campi = new HashSet<>();
        campi.add(Campus.builder().name("Neben den Eichen").build());
        campi.add(Campus.builder().name("Über den Eichen").build());
        campi.add(Campus.builder().name("Unter den Eichen").build());

        university.setCampus(campi);

        assertThat(university.getCampus(), hasItems(
                hasProperty("name",is("Neben den Eichen")),
                hasProperty("name",is("Über den Eichen")),
                hasProperty("name",is("Unter den Eichen"))

        ));

    }

    @Test
    public void whenSetFieldOfStudy_SaveFieldOfStudy(){
        Set<FieldOfStudy> fieldOfStudies = new HashSet<>();
        fieldOfStudies.add(FieldOfStudy.builder().title("Informatik").build());
        fieldOfStudies.add(FieldOfStudy.builder().title("Maschinenbau").build());
        fieldOfStudies.add(FieldOfStudy.builder().title("Körperhygiene").build());

        university.setFieldOfStudies(fieldOfStudies);

        assertThat(university.getFieldOfStudies(), hasItems(
                hasProperty("title", is("Informatik")),
                hasProperty("title", is("Maschinenbau")),
                hasProperty("title", is("Körperhygiene"))
        ));
    }


}
