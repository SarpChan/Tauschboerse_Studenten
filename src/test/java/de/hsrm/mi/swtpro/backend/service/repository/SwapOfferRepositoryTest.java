package de.hsrm.mi.swtpro.backend.service.repository;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.EntityManager;

import de.hsrm.mi.swtpro.backend.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SwapOfferRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    SwapOfferRepository swapOfferRepository;

    private Student student;
    private Group fromGroup;
    private Group toGroup;
    private long id;

    @Before
    public void setUp() {
        User user = User.builder()
                .firstName("Lukas")
                .lastName("wede")
                .loginName("w001")
                .password("password")
                .build();

        Term term = Term.builder()
                .startDate(new Date(System.currentTimeMillis()))
                .endDate(new Date(System.currentTimeMillis()))
                .period(1)
                .build();

        StudyProgram studyProg = StudyProgram.builder()
                .title("test")
                .degree("E")
                .build();

        ExamRegulation examReg = ExamRegulation.builder()
                .date(new Date(System.currentTimeMillis()))
                .studyProgram(studyProg)
                .build();

        student = Student.builder().user(user)
                .mail("3@e.de")
                .enrollmentNumber(10)
                .examRegulation(examReg)
                .enrolmentTerm(term)
                .build();

        Lecturer lecturer = Lecturer.builder()
                .build();

        University university = University.builder()
                .name("Hochschule RheinMain")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .build();

        Campus campus = Campus.builder()
                .name("Unter den Eichen")
                .address("Kurt-Schumacher-Ring 18, 65197 Wiesbaden")
                .university(university)
                .build();


        Building building = Building.builder()
                .name("D Gebäude")
                .campus(campus)
                .build();

        Room room = Room.builder()
                .number(17)
                .building(building)
                .build();

        Course course = Course.builder()
                .owner(user)
                .title("A")
                .build();

        CourseComponent courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .exam("Prüfung")
                .build();

        toGroup = Group.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .startTime(LocalTime.of(14, 0))
                .endTime(LocalTime.of(15, 0))
                .slots(17)
                .room(room)
                .term(term)
                .lecturer(lecturer)
                .student(student)
                .courseComponent(courseComponent)
                .groupChar('A')
                .build();

        fromGroup = Group.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .startTime(LocalTime.of(14, 0))
                .endTime(LocalTime.of(15, 0))
                .slots(17)
                .room(room)
                .term(term)
                .lecturer(lecturer)
                .student(student)
                .courseComponent(courseComponent)
                .groupChar('B')
                .build();

        SwapOffer swapOffer = SwapOffer.builder()
                .toGroup(toGroup)
                .fromGroup(fromGroup)
                .student(student)
                .date(new Timestamp(564738))
                .build();

        entityManager.persist(user);
        entityManager.persist(course);
        entityManager.persist(courseComponent);
        entityManager.persist(lecturer);
        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
        entityManager.persist(room);
        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(term);
        entityManager.persist(student);
        entityManager.persist(fromGroup);
        entityManager.persist(toGroup);
        entityManager.persist(swapOffer);
        id = swapOffer.getId();
    }

    @Test
    public void whenFindAll_thenReturnSwapOfferList() {
        assertThat(swapOfferRepository.findAll(), hasItem(
                hasProperty("date", is(new Timestamp(564738)))
        ));
    }

    @Test
    public void whenFindById_thenReturnSwapOffer() {
        assertTrue(swapOfferRepository.findById(id).isPresent());
        assertThat(swapOfferRepository.findById(id).get(),
                hasProperty("date", is(new Timestamp(564738))));
    }

    @Test
    public void whenFindByToGroup_thenReturnSwapOfferList() {
        assertThat(swapOfferRepository.findByToGroup(toGroup), hasItem(
                hasProperty("date", is(new Timestamp(564738)))
        ));
    }

    @Test
    public void whenFindByFromGroup_thenReturnSwapOfferList() {
        assertThat(swapOfferRepository.findByFromGroup(fromGroup), hasItem(
                hasProperty("date", is(new Timestamp(564738)))
        ));
    }

    @Test
    public void whenFindByDate_thenReturnSwapOfferList() {
        assertThat(swapOfferRepository.findByDate(new Timestamp(564738)),
                hasItem(hasProperty("date", is(new Timestamp(564738)))));
    }
}
