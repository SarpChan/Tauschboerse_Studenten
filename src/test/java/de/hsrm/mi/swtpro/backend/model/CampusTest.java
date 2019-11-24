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
                .adress("Champ de Mars, 5 Avenue Anatole France, 75007 Paris, Frankreich")
                .build();

        campus = Campus.builder()
                .name("Unter den Eichen")
                .adress("Platz der Republik 1, 11011 Berlin")
                .university(university)
                .build();
    }

    @Test
    public void whenGetName_thenReturnName(){
        assertEquals(campus.getName(),"Unter den Eichen");
    }

    @Test
    public void whenGetAddress_thenReturnAddress(){
        assertEquals(campus.getAdress(),"Platz der Republik 1, 11011 Berlin");
    }

    @Test
    public void whenGetUniversity_thenReturnUniversity(){
        assertEquals(campus.getUniversity().getName(),"FH Kiel");
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
