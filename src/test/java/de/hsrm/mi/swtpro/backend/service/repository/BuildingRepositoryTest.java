package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.model.Campus;
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
        University university = new University("Hochschule RheinMain","Kurt-Schumacher-Ring 18, 65197 Wiesbaden");
        Campus campus = new Campus("Unter den Eichen","Kurt-Schumacher-Ring 18, 65197 Wiesbaden",university);
        Building building = new Building("D Gebäude",campus);
        building.setId(17);

        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
    }

    @Test
    public void whenFindByName_thenReturnBuildingList(){
        List<Building> buildings = buildingRepository.findByName("D Gebäude");
        assertEquals(buildings.get(0).getId(),17);
    }

    @Test
    public void whenFindAll_thenReturnBuildingList(){
        List<Building> buildings = buildingRepository.findAll();
        assertEquals(buildings.size(),1);
    }
}
