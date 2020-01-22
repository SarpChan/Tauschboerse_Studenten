package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.model.Campus;
import de.hsrm.mi.swtpro.backend.model.Room;
import de.hsrm.mi.swtpro.backend.model.University;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BuildingRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    BuildingRepository buildingRepository;

    private Campus campus;
    private Room room;

    @Before
    public void setUp(){
        University university = University.builder()
                .name("Hochschule RheinMain")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .build();

        campus = Campus.builder()
                .name("Unter den Eichen")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .university(university)
                .build();


        Building building= Building.builder()
                .name("D Gebäude")
                .campus(campus)
                .build();

        room = Room.builder()
                .number(17)
                .building(building)
                .build();



        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
        entityManager.persist(room);
    }

    @Test
    public void whenFindByName_thenReturnBuildingList(){
        List<Building> buildings = buildingRepository.findByName("D Gebäude");
        assertEquals(buildings.get(0).getCampus().getName(),"Unter den Eichen");
    }

    @Test
    public void whenFindAll_thenReturnBuildingList(){
        List<Building> buildings = buildingRepository.findAll();
        assertEquals(buildings.size(),1);
    }

    @Test
    public void whenFindByCampus_thenReturnBuildingList(){
        assertTrue(buildingRepository.findByCampus(campus).isPresent());
        assertThat(buildingRepository.findByCampus(campus).get(),
                hasProperty("name", is("D Gebäude")
        ));
    }

    @Test
    public void whenFindByRooms_thenReturnBuildingList(){
        assertThat(buildingRepository.findByRooms(room),hasItem(
                hasProperty("name", is("D Gebäude"))
        ));
    }
}
