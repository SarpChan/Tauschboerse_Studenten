package de.hsrm.mi.swtpro.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.service.repository.StudyProgramRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.util.HashSet;


/**
 * The JSON Generator is used to generate a JSON file with sample entitys with all their relations for
 * the whole model.
 */
@Component
public class JSONGenerator {

    @Autowired
    EntityManagerFactory emf;
    private EntityManager entityManager;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    StudyProgramRepository studyProgramRepository;


    public File createJSON() {
        entityManager = emf.createEntityManager();
        University uni = University.builder().name("Hochschule RheinMain").address("Kurt-Schuhmacher-Ring 18").build();
        entityManager.persist(uni);
        Campus ude = Campus.builder().name("Unter den Eichen").address("Unter den Eichen 6").university(uni).build();
        entityManager.persist(ude);
        Building dBuilding = Building.builder().name("Gebäude D").campus(ude).build();
        entityManager.persist(dBuilding);
        Room r14 = Room.builder().number(14).seats(60).building(dBuilding).build();
        entityManager.persist(r14);
        Term ws1920 = Term.builder().startDate(Date.valueOf("2019-10-01")).endDate(Date.valueOf("2020-03-31")).period(1).build();
        entityManager.persist(ws1920);
        ExamRegulation po2017 = ExamRegulation.builder().date(Date.valueOf("2017-10-01")).build();
        entityManager.persist(po2017);
        User testUser = User.builder().firstName("Test").lastName("User").loginName("testUser").password("test").build();
        entityManager.persist(testUser);
        User passedUser = User.builder().firstName("Passed").lastName("Student").loginName("passedUser").password("test").build();
        entityManager.persist(passedUser);
        Student testStudent = Student.builder().user(testUser).enrollmentNumber(123456)
                .enrolmentTerm(ws1920).mail("test@test.com").examRegulation(po2017).build();
        entityManager.persist(testStudent);
        Student passedStudent = Student.builder().user(passedUser).enrollmentNumber(987654)
                .enrolmentTerm(ws1920).mail("passed@test.com").examRegulation(po2017).build();
        entityManager.persist(passedStudent);
        Lecturer testLecturer = Lecturer.builder().priviledge(1).user(testUser).build();
        entityManager.persist(testLecturer);
        StudyProgram medieninformatik = StudyProgram.builder().title("Medieninformatik").degree("Bachlor").build();
        entityManager.persist(medieninformatik);
        FieldOfStudy dcsm = FieldOfStudy.builder().title("DCSM").build();
        entityManager.persist(dcsm);
        Curriculum curriculum = Curriculum.builder().examRegulation(po2017).termPeriod(1).build();
        entityManager.persist(curriculum);
        Module mProgrammieren3 = Module.builder().title("Programmieren 3").creditPoints(5).period(1).build();
        entityManager.persist(mProgrammieren3);
        ModuleInCurriculum moduleInCurriculum = ModuleInCurriculum.builder().curriculum(curriculum).module(mProgrammieren3).termPeriod(1).build();
        entityManager.persist(moduleInCurriculum);
        Course cProgrammieren3 = Course.builder().title("Programmieren 3").owner(testUser).build();
        entityManager.persist(cProgrammieren3);
        CourseComponent prog3V = CourseComponent.builder().course(cProgrammieren3).creditPoints(5).type(CourseType.LECTURE).exam("Klausur").build();
        entityManager.persist(prog3V);
        CourseComponent prog3P = CourseComponent.builder().course(cProgrammieren3).exam("Praktische Tätigkeit").type(CourseType.PRACTICE).creditPoints(0).build();
        entityManager.persist(prog3P);
        Group prog3Vgroup = Group.builder().lecturer(testLecturer).slots(100).room(r14).term(ws1920).dayOfWeek((DayOfWeek.MONDAY)).groupChar('A')
                .courseComponent(prog3V).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        entityManager.persist(prog3Vgroup);
        Group prog3PgroupA = Group.builder().lecturer(testLecturer).slots(20).room(r14).term(ws1920).dayOfWeek((DayOfWeek.TUESDAY)).groupChar('A')
                .courseComponent(prog3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        entityManager.persist(prog3PgroupA);
        Group prog3PgroupB = Group.builder().lecturer(testLecturer).slots(20).room(r14).term(ws1920).dayOfWeek((DayOfWeek.WEDNESDAY)).groupChar('B')
                .courseComponent(prog3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        entityManager.persist(prog3PgroupB);
        Group prog3PgroupC = Group.builder().lecturer(testLecturer).slots(20).room(r14).term(ws1920).dayOfWeek((DayOfWeek.THURSDAY)).groupChar('C')
                .courseComponent(prog3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        entityManager.persist(prog3PgroupC);
        Group prog3PgroupD = Group.builder().lecturer(testLecturer).slots(20).room(r14).term(ws1920).dayOfWeek((DayOfWeek.FRIDAY)).groupChar('D')
                .courseComponent(prog3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        entityManager.persist(prog3PgroupD);
        StudentAttendsCourse studentAttendsCourse = StudentAttendsCourse.builder().student(testStudent).course(cProgrammieren3).term(ws1920).build();
        entityManager.persist(studentAttendsCourse);
        StudentPassedExam studentPassedExam = StudentPassedExam.builder().courseComponent(prog3P).student(passedStudent).grade(4.0f).build();
        StudentPassedExam studentPassedExam2 = StudentPassedExam.builder().courseComponent(prog3V).student(passedStudent).grade(4.0f).build();
        StudentPrioritizesGroup studentPrioritizesGroup = StudentPrioritizesGroup.builder().group(prog3PgroupA).student(testStudent).priority(1).build();
        SwapOffer swapOffer = SwapOffer.builder().student(testStudent).date(Timestamp.from(Instant.now())).fromGroup(prog3PgroupA).toGroup(prog3PgroupB).build();
        entityManager.persist(swapOffer);

        uni.setCampuses(new HashSet<>());
        uni.setFieldsOfStudy(new HashSet<>());
        ude.setBuildings(new HashSet<>());
        dcsm.setStudyPrograms(new HashSet<>());
        dBuilding.setRooms(new HashSet<>());
        medieninformatik.setExamRegulations(new HashSet<>());
        medieninformatik.setFieldsOfStudy(new HashSet<>());
        po2017.setCurriculums(new HashSet<>());
        po2017.setStudents(new HashSet<>());
        ws1920.setCourses(new HashSet<>());
        curriculum.setModulesInCurriculum(new HashSet<>());
        mProgrammieren3.setCourses(new HashSet<>());
        mProgrammieren3.setModulesInCurriculum(new HashSet<>());
        cProgrammieren3.setModules(new HashSet<>());
        cProgrammieren3.setTerms(new HashSet<>());
        cProgrammieren3.setCourseComponents(new HashSet<>());
        prog3V.setGroups(new HashSet<>());
        prog3P.setGroups(new HashSet<>());
        prog3Vgroup.setStudents(new HashSet<>());
        prog3PgroupA.setStudents(new HashSet<>());
        prog3PgroupB.setStudents(new HashSet<>());
        prog3PgroupC.setStudents(new HashSet<>());
        prog3PgroupD.setStudents(new HashSet<>());

        testStudent.setAttendCourses(new HashSet<>());
        testStudent.getAttendCourses().add(studentAttendsCourse);
        testStudent.setPrioritizeGroups(new HashSet<>());
        testStudent.getPrioritizeGroups().add(studentPrioritizesGroup);

        passedStudent.setPassedExams(new HashSet<>());
        passedStudent.getPassedExams().add(studentPassedExam);
        passedStudent.getPassedExams().add(studentPassedExam2);

        uni.getCampuses().add(ude);
        uni.getFieldsOfStudy().add(dcsm);

        ude.getBuildings().add(dBuilding);

        dBuilding.getRooms().add(r14);

        dcsm.setUniversity(uni);
        dcsm.getStudyPrograms().add(medieninformatik);

        medieninformatik.getExamRegulations().add(po2017);
        medieninformatik.getFieldsOfStudy().add(dcsm);

        ws1920.addCourse(cProgrammieren3);

        po2017.getCurriculums().add(curriculum);
        po2017.getStudents().add(testStudent);
        po2017.setStudyProgram(medieninformatik);

        curriculum.getModulesInCurriculum().add(moduleInCurriculum);
        curriculum.setExamRegulation(po2017);

        mProgrammieren3.addCourse(cProgrammieren3);
        mProgrammieren3.getModulesInCurriculum().add(moduleInCurriculum);

        cProgrammieren3.addCourseComponent(prog3V);
        cProgrammieren3.addCourseComponent(prog3P);
        cProgrammieren3.getModules().add(mProgrammieren3);
        cProgrammieren3.getTerms().add(ws1920);

        prog3V.addGroup(prog3Vgroup);
        prog3P.addGroup(prog3PgroupA);
        prog3P.addGroup(prog3PgroupB);
        prog3P.addGroup(prog3PgroupC);
        prog3P.addGroup(prog3PgroupD);

        prog3V.setExam("Klausur");
        prog3P.setExam("Praktische Tätigkeit");

        prog3Vgroup.addStudent(testStudent);
        prog3PgroupA.addStudent(testStudent);
        prog3PgroupB.addStudent(testStudent);
        prog3PgroupC.addStudent(testStudent);
        prog3PgroupD.addStudent(testStudent);



        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("hsrm_medieninformatik.json");
        try {
            objectMapper.writeValue(file, uni);

        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("JSON new File Error");
        }
        return file;


    }
}
