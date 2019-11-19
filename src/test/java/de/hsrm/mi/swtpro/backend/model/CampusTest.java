package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class CampusTest {

    private University university;
    private Campus campus;
    private List<Building> buildings;

    @Before
    public void setUp(){
        university = new University("FH Kiel","Champ de Mars, 5 Avenue Anatole France, 75007 Paris, Frankreich");
        campus = new Campus("Unter den Eichen","Platz der Republik 1, 11011 Berlin",university);

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
        buildings = new ArrayList<>();
        buildings.add(new Building("C",campus));
        buildings.add(new Building("C++",campus));
        buildings.add(new Building("C#",campus));

        campus.setBuildings(buildings);

        assertEquals(campus.getBuildings().get(0).getName(),"C");
        assertEquals(campus.getBuildings().get(1).getName(),"C++");
        assertEquals(campus.getBuildings().get(2).getName(),"C#");
    }
}
