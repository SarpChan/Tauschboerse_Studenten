package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Lecturer;
import de.hsrm.mi.swtpro.backend.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LectureRepositoryTest {

    private Group group;
    private User user;
    private long id;

    @Autowired
    EntityManager entityManager;
    @Autowired
    LectureRepository lectureRepository;

    @Before
    public void setUp(){

        user = User.builder()
                .loginName("123489")
                .build();

        Lecturer lecturer = Lecturer.builder()
                .priviledge(10)
                .user(user)
                .build();

        group = Group.builder()
                .lecturer(lecturer)
                .slots(17)
                .build();

        entityManager.persist(user);
        entityManager.persist(lecturer);
        entityManager.persist(group);
        id = lecturer.getId();
    }

    @Test
    public void whenFindById_thenReturnLecture(){
        assertTrue(lectureRepository.findById(id).isPresent());
        assertThat(lectureRepository.findById(id).get(),
                hasProperty("priviledge",is(10)));
    }

    @Test
    public void whenFindAll_thenReturnLectureList(){
        assertThat(lectureRepository.findAll(), hasItem(
                hasProperty("priviledge",is(10))
        ));
    }

    @Test
    public void whenFindByPriviledge_thenReturnLectureList(){
        assertThat(lectureRepository.findByPriviledge(10), hasItem(
                hasProperty("id",is(id))
        ));
    }

    @Test
    public void whenFindByGroups_thenReturnLectureList(){
        assertThat(lectureRepository.findByGroups(group),hasItem(
                hasProperty("priviledge",is(10))
        ));
    }

    @Test
    public void whenFindByUser_thenReturnLectureList(){
        assertThat(lectureRepository.findByUser(user),hasItem(
                hasProperty("priviledge",is(10))
        ));
    }

}
