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

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CampusRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    CampusRepository campusRepository;

    private long id;
    private Building building;
    private University university;

    @Before
    public void setUp(){

        university = University.builder()
                .name("HSRM")
                .address("KSR 4,91233 Wiesbaden")
                .build();

        Campus campus = Campus.builder()
                .name("unter den Eichen")
                .university(university)
                .address("Unter den Eichen 5, 12389 Wiesbaden")
                .build();

        building= Building.builder()
                .name("D Gebäude")
                .campus(campus)
                .build();

        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
        id = campus.getId();
    }

    @Test
    public void whenFindByAll_thenReturnCampusList(){
        assertEquals(1,campusRepository.findAll().size());
    }

    @Test
    public void whenFindById_thenReturnCampus(){
        assertTrue(campusRepository.findById(id).isPresent());
        assertThat(campusRepository.findById(id).get(),hasProperty("name",is("unter den Eichen")));
    }

    @Test
    public void whenFindByName_thenReturnCampusList(){
        assertEquals("Unter den Eichen 5, 12389 Wiesbaden",
                campusRepository.findByName("unter den Eichen").get(0).getAddress());
    }

    @Test
    public void whenFindByAddress_thenReturnCampus(){
        assertTrue(campusRepository.findByAddress("Unter den Eichen 5, 12389 Wiesbaden").isPresent());
        assertEquals("unter den Eichen",
                campusRepository.findByAddress("Unter den Eichen 5, 12389 Wiesbaden").get().getName());
    }

    @Test
    public void whenFindByBuildings_thenReturnCampusList(){
        assertTrue(campusRepository.findByBuildings(building).isPresent());
        assertThat(campusRepository.findByBuildings(building).get(),
                hasProperty("address",is("Unter den Eichen 5, 12389 Wiesbaden"))
        );
    }

    @Test
    public void whenFindByUniversity_thenReturnCampusList(){
        assertThat(campusRepository.findByUniversity(university),hasItem(
                hasProperty("address",is("Unter den Eichen 5, 12389 Wiesbaden"))
        ));
    }
}
