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
public class BuildingTest {

    private University university;
    private Campus campus;
    private Building building;

    @Before
    public void setUp(){

        university = University.builder()
                .name("HSRM")
                .adress("Neuschwansteinstraße 20, 87645 Schwangau")
                .build();

        campus = Campus.builder()
                .name("Unter den Eichen")
                .adress("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .university(university)
                .build();

        Set<Room> rooms = new HashSet<>();
        rooms.add(Room.builder().number(17).build());
        rooms.add(Room.builder().number(42).build());
        rooms.add(Room.builder().number(14).build());

        building = Building.builder()
                .name("D Gebäude")
                .campus(campus)
                .rooms(rooms)
                .build();


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
        assertThat(building.getRooms(),containsInAnyOrder(
                hasProperty("number",is(14)),
                hasProperty("number",is(17)),
                hasProperty("number",is(42))
        ));
    }

    @Test
    public void whenSetCampus_thanSaveCampus(){
        building.setCampus(Campus.builder()
                .name("RüppelCampus")
                .adress("RüppelCampus")
                .university(university)
                .build()
        );

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
        building.getRooms().add(Room.builder().number(21).build());
        assertThat(building.getRooms(),hasItem(hasProperty("number",is(21))));
    }
}
