package de.hsrm.mi.swtpro.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.Module;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Builder;

@Component
public class JSONGenerator {

    @PostConstruct
    private void init() {

        University uni = University.builder().name("Hochschule RheinMain").adress("Kurt-Schuhmacher-Ring 18").build();
        Campus ude = Campus.builder().name("Unter den Eichen").adress("Unter den Eichen 6").university(uni).build();
        uni.getCampuses().add(ude);
        Building dBuilding = Building.builder().name("Gebäude D").campus(ude).build();
        ude.getBuildings().add(dBuilding);
        Room r14 = Room.builder().number(14).seats(60).building(dBuilding).build();
        dBuilding.getRooms().add(r14);

        Term ws1920 = Term.builder().startDate(Date.valueOf("2019-10-01")).endDate(Date.valueOf("2020-03-31")).period(1).build();
        ExamRegulation po2017 = ExamRegulation.builder().date(Date.valueOf("2017-10-01")).build();

        User testUser = User.builder().firstName("Test").lastName("User").loginName("testUser").password("test").build();
        Student testStudent = Student.builder().user(testUser).enrolementNumber(123456)
                .enrolmentTerm(ws1920).mail("test@test.com").examRegulation(po2017).build();


        FieldOfStudy dcsm = FieldOfStudy.builder().title("DCSM").build();
        StudyProgram medieninformatik = StudyProgram.builder().title("Medieninformatik").degree("Bachlor").build();
        Curriculum curriculum = Curriculum.builder().examRegulation(po2017).term(1).build();
        Module mProgrammieren3 = Module.builder().title("Programmieren 3").creditPoints(5).period(1).build();
        Course cProgrammieren3 = Course.builder().title("Programmieren 3").owner(testUser).build();
        CourseComponent prog3V = CourseComponent.builder().course(cProgrammieren3).creditPoints(5).type(CourseType.LECTURE).exam("Klausur").build();
        CourseComponent prog3P = CourseComponent.builder().course(cProgrammieren3).exam("Praktische Tätigkeit").type(CourseType.PRACTICE).creditPoints(0).build();
        Group prog3Vgroup = Group.builder().lecturer(testUser).slots(100).room(r14).term(ws1920).dayOfWeek((DayOfWeek.MONDAY)).group('A')
                .courseComponent(prog3V).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        Group prog3PgroupA = Group.builder().lecturer(testUser).slots(20).room(r14).term(ws1920).dayOfWeek((DayOfWeek.TUESDAY)).group('A')
                .courseComponent(prog3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        Group prog3PgroupB = Group.builder().lecturer(testUser).slots(20).room(r14).term(ws1920).dayOfWeek((DayOfWeek.WEDNESDAY)).group('B')
                .courseComponent(prog3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        Group prog3PgroupC = Group.builder().lecturer(testUser).slots(20).room(r14).term(ws1920).dayOfWeek((DayOfWeek.THURSDAY)).group('C')
                .courseComponent(prog3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
        Group prog3PgroupD = Group.builder().lecturer(testUser).slots(20).room(r14).term(ws1920).dayOfWeek((DayOfWeek.FRIDAY)).group('D')
                .courseComponent(prog3P).startTime(LocalTime.of(10,00)).endTime(LocalTime.of(11,30)).build();
       StudentAttendsCourse studentAttendsCourse = StudentAttendsCourse.builder().student(testStudent).course(cProgrammieren3).term(ws1920).build();

        uni.getFieldOfStudies().add(dcsm);
        dcsm.setUniversity(uni);
        po2017.getCurricula().add(curriculum);
        curriculum.getModules().add(mProgrammieren3);
        curriculum.setExamRegulation(po2017);
        mProgrammieren3.addCourse(cProgrammieren3);
        mProgrammieren3.addCurriuculum(curriculum);
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

        ws1920.addCourse(cProgrammieren3);
        po2017.setStudyProgram(medieninformatik);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("hsrm_medieninformatik.json"), uni);
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("JSON new File Error");
        }
    }
}