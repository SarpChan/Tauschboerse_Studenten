package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class BuildingTest {

    private University university;
    private Campus campus;
    private Building building;
    private List<Room> rooms;

    @Before
    public void setUp(){
        university = new University("Hochschule RheinMain","Kurt-Schumacher-Ring 18, 65197 Wiesbaden");
        campus = new Campus("Unter den Eichen","Kurt-Schumacher-Ring 18, 65197 Wiesbaden",university);
        building = new Building("D Gebäude",campus);

        rooms = new ArrayList<>();
        rooms.add(new Room(17));
        rooms.add(new Room(42));
        rooms.add(new Room(14));

        building.setRooms(rooms);
        building.setId(17);
    }

    @Test
    public void whenGetCampus_thanReturnCampus(){
        assertEquals(building.getCampus(),campus);
    }

    @Test
    public void whenGetName_thanReturnName(){
        assertEquals(building.getName(),"D Gebäude");
    }

    @Test
    public void whenGetId_thanReturnId(){
        assertEquals(building.getId(),17);
    }

    @Test
    public void whenGetRooms_thanReturnRoomList(){
        assertEquals(building.getRooms().get(0).getNumber(),17);
        assertEquals(building.getRooms().get(1).getNumber(),42);
        assertEquals(building.getRooms().get(2).getNumber(),14);
    }

    @Test
    public void whenSetCampus_thanSaveCampus(){
        building.setCampus(new Campus("RüppelCampus","221B Baker, London",university));
        assertEquals(building.getCampus().getName(),"RüppelCampus");
    }

    @Test
    public void whenSetName_thanSaveName(){
        building.setName("Lauchtower");
        assertEquals(building.getName(),"Lauchtower");
    }

    @Test
    public void whenSetId_thanSaveId(){
        building.setId(42);
        assertEquals(building.getId(),42);
    }

    @Test
    public void whenAddRoom_thanSaveRoom(){
        building.getRooms().add(new Room(21));
        assertEquals(building.getRooms().get(3).getNumber(),21);
    }
}
