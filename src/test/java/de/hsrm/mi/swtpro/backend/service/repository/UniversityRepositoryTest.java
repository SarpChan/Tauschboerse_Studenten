package de.hsrm.mi.swtpro.backend.service.repository;

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
public class UniversityRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    UniversityRepository universityRepository;

    @Before
    public void setUp(){
        University university = University.builder()
                .name("Hochschule RheinMain")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .build();

        entityManager.persist(university);
    }

    @Test
    public void whenFindByAddress_thenReturnUniversityList(){
        List<University> universities = universityRepository.findByAddress("Kurt-Schumacher-Ring 18, 65197 Wiesbaden");
        assertEquals(universities.get(0).getName(),"Hochschule RheinMain");
    }

    @Test
    public void whenFindByName_thenReturnUniversity(){
        University university = universityRepository.findByName("Hochschule RheinMain");
        assertEquals(university.getAddress(),"Kurt-Schumacher-Ring 18, 65197 Wiesbaden");
    }

    @Test
    public void whenFindAll_ReturnUniversityList(){
        List<University> universities = universityRepository.findAll();
        assertEquals(universities.size(),1);
    }

}
