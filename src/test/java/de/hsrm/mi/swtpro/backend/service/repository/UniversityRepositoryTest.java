package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Campus;
import de.hsrm.mi.swtpro.backend.model.FieldOfStudy;
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
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UniversityRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    UniversityRepository universityRepository;


    private FieldOfStudy fieldOfStudy;
    private Campus campus;
    private long id;

    @Before
    public void setUp(){

        University university = University.builder()
                .name("Hochschule RheinMain")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .build();

        campus = Campus.builder()
                .name("unter den Leichen")
                .university(university)
                .address("Unter den Eichen, 65197 Wiesbaden")
                .build();

        fieldOfStudy = FieldOfStudy.builder()
                .title("field of Study")
                .university(university)
                .build();

        entityManager.persist(university);
        entityManager.persist(fieldOfStudy);
        entityManager.persist(campus);

        id = university.getId();
    }

    @Test
    public void whenFindById_thenReturnUniversity(){
        assertTrue(universityRepository.findById(id).isPresent());
        assertThat(universityRepository.findById(id).get(),
                hasProperty("address",is("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")));
    }

    @Test
    public void whenFindByAddress_thenReturnUniversityList(){
        List<University> universities = universityRepository.findByAddress("Kurt-Schumacher-Ring 18, 65197 Wiesbaden");
        assertEquals(universities.get(0).getName(),"Hochschule RheinMain");
    }

    @Test
    public void whenFindByName_thenReturnUniversity(){
        assertTrue(universityRepository.findByName("Hochschule RheinMain").isPresent());
        University university = universityRepository.findByName("Hochschule RheinMain").get();
        assertEquals(university.getAddress(),"Kurt-Schumacher-Ring 18, 65197 Wiesbaden");
    }

    @Test
    public void whenFindAll_ReturnUniversityList(){
        List<University> universities = universityRepository.findAll();
        assertEquals(universities.size(),1);
    }

    @Test
    public void whenFindByFieldOfStudy_thenReturnUniversityList(){
        assertThat(universityRepository.findByFieldsOfStudy(fieldOfStudy),hasItem(
                hasProperty("address",is("Kurt-Schumacher-Ring 18, 65197 Wiesbaden"))
        ));
    }

    @Test
    public void whenFindByCampus_thenReturnUniversityList(){
        assertThat(universityRepository.findByCampuses(campus),hasItem(
                hasProperty("address",is("Kurt-Schumacher-Ring 18, 65197 Wiesbaden"))
        ));
    }

}
