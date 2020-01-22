package de.hsrm.mi.swtpro.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.service.repository.StudyProgramRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UniversityRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;


/**
 * The JSON ServiceGenerator is used to generate a JSON file with sample entitys with all their relations for
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

    @Autowired
    PasswordEncoder passwordEncoder;


    public File createJSON() {
        entityManager = emf.createEntityManager();
        
        University uni = University.builder().name("Hochschule RheinMain").address("Kurt-Schuhmacher-Ring 18").build();
        //entityManager.persist(uni);

        Campus ude = Campus.builder().name("Unter den Eichen").address("Unter den Eichen 6").university(uni).build();
        //entityManager.persist(ude);


        // Building

        Building dBuilding = Building.builder().name("D").campus(ude).build();
        //entityManager.persist(dBuilding);
        Building cBuilding = Building.builder().name("C").campus(ude).build();
        //entityManager.persist(cBuilding);


        // Rooms

        Room r11 = Room.builder().number(11).seats(100).building(dBuilding).build();
        //entityManager.persist(r11);
        Room r12 = Room.builder().number(12).seats(20).building(dBuilding).build();
        //entityManager.persist(r12);
        Room r13 = Room.builder().number(13).seats(20).building(dBuilding).build();
        //entityManager.persist(r13);
        Room r14 = Room.builder().number(14).seats(60).building(dBuilding).build();
        //entityManager.persist(r14);
        Room r17 = Room.builder().number(17).seats(80).building(dBuilding).build();
        //entityManager.persist(r17);
        Room r23 = Room.builder().number(23).seats(25).building(cBuilding).build();
        //entityManager.persist(r23);
        Room r25 = Room.builder().number(25).seats(40).building(cBuilding).build();
        //entityManager.persist(r25);


        // Terms

        Term ws1920 = Term.builder().startDate(Date.valueOf("2019-10-01")).endDate(Date.valueOf("2020-03-31")).period(1).build();
        //entityManager.persist(ws1920);
        Term ss2019 = Term.builder().startDate(Date.valueOf("2019-04-01")).endDate(Date.valueOf("2019-08-31")).period(2).build();
        //entityManager.persist(ss2019);


        // Fields of Study

        FieldOfStudy informatik = FieldOfStudy.builder().title("Informatik").university(uni).build();
        //entityManager.persist(informatik);


        // Study Program

        StudyProgram medieninformatik = StudyProgram.builder().title("Medieninformatik").degree("Bachelor").fieldOfStudy(informatik).build();
        //entityManager.persist(medieninformatik);
        StudyProgram angewandteinformatik = StudyProgram.builder().title("Angewandte Informatik").degree("Bachelor").fieldOfStudy(informatik).build();
        //entityManager.persist(angewandteinformatik);


        // PO

        ExamRegulation po2017 = ExamRegulation.builder().date(Date.valueOf("2017-10-01")).studyProgram(medieninformatik).build();
        //entityManager.persist(po2017);
        ExamRegulation po2016 = ExamRegulation.builder().date(Date.valueOf("2016-10-01")).studyProgram(angewandteinformatik).build();
        //entityManager.persist(po2016);


        // User

        User wweitz = User.builder().firstName("Wolfgang").lastName("Weitz").loginName("wweitz").password("wweitz").build();
        //entityManager.persist(wweitz);
        User jberdux = User.builder().firstName("Joerg").lastName("Berdux").loginName("jberdux").password("jberdux").build();
        //entityManager.persist(jberdux);
        User rreichenauer = User.builder().firstName("Roland").lastName("Reichenauer").loginName("rreichenauer").password("rreichenauer").build();
        //entityManager.persist(rreichenauer);
        User uschwaneke = User.builder().firstName("Ulrich").lastName("Schwaneke").loginName("uschwaneke").password("uschwaneke").build();
        //entityManager.persist(uschwaneke);

        User nahlers = User.builder().firstName("Nicklas").lastName("Ahlers").loginName("nahlers").password("nahlers").build();
        //entityManager.persist(nahlers);
        User vesper = User.builder().firstName("Vanessa").lastName("Esper").loginName("vesper").password(passwordEncoder.encode("vesper")).build();
        //entityManager.persist(vesper);
        User tthiel = User.builder().firstName("Tobi").lastName("Thiel").loginName("tthiel").password("tthiel").build();
        //entityManager.persist(tthiel);
        User jwirt = User.builder().firstName("Julia").lastName("Wirt").loginName("jwirt").password("jwirt").build();
        //entityManager.persist(jwirt);
        User jmuel = User.builder().firstName("Julius").lastName("Muel").loginName("jmuel").password("jmuel").build();
        //entityManager.persist(jmuel);
        User ydeuster = User.builder().firstName("Yen").lastName("Deuster").loginName("ydeuster").password("ydeuster").build();
        //entityManager.persist(ydeuster);


        // Roles

        Student stu_esper = Student.builder().user(vesper).enrolmentNumber(1076576)
                .enrolmentTerm(ss2019).mail("esper@mail.com").examRegulation(po2017).build();
        //entityManager.persist(stu_esper);
        Student stu_ahlers = Student.builder().user(nahlers).enrolmentNumber(1076688)
                .enrolmentTerm(ss2019).mail("ahlers@mail.com").examRegulation(po2017).build();
        //entityManager.persist(stu_ahlers);
        Student stu_deuster = Student.builder().user(ydeuster).enrolmentNumber(1075577)
                .enrolmentTerm(ss2019).mail("deuster@mail.com").examRegulation(po2017).build();
        //entityManager.persist(stu_deuster);
        Student stu_wirt = Student.builder().user(jwirt).enrolmentNumber(1078833)
                .enrolmentTerm(ss2019).mail("wirt@mail.com").examRegulation(po2017).build();
        //entityManager.persist(stu_wirt);
        Student stu_thiel = Student.builder().user(tthiel).enrolmentNumber(1074411)
                .enrolmentTerm(ss2019).mail("thiel@mail.com").examRegulation(po2017).build();
        //entityManager.persist(stu_thiel);

        Lecturer lec_weitz = Lecturer.builder().priviledge(1).user(wweitz).build();
        //entityManager.persist(lec_weitz);
        Lecturer lec_berdux = Lecturer.builder().priviledge(1).user(jberdux).build();
        //entityManager.persist(lec_berdux);
        Lecturer lec_reichenauer = Lecturer.builder().priviledge(1).user(rreichenauer).build();
        //entityManager.persist(lec_reichenauer);
        Lecturer lec_schwaneke = Lecturer.builder().priviledge(1).user(uschwaneke).build();
        //entityManager.persist(lec_schwaneke);

        Administrator adm_weitz = Administrator.builder().priviledge(1).user(wweitz).build();
        //entityManager.persist(adm_weitz);


        // Curriculum

        Curriculum curriculumMi = Curriculum.builder().examRegulation(po2017).maxTerms(1).build();
        //entityManager.persist(curriculumMi);
        Curriculum curriculumAi = Curriculum.builder().examRegulation(po2016).maxTerms(1).build();
        //entityManager.persist(curriculumAi);


        // Modules

        Module mProgrammieren1 = Module.builder().title("Programmieren 1").creditPoints(5).period(1).build();
        //entityManager.persist(mProgrammieren1);
        Module mProgrammieren2 = Module.builder().title("Programmieren 2").creditPoints(5).period(1).build();
        //entityManager.persist(mProgrammieren2);
        Module mProgrammieren3 = Module.builder().title("Programmieren 3").creditPoints(5).period(1).build();
        //entityManager.persist(mProgrammieren3);
        Module mMathe1 = Module.builder().title("Mathe 1").creditPoints(5).period(1).build();
        //entityManager.persist(mMathe1);
        Module mMathe2 = Module.builder().title("Mathe 2").creditPoints(5).period(2).build();
        //entityManager.persist(mMathe2);
        Module mMathe3 = Module.builder().title("Mathe 3").creditPoints(5).period(3).build();
        //entityManager.persist(mMathe3);


        // Module in Curriculum

        ModuleInCurriculum prog1inAi = ModuleInCurriculum.builder().curriculum(curriculumAi).module(mProgrammieren1).termPeriod(1).build();
        //entityManager.persist(prog1inAi);
        ModuleInCurriculum prog2inAi = ModuleInCurriculum.builder().curriculum(curriculumAi).module(mProgrammieren2).termPeriod(2).build();
        //entityManager.persist(prog2inAi);
        ModuleInCurriculum prog1inMi = ModuleInCurriculum.builder().curriculum(curriculumMi).module(mProgrammieren1).termPeriod(1).build();
        //entityManager.persist(prog1inMi);
        ModuleInCurriculum prog2inMi = ModuleInCurriculum.builder().curriculum(curriculumMi).module(mProgrammieren2).termPeriod(2).build();
        //entityManager.persist(prog2inMi);
        ModuleInCurriculum prog3inMi = ModuleInCurriculum.builder().curriculum(curriculumMi).module(mProgrammieren3).termPeriod(3).build();
        //entityManager.persist(prog3inMi);
        ModuleInCurriculum mathe1inAi = ModuleInCurriculum.builder().curriculum(curriculumAi).module(mMathe1).termPeriod(1).build();
        //entityManager.persist(mathe1inAi);
        ModuleInCurriculum mathe2inAi = ModuleInCurriculum.builder().curriculum(curriculumAi).module(mMathe2).termPeriod(2).build();
        //entityManager.persist(mathe2inAi);
        ModuleInCurriculum mathe3inAi = ModuleInCurriculum.builder().curriculum(curriculumAi).module(mMathe3).termPeriod(3).build();
        //entityManager.persist(mathe3inAi);
        ModuleInCurriculum mathe1inMi = ModuleInCurriculum.builder().curriculum(curriculumMi).module(mMathe1).termPeriod(1).build();
        //entityManager.persist(mathe1inMi);
        ModuleInCurriculum mathe2inMi = ModuleInCurriculum.builder().curriculum(curriculumMi).module(mMathe2).termPeriod(2).build();
        //entityManager.persist(mathe2inMi);


        // Courses

        Course cProgrammieren1 = Course.builder().title("Programmieren 1").owner(jberdux).build();
        //entityManager.persist(cProgrammieren1);
        Course cProgrammieren2 = Course.builder().title("Programmieren 2").owner(jberdux).build();
        //entityManager.persist(cProgrammieren2);
        Course cProgrammieren3 = Course.builder().title("Programmieren 3").owner(wweitz).build();
        //entityManager.persist(cProgrammieren3);
        Course cMathe1 = Course.builder().title("Lineare Algebra").owner(rreichenauer).build();
        //entityManager.persist(cMathe1);
        Course cMathe2 = Course.builder().title("Analysis").owner(rreichenauer).build();
        //entityManager.persist(cMathe2);
        Course cMathe3 = Course.builder().title("Mathe 3").owner(uschwaneke).build();
        //entityManager.persist(cMathe3);


        // Course Component

        CourseComponent prog1V = CourseComponent.builder().course(cProgrammieren1).creditPoints(3).type(CourseType.LECTURE).exam("Klausur").build();
        //entityManager.persist(prog1V);
        CourseComponent prog1P = CourseComponent.builder().course(cProgrammieren1).exam("Praktische Tätigkeit").type(CourseType.PRACTICE).creditPoints(3).build();
        //entityManager.persist(prog1P);

        CourseComponent prog2V = CourseComponent.builder().course(cProgrammieren2).creditPoints(3).type(CourseType.LECTURE).exam("Klausur").build();
        //entityManager.persist(prog2V);
        CourseComponent prog2P = CourseComponent.builder().course(cProgrammieren2).exam("Praktische Tätigkeit").type(CourseType.PRACTICE).creditPoints(3).build();
        //entityManager.persist(prog2P);

        CourseComponent prog3V = CourseComponent.builder().course(cProgrammieren3).creditPoints(3).type(CourseType.LECTURE).exam("Klausur").build();
        //entityManager.persist(prog3V);
        CourseComponent prog3P = CourseComponent.builder().course(cProgrammieren3).exam("Praktische Tätigkeit").type(CourseType.PRACTICE).creditPoints(3).build();
        //entityManager.persist(prog3P);

        CourseComponent mathe1V = CourseComponent.builder().course(cMathe1).creditPoints(5).type(CourseType.LECTURE).exam("Klausur").build();
        //entityManager.persist(mathe1V);
        CourseComponent mathe1P = CourseComponent.builder().course(cMathe1).exam("Praktische Tätigkeit").type(CourseType.PRACTICE).creditPoints(0).build();
        //entityManager.persist(mathe1P);

        CourseComponent mathe2V = CourseComponent.builder().course(cMathe2).creditPoints(5).type(CourseType.LECTURE).exam("Klausur").build();
        //entityManager.persist(mathe2V);
        CourseComponent mathe2P = CourseComponent.builder().course(cMathe2).exam("Praktische Tätigkeit").type(CourseType.PRACTICE).creditPoints(0).build();
        //entityManager.persist(mathe2P);

        CourseComponent mathe3V = CourseComponent.builder().course(cMathe3).creditPoints(5).type(CourseType.LECTURE).exam("Klausur").build();
        //entityManager.persist(mathe3V);
        CourseComponent mathe3P = CourseComponent.builder().course(cMathe3).exam("Praktische Tätigkeit").type(CourseType.PRACTICE).creditPoints(0).build();
        //entityManager.persist(mathe3P);


        // Student Attends Course

        StudentAttendsCourse esperProg2 = StudentAttendsCourse.builder().student(stu_esper).course(cProgrammieren2).term(ss2019).build();
        //entityManager.persist(esperProg2);
        StudentAttendsCourse esperMathe2 = StudentAttendsCourse.builder().student(stu_esper).course(cMathe2).term(ss2019).build();
        //entityManager.persist(esperMathe2);
        StudentAttendsCourse esperProg3 = StudentAttendsCourse.builder().student(stu_esper).course(cProgrammieren3).term(ws1920).build();
        //entityManager.persist(esperProg3);
        StudentAttendsCourse esperMathe3 = StudentAttendsCourse.builder().student(stu_esper).course(cMathe3).term(ws1920).build();
        //entityManager.persist(esperMathe3);

        StudentAttendsCourse ahlersMathe3 = StudentAttendsCourse.builder().student(stu_ahlers).course(cMathe3).term(ws1920).build();
        //entityManager.persist(ahlersMathe3);
        StudentAttendsCourse ahlersProg3 = StudentAttendsCourse.builder().student(stu_ahlers).course(cProgrammieren3).term(ws1920).build();
        //entityManager.persist(ahlersProg3);

        StudentAttendsCourse thielMathe1 = StudentAttendsCourse.builder().student(stu_thiel).course(cMathe1).term(ws1920).build();
        //entityManager.persist(thielMathe1);
        StudentAttendsCourse thielMathe3 = StudentAttendsCourse.builder().student(stu_thiel).course(cMathe3).term(ws1920).build();
        //entityManager.persist(thielMathe3);
        StudentAttendsCourse thielProg3 = StudentAttendsCourse.builder().student(stu_thiel).course(cProgrammieren3).term(ws1920).build();
        //entityManager.persist(thielProg3);


        // Groups

        Group prog1Vgroup = Group.builder().lecturer(lec_berdux).slots(70).room(r11).term(ws1920).dayOfWeek((DayOfWeek.MONDAY)).groupChar('A')
                .courseComponent(prog3V).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog1Vgroup);
        Group prog1PgroupA = Group.builder().lecturer(lec_berdux).slots(20).room(r12).term(ws1920).dayOfWeek((DayOfWeek.MONDAY)).groupChar('A')
                .courseComponent(prog3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog1PgroupA);
        Group prog1PgroupB = Group.builder().lecturer(lec_berdux).slots(20).room(r12).term(ws1920).dayOfWeek((DayOfWeek.MONDAY)).groupChar('B')
                .courseComponent(prog3P).startTime(LocalTime.of(11,45)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog1PgroupB);

        Group prog2Vgroup = Group.builder().lecturer(lec_berdux).slots(80).room(r11).term(ss2019).dayOfWeek((DayOfWeek.MONDAY)).groupChar('A')
                .courseComponent(prog3V).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog2Vgroup);
        Group prog2PgroupA = Group.builder().lecturer(lec_berdux).slots(20).room(r13).term(ss2019).dayOfWeek((DayOfWeek.MONDAY)).groupChar('A')
                .courseComponent(prog3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog2PgroupA);
        Group prog2PgroupB = Group.builder().lecturer(lec_berdux).slots(20).room(r13).term(ss2019).dayOfWeek((DayOfWeek.MONDAY)).groupChar('B')
                .courseComponent(prog3P).startTime(LocalTime.of(11,45)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog2PgroupB);

        Group prog3Vgroup = Group.builder().lecturer(lec_weitz).slots(90).room(r11).term(ws1920).dayOfWeek((DayOfWeek.TUESDAY)).groupChar('A')
                .courseComponent(prog3V).startTime(LocalTime.of(8,15)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog3Vgroup);
        Group prog3PgroupA = Group.builder().lecturer(lec_weitz).slots(20).room(r12).term(ws1920).dayOfWeek((DayOfWeek.TUESDAY)).groupChar('A')
                .courseComponent(prog3P).startTime(LocalTime.of(10,0)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog3PgroupA);
        Group prog3PgroupB = Group.builder().lecturer(lec_weitz).slots(20).room(r12).term(ws1920).dayOfWeek((DayOfWeek.TUESDAY)).groupChar('B')
                .courseComponent(prog3P).startTime(LocalTime.of(11,45)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog3PgroupB);
        Group prog3PgroupC = Group.builder().lecturer(lec_weitz).slots(20).room(r13).term(ws1920).dayOfWeek((DayOfWeek.TUESDAY)).groupChar('C')
                .courseComponent(prog3P).startTime(LocalTime.of(10,0)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog3PgroupC);
        Group prog3PgroupD = Group.builder().lecturer(lec_weitz).slots(20).room(r13).term(ws1920).dayOfWeek((DayOfWeek.TUESDAY)).groupChar('D')
                .courseComponent(prog3P).startTime(LocalTime.of(11,45)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(prog3PgroupD);

        Group mathe1Vgroup = Group.builder().lecturer(lec_reichenauer).slots(100).room(r11).term(ws1920).dayOfWeek((DayOfWeek.WEDNESDAY)).groupChar('A')
                .courseComponent(mathe1V).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(mathe1Vgroup);
        Group mathe1PgroupA = Group.builder().lecturer(lec_reichenauer).slots(20).room(r11).term(ws1920).dayOfWeek((DayOfWeek.WEDNESDAY)).groupChar('A')
                .courseComponent(mathe1P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(mathe1PgroupA);
        Group mathe1PgroupB = Group.builder().lecturer(lec_reichenauer).slots(20).room(r11).term(ws1920).dayOfWeek((DayOfWeek.WEDNESDAY)).groupChar('B')
                .courseComponent(mathe1P).startTime(LocalTime.of(11,45)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(mathe1PgroupB);

        Group mathe2Vgroup = Group.builder().lecturer(lec_reichenauer).slots(110).room(r11).term(ss2019).dayOfWeek((DayOfWeek.WEDNESDAY)).groupChar('A')
                .courseComponent(mathe1V).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(mathe2Vgroup);
        Group mathe2PgroupA = Group.builder().lecturer(lec_reichenauer).slots(20).room(r11).term(ss2019).dayOfWeek((DayOfWeek.WEDNESDAY)).groupChar('A')
                .courseComponent(mathe1P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(mathe2PgroupA);
        Group mathe2PgroupB = Group.builder().lecturer(lec_reichenauer).slots(20).room(r11).term(ss2019).dayOfWeek((DayOfWeek.WEDNESDAY)).groupChar('B')
                .courseComponent(mathe1P).startTime(LocalTime.of(11,45)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(mathe2PgroupB);

        Group mathe3Vgroup = Group.builder().lecturer(lec_schwaneke).slots(120).room(r14).term(ws1920).dayOfWeek((DayOfWeek.THURSDAY)).groupChar('A')
                .courseComponent(mathe3V).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(mathe3Vgroup);
        Group mathe3PgroupA = Group.builder().lecturer(lec_schwaneke).slots(20).room(r17).term(ws1920).dayOfWeek((DayOfWeek.THURSDAY)).groupChar('A')
                .courseComponent(mathe3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(mathe3PgroupA);
        Group mathe3PgroupB = Group.builder().lecturer(lec_schwaneke).slots(20).room(r17).term(ws1920).dayOfWeek((DayOfWeek.THURSDAY)).groupChar('B')
                .courseComponent(mathe3P).startTime(LocalTime.of(11,45)).endTime(LocalTime.of(11,30)).build();
        //entityManager.persist(mathe3PgroupB);


        // Swap Offer

        SwapOffer esperMathe3P_B_to_A = SwapOffer.builder().student(stu_esper).timestamp(new Timestamp(System.currentTimeMillis())).fromGroup(mathe3PgroupB).toGroup(mathe3PgroupA).build();
        //entityManager.persist(esperMathe3P_B_to_A);
        SwapOffer esperProg3P_A_to_B = SwapOffer.builder().student(stu_esper).timestamp(new Timestamp(System.currentTimeMillis())).fromGroup(prog3PgroupA).toGroup(prog3PgroupB).build();
        //entityManager.persist(esperProg3P_A_to_B);

        SwapOffer thielMathe3P_B_to_A = SwapOffer.builder().student(stu_thiel).timestamp(new Timestamp(System.currentTimeMillis())).fromGroup(mathe3PgroupB).toGroup(mathe3PgroupA).build();
        //entityManager.persist(thielMathe3P_B_to_A);
        SwapOffer thielProg3P_B_to_A = SwapOffer.builder().student(stu_thiel).timestamp(new Timestamp(System.currentTimeMillis())).fromGroup(prog3PgroupB).toGroup(prog3PgroupA).build();
        //entityManager.persist(thielProg3P_B_to_A);

        SwapOffer ahlersMathe3P_A_to_B = SwapOffer.builder().student(stu_ahlers).timestamp(new Timestamp(System.currentTimeMillis())).fromGroup(mathe3PgroupA).toGroup(mathe3PgroupB).build();
        //entityManager.persist(ahlersMathe3P_A_to_B);
        SwapOffer ahlersProg3P_B_to_A = SwapOffer.builder().student(stu_ahlers).timestamp(new Timestamp(System.currentTimeMillis())).fromGroup(prog3PgroupB).toGroup(prog3PgroupA).build();
        //entityManager.persist(ahlersProg3P_B_to_A);


        // Student Passed Exam

        StudentPassedExam ahlersPassMathe1V = StudentPassedExam.builder().student(stu_ahlers).courseComponent(mathe1V).grade(1.7f).build();
        //entityManager.persist(ahlersPassMathe1V);
        StudentPassedExam ahlersPassMathe1P = StudentPassedExam.builder().student(stu_ahlers).courseComponent(mathe1P).build();
        //entityManager.persist(ahlersPassMathe1P);
        StudentPassedExam ahlersPassProg1V = StudentPassedExam.builder().student(stu_ahlers).courseComponent(prog1V).grade(1.0f).build();
        //entityManager.persist(ahlersPassProg1V);
        StudentPassedExam ahlersPassProg1P = StudentPassedExam.builder().student(stu_ahlers).courseComponent(prog1P).build();
        //entityManager.persist(ahlersPassProg1P);

        StudentPassedExam thielPassMathe2V = StudentPassedExam.builder().student(stu_thiel).courseComponent(mathe2V).grade(1.3f).build();
        //entityManager.persist(thielPassMathe2V);
        StudentPassedExam thielPassMathe2P = StudentPassedExam.builder().student(stu_thiel).courseComponent(mathe2P).build();
        //entityManager.persist(thielPassMathe2P);

        StudentPassedExam esperPassMathe1V = StudentPassedExam.builder().student(stu_esper).courseComponent(mathe1V).grade(1.3f).build();
        //entityManager.persist(esperPassMathe1V);
        StudentPassedExam esperPassMathe1P = StudentPassedExam.builder().student(stu_esper).courseComponent(mathe1P).build();
        //entityManager.persist(esperPassMathe1P);


        // C O N N E C T I O N     T A B L E S


        // Campus <-> University

        uni.setCampuses(new HashSet<>());
        uni.getCampuses().add(ude);


        // Campus <-> Building

        ude.setBuildings(new HashSet<>());
        ude.getBuildings().add(dBuilding);
        ude.getBuildings().add(cBuilding);


        // Building <-> Rooms

        dBuilding.setRooms(new HashSet<>());
        dBuilding.getRooms().add(r11);
        dBuilding.getRooms().add(r12);
        dBuilding.getRooms().add(r13);
        dBuilding.getRooms().add(r14);
        dBuilding.getRooms().add(r17);

        cBuilding.setRooms(new HashSet<>());
        cBuilding.getRooms().add(r23);
        cBuilding.getRooms().add(r25);


        // University <-> Fields of Study

        uni.setFieldsOfStudy(new HashSet<>());
        uni.getFieldsOfStudy().add(informatik);


        // Study Program <-> Field of Study

        informatik.setStudyPrograms(new HashSet<>());
        informatik.getStudyPrograms().add(medieninformatik);
        informatik.getStudyPrograms().add(angewandteinformatik);

        medieninformatik.setFieldsOfStudy(new HashSet<>());
        medieninformatik.getFieldsOfStudy().add(informatik);

        angewandteinformatik.setFieldsOfStudy(new HashSet<>());
        angewandteinformatik.getFieldsOfStudy().add(informatik);


        // PO <-> Study Program

        medieninformatik.setExamRegulations(new HashSet<>());
        medieninformatik.getExamRegulations().add(po2017);

        angewandteinformatik.setExamRegulations(new HashSet<>());
        angewandteinformatik.getExamRegulations().add(po2016);


        // PO <-> Student

        po2016.setStudents(new HashSet<>());
        po2017.setStudents(new HashSet<>());
        po2017.getStudents().add(stu_ahlers);
        po2017.getStudents().add(stu_esper);
        po2017.getStudents().add(stu_wirt);
        po2017.getStudents().add(stu_thiel);
        po2017.getStudents().add(stu_deuster);


        // Connect PO <-> Curriculum

        po2017.setCurriculums(new HashSet<>());
        po2017.getCurriculums().add(curriculumMi);

        po2016.setCurriculums(new HashSet<>());
        po2016.getCurriculums().add(curriculumAi);


        // Modules <-> Curriculum

        curriculumAi.setModulesInCurriculum(new HashSet<>());
        curriculumAi.getModulesInCurriculum().add(prog1inAi);
        curriculumAi.getModulesInCurriculum().add(prog2inAi);
        curriculumAi.getModulesInCurriculum().add(mathe1inAi);
        curriculumAi.getModulesInCurriculum().add(mathe2inAi);
        curriculumAi.getModulesInCurriculum().add(mathe3inAi);

        curriculumMi.setModulesInCurriculum(new HashSet<>());
        curriculumMi.getModulesInCurriculum().add(prog1inMi);
        curriculumMi.getModulesInCurriculum().add(prog2inMi);
        curriculumMi.getModulesInCurriculum().add(prog3inMi);
        curriculumMi.getModulesInCurriculum().add(mathe1inMi);
        curriculumMi.getModulesInCurriculum().add(mathe2inMi);

        mProgrammieren1.setModulesInCurriculum(new HashSet<>());
        mProgrammieren1.getModulesInCurriculum().add(prog1inAi);
        mProgrammieren1.getModulesInCurriculum().add(prog1inMi);

        mProgrammieren2.setModulesInCurriculum(new HashSet<>());
        mProgrammieren2.getModulesInCurriculum().add(prog2inAi);
        mProgrammieren2.getModulesInCurriculum().add(prog2inMi);

        mProgrammieren3.setModulesInCurriculum(new HashSet<>());
        mProgrammieren3.getModulesInCurriculum().add(prog3inMi);

        mMathe1.setModulesInCurriculum(new HashSet<>());
        mMathe1.getModulesInCurriculum().add(mathe1inAi);
        mMathe1.getModulesInCurriculum().add(mathe1inMi);

        mMathe2.setModulesInCurriculum(new HashSet<>());
        mMathe2.getModulesInCurriculum().add(mathe2inAi);
        mMathe2.getModulesInCurriculum().add(mathe2inMi);

        mMathe3.setModulesInCurriculum(new HashSet<>());
        mMathe3.getModulesInCurriculum().add(mathe3inAi);


        // Connect Modules <-> Courses

        ws1920.setCourses(new HashSet<>());
        ws1920.getCourses().add(cProgrammieren1);
        ws1920.getCourses().add(cProgrammieren3);
        ws1920.getCourses().add(cMathe1);
        ws1920.getCourses().add(cMathe3);

        ss2019.setCourses(new HashSet<>());
        ss2019.getCourses().add(cProgrammieren2);
        ss2019.getCourses().add(cMathe2);

        cProgrammieren1.setTerms(new HashSet<>());
        cProgrammieren1.getTerms().add(ws1920);

        cProgrammieren2.setTerms(new HashSet<>());
        cProgrammieren2.getTerms().add(ss2019);

        cProgrammieren3.setTerms(new HashSet<>());
        cProgrammieren3.getTerms().add(ws1920);

        cMathe1.setTerms(new HashSet<>());
        cMathe1.getTerms().add(ws1920);

        cMathe2.setTerms(new HashSet<>());
        cMathe2.getTerms().add(ss2019);

        cMathe3.setTerms(new HashSet<>());
        cMathe3.getTerms().add(ws1920);

        mProgrammieren1.setCourses(new HashSet<>());
        mProgrammieren1.getCourses().add(cProgrammieren1);

        mProgrammieren2.setCourses(new HashSet<>());
        mProgrammieren2.getCourses().add(cProgrammieren2);

        mProgrammieren3.setCourses(new HashSet<>());
        mProgrammieren3.getCourses().add(cProgrammieren3);

        mMathe1.setCourses(new HashSet<>());
        mMathe1.getCourses().add(cMathe1);

        mMathe2.setCourses(new HashSet<>());
        mMathe2.getCourses().add(cMathe2);

        mMathe3.setCourses(new HashSet<>());
        mMathe3.getCourses().add(cMathe3);

        cProgrammieren1.setModules(new HashSet<>());
        cProgrammieren1.getModules().add(mProgrammieren1);

        cProgrammieren2.setModules(new HashSet<>());
        cProgrammieren2.getModules().add(mProgrammieren2);

        cProgrammieren3.setModules(new HashSet<>());
        cProgrammieren3.getModules().add(mProgrammieren3);

        cMathe1.setModules(new HashSet<>());
        cMathe1.getModules().add(mMathe1);

        cMathe2.setModules(new HashSet<>());
        cMathe2.getModules().add(mMathe2);

        cMathe3.setModules(new HashSet<>());
        cMathe3.getModules().add(mMathe3);


        // Connect Course <-> CourseComponent

        cProgrammieren1.setCourseComponents(new HashSet<>());
        cProgrammieren1.getCourseComponents().add(prog1V);
        cProgrammieren1.getCourseComponents().add(prog1P);

        cProgrammieren2.setCourseComponents(new HashSet<>());
        cProgrammieren2.getCourseComponents().add(prog2V);
        cProgrammieren2.getCourseComponents().add(prog2P);

        cProgrammieren3.setCourseComponents(new HashSet<>());
        cProgrammieren3.getCourseComponents().add(prog3V);
        cProgrammieren3.getCourseComponents().add(prog3P);


        cMathe1.setCourseComponents(new HashSet<>());
        cMathe1.getCourseComponents().add(mathe1V);
        cMathe1.getCourseComponents().add(mathe1P);

        cMathe2.setCourseComponents(new HashSet<>());
        cMathe2.getCourseComponents().add(mathe2V);
        cMathe2.getCourseComponents().add(mathe2P);

        cMathe3.setCourseComponents(new HashSet<>());
        cMathe3.getCourseComponents().add(mathe3V);
        cMathe3.getCourseComponents().add(mathe3P);


        // Connect Student <-> Attends Course

        stu_ahlers.setAttendCourses(new HashSet<>());
        stu_ahlers.getAttendCourses().add(ahlersMathe3);
        stu_ahlers.getAttendCourses().add(ahlersProg3);

        stu_esper.setAttendCourses(new HashSet<>());
        stu_esper.getAttendCourses().add(esperMathe2);
        stu_esper.getAttendCourses().add(esperProg2);
        stu_esper.getAttendCourses().add(esperMathe3);
        stu_esper.getAttendCourses().add(esperProg3);

        stu_thiel.setAttendCourses(new HashSet<>());
        stu_thiel.getAttendCourses().add(thielMathe1);
        stu_thiel.getAttendCourses().add(thielMathe3);
        stu_thiel.getAttendCourses().add(thielProg3);

        cProgrammieren2.setStudentAttendsCourses(new HashSet<>());
        cProgrammieren2.getStudentAttendsCourses().add(esperProg2);

        cProgrammieren3.setStudentAttendsCourses(new HashSet<>());
        cProgrammieren3.getStudentAttendsCourses().add(esperProg3);
        cProgrammieren3.getStudentAttendsCourses().add(ahlersProg3);

        cMathe1.setStudentAttendsCourses(new HashSet<>());
        cMathe1.getStudentAttendsCourses().add(thielMathe1);

        cMathe2.setStudentAttendsCourses(new HashSet<>());
        cMathe2.getStudentAttendsCourses().add(esperMathe2);

        cMathe3.setStudentAttendsCourses(new HashSet<>());
        cMathe3.getStudentAttendsCourses().add(ahlersMathe3);
        cMathe3.getStudentAttendsCourses().add(esperMathe3);
        cMathe3.getStudentAttendsCourses().add(thielMathe3);

        ws1920.setStudentAttendsCourses(new HashSet<>());
        ws1920.getStudentAttendsCourses().add(esperMathe3);
        ws1920.getStudentAttendsCourses().add(esperProg3);
        ws1920.getStudentAttendsCourses().add(ahlersMathe3);
        ws1920.getStudentAttendsCourses().add(ahlersProg3);
        ws1920.getStudentAttendsCourses().add(thielMathe1);
        ws1920.getStudentAttendsCourses().add(thielMathe3);
        ws1920.getStudentAttendsCourses().add(thielProg3);

        ss2019.setStudentAttendsCourses(new HashSet<>());
        ss2019.getStudentAttendsCourses().add(esperMathe2);
        ss2019.getStudentAttendsCourses().add(esperProg2);


        // Connect Groups <-> CourseComponent

        prog1V.setGroups(new HashSet<>());
        prog1V.getGroups().add(prog1Vgroup);

        prog1P.setGroups(new HashSet<>());
        prog1P.getGroups().add(prog1PgroupA);
        prog1P.getGroups().add(prog1PgroupB);

        prog2V.setGroups(new HashSet<>());
        prog2V.getGroups().add(prog2Vgroup);

        prog2P.setGroups(new HashSet<>());
        prog2P.getGroups().add(prog2PgroupA);
        prog2P.getGroups().add(prog2PgroupB);

        prog3V.setGroups(new HashSet<>());
        prog3V.getGroups().add(prog3Vgroup);

        prog3P.setGroups(new HashSet<>());
        prog3P.getGroups().add(prog3PgroupA);
        prog3P.getGroups().add(prog3PgroupB);
        prog3P.getGroups().add(prog3PgroupC);
        prog3P.getGroups().add(prog3PgroupD);

        mathe1V.setGroups(new HashSet<>());
        mathe1V.getGroups().add(mathe1Vgroup);

        mathe1P.setGroups(new HashSet<>());
        mathe1P.getGroups().add(mathe1PgroupA);
        mathe1P.getGroups().add(mathe1PgroupB);

        mathe2V.setGroups(new HashSet<>());
        mathe2V.getGroups().add(mathe2Vgroup);

        mathe2P.setGroups(new HashSet<>());
        mathe2P.getGroups().add(mathe2PgroupA);
        mathe2P.getGroups().add(mathe2PgroupB);

        mathe3V.setGroups(new HashSet<>());
        mathe3V.getGroups().add(mathe3Vgroup);

        mathe3P.setGroups(new HashSet<>());
        mathe3P.getGroups().add(mathe3PgroupA);
        mathe3P.getGroups().add(mathe3PgroupB);


        // Connect Student <-> Groups

        stu_ahlers.setGroups(new HashSet<>());
        stu_ahlers.getGroups().add(mathe3Vgroup);
        stu_ahlers.getGroups().add(mathe3PgroupA);
        stu_ahlers.getGroups().add(prog3Vgroup);
        stu_ahlers.getGroups().add(prog3PgroupB);

        stu_esper.setGroups(new HashSet<>());
        stu_esper.getGroups().add(mathe2Vgroup);
        stu_esper.getGroups().add(mathe2PgroupA);
        stu_esper.getGroups().add(mathe3Vgroup);
        stu_esper.getGroups().add(mathe3PgroupB);
        stu_esper.getGroups().add(prog2Vgroup);
        stu_esper.getGroups().add(prog2PgroupA);
        stu_esper.getGroups().add(prog3Vgroup);
        stu_esper.getGroups().add(prog3PgroupA);

        stu_thiel.setGroups(new HashSet<>());
        stu_thiel.getGroups().add(mathe1Vgroup);
        stu_thiel.getGroups().add(mathe1PgroupB);
        stu_thiel.getGroups().add(mathe3Vgroup);
        stu_thiel.getGroups().add(mathe3PgroupB);
        stu_thiel.getGroups().add(prog3Vgroup);
        stu_thiel.getGroups().add(prog3PgroupB);

        mathe2Vgroup.setStudents(new HashSet<>());
        mathe2Vgroup.getStudents().add(stu_esper);

        mathe2PgroupA.setStudents(new HashSet<>());
        mathe2PgroupA.getStudents().add(stu_esper);

        mathe3Vgroup.setStudents(new HashSet<>());
        mathe3Vgroup.getStudents().add(stu_esper);
        mathe3Vgroup.getStudents().add(stu_thiel);
        mathe3Vgroup.getStudents().add(stu_ahlers);

        mathe3PgroupA.setStudents(new HashSet<>());
        mathe3PgroupA.getStudents().add(stu_ahlers);

        mathe3PgroupB.setStudents(new HashSet<>());
        mathe3PgroupB.getStudents().add(stu_esper);
        mathe3PgroupB.getStudents().add(stu_thiel);

        prog2Vgroup.setStudents(new HashSet<>());
        prog2Vgroup.getStudents().add(stu_esper);

        prog2PgroupA.setStudents(new HashSet<>());
        prog2PgroupA.getStudents().add(stu_esper);

        prog3Vgroup.setStudents(new HashSet<>());
        prog3Vgroup.getStudents().add(stu_esper);
        prog3Vgroup.getStudents().add(stu_ahlers);
        prog3Vgroup.getStudents().add(stu_thiel);

        prog3PgroupA.setStudents(new HashSet<>());
        prog3PgroupA.getStudents().add(stu_esper);

        prog3PgroupB.setStudents(new HashSet<>());
        prog3PgroupB.getStudents().add(stu_ahlers);
        prog3PgroupB.getStudents().add(stu_thiel);


        // Connect Groups <-> Room

        r11.setGroups(new HashSet<>());
        r11.getGroups().add(prog1Vgroup);
        r11.getGroups().add(prog2Vgroup);
        r11.getGroups().add(prog3Vgroup);
        r11.getGroups().add(mathe1Vgroup);
        r11.getGroups().add(mathe2Vgroup);
        r11.getGroups().add(mathe1PgroupA);
        r11.getGroups().add(mathe1PgroupB);
        r11.getGroups().add(mathe2PgroupA);
        r11.getGroups().add(mathe2PgroupB);

        r12.setGroups(new HashSet<>());
        r12.getGroups().add(prog1PgroupA);
        r12.getGroups().add(prog1PgroupB);
        r12.getGroups().add(prog3PgroupA);
        r12.getGroups().add(prog3PgroupB);

        r13.setGroups(new HashSet<>());
        r13.getGroups().add(prog2PgroupA);
        r13.getGroups().add(prog2PgroupB);
        r13.getGroups().add(prog3PgroupC);
        r13.getGroups().add(prog3PgroupD);

        r14.setGroups(new HashSet<>());
        r14.getGroups().add(mathe3Vgroup);

        r17.setGroups(new HashSet<>());
        r17.getGroups().add(mathe3PgroupA);
        r17.getGroups().add(mathe3PgroupB);


        // Connect PassedExam <-> Student

        stu_ahlers.setPassedExams(new HashSet<>());
        stu_ahlers.getPassedExams().add(ahlersPassMathe1P);
        stu_ahlers.getPassedExams().add(ahlersPassMathe1V);
        stu_ahlers.getPassedExams().add(ahlersPassProg1P);
        stu_ahlers.getPassedExams().add(ahlersPassProg1V);

        stu_esper.setPassedExams(new HashSet<>());
        stu_esper.getPassedExams().add(esperPassMathe1P);
        stu_esper.getPassedExams().add(esperPassMathe1V);

        stu_thiel.setPassedExams(new HashSet<>());
        stu_thiel.getPassedExams().add(thielPassMathe2P);
        stu_thiel.getPassedExams().add(thielPassMathe2V);

        prog1P.setStudentsPassedExam(new HashSet<>());
        prog1P.getStudentsPassedExam().add(ahlersPassProg1P);

        prog1V.setStudentsPassedExam(new HashSet<>());
        prog1V.getStudentsPassedExam().add(ahlersPassProg1V);

        mathe1P.setStudentsPassedExam(new HashSet<>());
        mathe1P.getStudentsPassedExam().add(esperPassMathe1P);
        mathe1P.getStudentsPassedExam().add(ahlersPassMathe1P);

        mathe1V.setStudentsPassedExam(new HashSet<>());
        mathe1V.getStudentsPassedExam().add(esperPassMathe1V);
        mathe1V.getStudentsPassedExam().add(ahlersPassMathe1V);

        mathe2P.setStudentsPassedExam(new HashSet<>());
        mathe2P.getStudentsPassedExam().add(thielPassMathe2P);

        mathe2V.setStudentsPassedExam(new HashSet<>());
        mathe2V.getStudentsPassedExam().add(thielPassMathe2V);


        // Connect SwapOffer <-> Student

        stu_esper.setSwapOffers(new HashSet<>());
        stu_esper.getSwapOffers().add(esperMathe3P_B_to_A);
        stu_esper.getSwapOffers().add(esperProg3P_A_to_B);

        stu_thiel.setSwapOffers(new HashSet<>());
        stu_thiel.getSwapOffers().add(thielMathe3P_B_to_A);
        stu_thiel.getSwapOffers().add(thielProg3P_B_to_A);

        stu_ahlers.setSwapOffers(new HashSet<>());
        stu_ahlers.getSwapOffers().add(ahlersMathe3P_A_to_B);
        stu_ahlers.getSwapOffers().add(ahlersProg3P_B_to_A);


        mathe3PgroupA.setSwapOffers(new HashSet<>());
        mathe3PgroupA.getSwapOffers().add(ahlersMathe3P_A_to_B);

        mathe3PgroupA.setSwapRequests(new HashSet<>());
        mathe3PgroupA.getSwapRequests().add(esperMathe3P_B_to_A);
        mathe3PgroupA.getSwapRequests().add(thielProg3P_B_to_A);


        mathe3PgroupB.setSwapOffers(new HashSet<>());
        mathe3PgroupB.getSwapOffers().add(esperMathe3P_B_to_A);
        mathe3PgroupB.getSwapOffers().add(thielMathe3P_B_to_A);

        mathe3PgroupB.setSwapRequests(new HashSet<>());
        mathe3PgroupB.getSwapRequests().add(ahlersMathe3P_A_to_B);


        prog3PgroupA.setSwapOffers(new HashSet<>());
        prog3PgroupA.getSwapOffers().add(esperProg3P_A_to_B);

        prog3PgroupA.setSwapRequests(new HashSet<>());
        prog3PgroupA.getSwapRequests().add(thielProg3P_B_to_A);
        prog3PgroupA.getSwapRequests().add(ahlersProg3P_B_to_A);


        prog3PgroupB.setSwapOffers(new HashSet<>());
        prog3PgroupB.getSwapOffers().add(ahlersProg3P_B_to_A);
        prog3PgroupB.getSwapOffers().add(thielProg3P_B_to_A);

        prog3PgroupB.setSwapRequests(new HashSet<>());
        prog3PgroupB.getSwapRequests().add(esperProg3P_A_to_B);



        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("hsrm_medieninformatik.json");
        try {
            objectMapper.writeValue(file, uni);

        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("JSON new File Error");
        }
        universityRepository.saveAndFlush(uni);
        return file;
        
    }
}
