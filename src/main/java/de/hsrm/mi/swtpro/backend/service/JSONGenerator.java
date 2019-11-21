package de.hsrm.mi.swtpro.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swtpro.backend.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Component
public class JSONGenerator {

    @PostConstruct
    private void init() {
        University uni = new University.Builder("HochschuleRheinMain","Kurt-Schuhmacher-Ring 18").build();
        Campus ude = new Campus.Builder("Unter den Eichen", "Unter den Eichen 6",uni).build();
        uni.getCampus().add(ude);
        System.out.println(ude.getId());
        Building dBuilding = new Building.Builder("Gebäude D", ude).build();
        ude.getBuildings().add(dBuilding);
        Room r14 = new Room.Builder(14, 60, dBuilding).build();
        dBuilding.getRooms().add(r14);

        Term ws1920 = new Term.Builder().withStartDate(Date.valueOf("2019-10-01")).withEndDate(Date.valueOf("2020-03-31")).inPeriod(1).build();

        ExamRegulation po2017 = new ExamRegulation.Builder(Date.valueOf("2017-10-01")).build();

        User testUser = new User.Builder("Test","User","testUser","test",true).build();
        Student testStudent = new Student.Builder("test","Student","testStudent","test",false
                ,123456,ws1920,"test@test.com",po2017).build();

        FieldOfStudy dcsm = new FieldOfStudy.Builder("DCSM").build();
        StudyProgram medieninformatik = new StudyProgram.Builder("Medieninformatik","Bachlor").withExamRegulation(po2017).build();
        Curriculum curriculum = new Curriculum.Builder(po2017).forTerm(1).build();
        Module mProgrammieren3 = new Module.Builder("Programmieren 3").inPeriod(1).inCurriculum(curriculum).hasCreditPoints(5).build();
        Course cProgrammieren3 = new Course.Builder("Programmieren 3").withOwner(testUser).build();
        CourseComponent prog3V = new CourseComponent.Builder(cProgrammieren3,CourseType.LECTURE).hasCreditPoints(5).build();
        CourseComponent prog3P = new CourseComponent.Builder(cProgrammieren3,CourseType.PRACTICE).hasCreditPoints(0).build();
        Group prog3Vgroup = new Group.Builder().hasLecturer(testUser).hasSlots(100).inRoom(r14).inTerm(ws1920).onDayOfWeek(DayOfWeek.MONDAY)
                .withStartTime(LocalTime.of(10,00)).withEndTime(LocalTime.of(11,30)).build(prog3V,'A');
        Group prog3PgroupA = new Group.Builder().hasLecturer(testUser).hasSlots(20).inRoom(r14).inTerm(ws1920).onDayOfWeek(DayOfWeek.TUESDAY)
                .withStartTime(LocalTime.of(10,00)).withEndTime(LocalTime.of(11,30)).build(prog3P,'A');
        Group prog3PgroupB = new Group.Builder().hasLecturer(testUser).hasSlots(20).inRoom(r14).inTerm(ws1920).onDayOfWeek(DayOfWeek.WEDNESDAY)
                .withStartTime(LocalTime.of(11,45)).withEndTime(LocalTime.of(13,15)).build(prog3P,'B');
        Group prog3PgroupC = new Group.Builder().hasLecturer(testUser).hasSlots(20).inRoom(r14).inTerm(ws1920).onDayOfWeek(DayOfWeek.THURSDAY)
                .withStartTime(LocalTime.of(14,15)).withEndTime(LocalTime.of(15,45)).build(prog3P,'C');
        Group prog3PgroupD = new Group.Builder().hasLecturer(testUser).hasSlots(20).inRoom(r14).inTerm(ws1920).onDayOfWeek(DayOfWeek.FRIDAY)
                .withStartTime(LocalTime.of(8,15)).withEndTime(LocalTime.of(9,45)).build(prog3P,'D');
        StudentAttendsCourse studentAttendsCourse = new StudentAttendsCourse.Builder().withStudent(testStudent).forCourse(cProgrammieren3).inTerm(ws1920).build();
        StudentAttendsGroup studentAttendsGroup = new StudentAttendsGroup.Builder(testStudent,prog3Vgroup).isParticipant(true).build();

        uni.getFieldOfStudies().add(dcsm);
        dcsm.setUniversity(uni);
        dcsm.getStudyProgram().add(medieninformatik);
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