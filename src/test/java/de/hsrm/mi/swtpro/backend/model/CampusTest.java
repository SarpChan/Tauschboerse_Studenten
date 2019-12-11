package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class CampusTest {

    private Campus campus;

    @Before
    public void setUp(){
        University university = University.builder()
                .name("FH Kiel")
                .address("Champ de Mars, 5 Avenue Anatole France, 75007 Paris, Frankreich")
                .build();

        Building building = Building.builder()
                .id(42L)
                .build();

        campus = Campus.builder()
                .id(17L)
                .name("Unter den Eichen")
                .building(building)
                .address("Platz der Republik 1, 11011 Berlin")
                .university(university)
                .build();
    }

    @Test
    public void whenGetId_thenReturnId(){
        assertEquals(campus.getId(),17L);
    }

    @Test
    public void whenGetName_thenReturnName(){
        assertEquals(campus.getName(),"Unter den Eichen");
    }

    @Test
    public void whenGetAddress_thenReturnAddress(){
        assertEquals(campus.getAddress(),"Platz der Republik 1, 11011 Berlin");
    }

    @Test
    public void whenGetUniversity_thenReturnUniversity(){
        assertEquals(campus.getUniversity().getName(),"FH Kiel");
    }

    @Test
    public void whenGetBuilding_thenReturnBuilding(){
        assertThat(campus.getBuildings(),hasItems(
                hasProperty("id",is(42L))));
    }

    @Test
    public void whenSetId_thenSaveId(){
        assertEquals(campus.getId(),17L);
    }

    @Test
    public void whenSetName_thenSaveName(){
        campus.setName("Unter den Eichen");
        assertEquals("Unter den Eichen",campus.getName());
    }

    @Test
    public void whenSetAddress_thenSaveAddress(){
        campus.setAddress("Bahnhofstraße 50, 65185 Wiesbaden");
        assertEquals("Bahnhofstraße 50, 65185 Wiesbaden",campus.getAddress());
    }

    @Test
    public void whenSetUniversity_thenSaveUniversity(){
        University university = University.builder()
                .id(65L)
                .name("Hauptschule RheinMain")
                .build();
        campus.setUniversity(university);
        assertThat(campus.getUniversity(),hasProperty("id",is(65L)));
    }

    @Test
    public void whenSetBuildings_thenSaveBuilding(){
        Set<Building> buildings = new HashSet<>();
        buildings.add(Building.builder().name("C").campus(campus).build());
        buildings.add(Building.builder().name("C++").campus(campus).build());
        buildings.add(Building.builder().name("C#").campus(campus).build());

        campus.setBuildings(buildings);
        assertThat(campus.getBuildings(),hasItems(
                hasProperty("name",is("C")),
                hasProperty("name",is("C++")),
                hasProperty("name",is("C#"))
        ));
    }
}
