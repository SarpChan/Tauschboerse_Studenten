package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Room;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    RoomRepository roomRepository;

    private Building building;
    private Group group;
    private long id;

    @Before
    public void setUp(){

        building = Building.builder()
                .name("Haus D")
                .build();



        Room room = Room.builder()
                .seats(170)
                .number(28)
                .building(building)
                .build();

        group = Group.builder()
                .room(room)
                .build();

        entityManager.persist(building);
        entityManager.persist(room);
        entityManager.persist(group);
        id = room.getId();
    }

    @Test
    public void whenFindAll_thanReturnRoomList(){
        List<Room> rooms = roomRepository.findAll();
        assertEquals(rooms.size(), 1);
    }

    @Test
    public void whenFindById_thenReturnRoomL(){
        assertTrue(roomRepository.findById(id).isPresent());
        assertThat(roomRepository.findById(id).get(),
                hasProperty("number", is(28)));
    }

    @Test
    public void whenFindByNumber_thanReturnRoomList(){
        assertThat(roomRepository.findByNumber(28), hasItem(hasProperty("number", is(28))));
    }

    @Test
    public void whenFindBySeats_thanReturnRoomList(){
        assertThat(roomRepository.findBySeats(170), hasItem(hasProperty("seats", is(170))));
    }

    @Test
    public void whenFindByBuilding_thanReturnRoomList(){
        assertThat(roomRepository.findByBuilding(building), hasItem(hasProperty("number", is(28))));
    }

    @Test
    public void whenFindByGroup_thenReturnRoomList(){
        assertThat(roomRepository.findByGroups(group),hasItem(
                hasProperty("number", is(28))
        ));
    }

}
