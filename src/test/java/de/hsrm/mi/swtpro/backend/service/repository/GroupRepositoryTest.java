package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    GroupRepository groupRepository;

    private Room room;
    private long id;
    private StudentPrioritizesGroup studentPrioritizesGroup;
    private Student student;
    private Term term;
    private Lecturer lecturer;
    private CourseComponent courseComponent;

    @Before
    public void setUp() {

        User user = User.builder()
                .firstName("Lukas")
                .lastName("wede")
                .loginName("w001")
                .password("password")
                .build();

        Course course = Course.builder()
                .owner(user)
                .title("A")
                .build();

        courseComponent = CourseComponent.builder()
                .course(course)
                .type(CourseType.LECTURE)
                .exam("Prüfung")
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

        room = Room.builder()
                .number(17)
                .building(building)
                .build();

        StudyProgram studyProg = StudyProgram.builder()
                .title("test")
                .degree("E")
                .build();

        ExamRegulation examReg = ExamRegulation.builder()
                .date(new Date(System.currentTimeMillis()))
                .studyProgram(studyProg)
                .build();

        term = Term.builder()
                .startDate(new Date(System.currentTimeMillis()))
                .endDate(new Date(System.currentTimeMillis()))
                .period(1)
                .build();

        student = Student.builder().user(user)
                .mail("3@e.de")
                .enrollmentNumber(10)
                .examRegulation(examReg)
                .enrolmentTerm(term)
                .build();

        lecturer = Lecturer.builder()
                .build();

        Group group = Group.builder()
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

        studentPrioritizesGroup = StudentPrioritizesGroup.builder()
                .priority(10)
                .student(student)
                .group(group)
                .build();


        entityManager.persist(user);
        entityManager.persist(studyProg);
        entityManager.persist(examReg);
        entityManager.persist(term);
        entityManager.persist(course);
        entityManager.persist(courseComponent);
        entityManager.persist(student);
        entityManager.persist(lecturer);
        entityManager.persist(university);
        entityManager.persist(campus);
        entityManager.persist(building);
        entityManager.persist(room);
        entityManager.persist(group);
        entityManager.persist(studentPrioritizesGroup);
        id = group.getId();
    }

    @Test
    public void whenFindAll_thenReturnGroupList() {
        assertThat(groupRepository.findAll(), hasItem(
                hasProperty("slots", is(17))
        ));
    }

    @Test
    public void whenFindById_thenReturnGroup() {
        assertTrue(groupRepository.findById(id).isPresent());
        assertThat(groupRepository.findById(id).get(),
                hasProperty("slots", is(17)
                ));
    }

    @Test
    public void whenFindBySlots_thenReturnGroupList() {
        assertThat(groupRepository.findBySlots(17), hasItem(
                hasProperty("id", is(id)
                )));
    }

    @Test
    public void whenFindByDayOfWeek_thenReturnGroupList() {
        assertThat(groupRepository.findByDayOfWeek(DayOfWeek.MONDAY),
                hasItem(hasProperty("slots", is(17))));
    }

    @Test
    public void whenFindByStartTime_thenReturnGroupList() {
        assertThat(groupRepository.findByStartTime(LocalTime.of(14, 0)), hasItem(
                hasProperty("slots", is(17))
        ));
    }

    @Test
    public void whenFindByEndTime_thenReturnGroupList() {
        assertThat(groupRepository.findByEndTime(LocalTime.of(15, 0)), hasItem(
                hasProperty("slots", is(17))
        ));
    }

    @Test
    public void whenFindByRoom_thenReturnGroupList() {
        assertThat(groupRepository.findByRoom(room),
                hasItem(hasProperty("slots", is(17))));
    }

    @Test
    public void whenFindByStudents_thenReturnGroupList() {
        assertThat(groupRepository.findByStudents(student), hasItem(
                hasProperty("slots", is(17))
        ));
    }

    @Test
    public void whenFindByPrioritizeGroups_thenReturnGroupList() {
        assertThat(groupRepository.findByPrioritizeGroups(studentPrioritizesGroup), hasItem(
                hasProperty("slots", is(17))
        ));
    }

    @Test
    public void whenFindByTerms_thenReturnGroupList() {
        assertThat(groupRepository.findByTerm(term), hasItem(
                hasProperty("slots", is(17))
        ));
    }

    @Test
    public void whenFindByLecture_thenReturnGroupList() {
        assertThat(groupRepository.findByLecturer(lecturer), hasItem(
                hasProperty("slots", is(17))
        ));
    }

    @Test
    public void whenFindByCourseComponent_thenReturnGroupList() {
        assertThat(groupRepository.findByCourseComponent(courseComponent), hasItem(
                hasProperty("slots", is(17))
        ));
    }

    @Test
    public void whenFindByGroupChar_thenReturnGroupList() {
        assertThat(groupRepository.findByGroupChar('A'), hasItem(
                hasProperty("slots", is(17))
        ));
    }

}
