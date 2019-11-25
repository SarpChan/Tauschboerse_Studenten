package de.hsrm.mi.swtpro.backend.model;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RoomTest {

    private Room room;
    private Building building;
    private Campus campus;

    @Before
    public void setUp(){

        University university = University.builder()
                .name("Hsrm")
                .adress("Neuschwansteinstraße 20, 87645 Schwangau")
                .build();

        campus = Campus.builder()
                .name("Unter den Eichen")
                .adress("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .university(university)
                .build();

        building = Building.builder()
                .campus(campus)
                .name("Haus D")
                .build();

        room = Room.builder()
                .id(1742)
                .number(17)
                .building(building)
                .seats(170)
                .build();

    }

    @Test
    public void whenGetBuilding_thanReturnBuilding(){assertEquals(room.getBuilding(), building);}

    @Test
    public void whenGetNumber_thanReturnNumber(){assertEquals(room.getNumber(), 17);}

    @Test
    public void whenGetSeats_thanReturnSeats(){assertEquals(room.getSeats(), 170);}

    @Test
    public  void whenGetId_thanReturnId(){assertEquals(room.getId(), 1742);}

    @Test
    public void whenSetNumber_thanSaveNumber(){
        room.setNumber(18);
        assertEquals(room.getNumber(), 18);
    }

    @Test
    public void whenSetId_thanSaveId(){
        room.setId(3232);
        assertEquals(room.getId(), 3232);
    }

    @Test
    public void whenSetSeats_thanSaveSeats(){
        room.setSeats(140);
        assertEquals(room.getSeats(), 140);
    }

    @Test
    public void whenSetBuilding_thanSaveBuilding(){
        room.setBuilding(Building.builder()
            .campus(campus)
            .name("Haus F")
            .build()
        );
        assertEquals(room.getBuilding().getName(), "Haus F");
    }


}
