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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BuildingRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    BuildingRepository buildingRepository;

    @Before
    public void setUp(){
        University university = University.builder()
                .name("Hochschule RheinMain")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .build();
        
        Campus campus = Campus.builder()
                .name("Unter den Eichen")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .university(university)
                .build();

        Room room = Room.builder()
                .number(17)
                .build();

        Building building= Building.builder()
                .name("D Gebäude")
                .campus(campus)
                .room(room)
                .build();

        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(room);
        entityManager.persist(building);
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
}
