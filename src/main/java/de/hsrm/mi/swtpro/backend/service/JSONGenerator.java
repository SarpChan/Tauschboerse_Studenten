package de.hsrm.mi.swtpro.backend.service;

import  de.hsrm.mi.swtpro.backend.model.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

@Component
public class JSONGenerator {

    @PostConstruct
    private void init() {
        University uni = new University("Hochschule RheinMain", "Kurt-Schuhmacher-Ring 18");
        Campus ude = new Campus("Unter den Eichen", "Unter den Eichen 6", uni);
        uni.getCampus().add(ude);
        System.out.println(ude.getId());
        Building dBuilding = new Building("Gebäude D", ude);
        ude.getBuildings().add(dBuilding);
        Room r14 = new Room(14, 60, dBuilding);
        dBuilding.getRooms().add(r14);

        Term ws1920 = new Term.Builder().withStartDate(Date.valueOf("2019-10-01")).withEndDate(Date.valueOf("2020-03-31")).inPeriod(1).build();

        ExamRegulation po2017 = new ExamRegulation.Builder(Date.valueOf("2017-10-01")).build();

        User testUser = new User("Test","User","testUser","test",true);
        Student testStudent = new Student("test","Student","testStudent","test",false
                ,123456,ws1920,"test@test.com",po2017);

        FieldOfStudy dcsm = new FieldOfStudy("DCSM");
        StudyProgram medieninformatik = new StudyProgram.Builder("Medieninformatik","Bachlor").withExamRegulation(po2017).build();
        Curriculum curriculum = new Curriculum.Builder(po2017).forTerm(1).build();
        Module mProgrammieren3 = new Module.Builder("Programmieren 3").inPeriod(1).inCurriculum(curriculum).hasCreditPoints(5).build();
        Course cProgrammieren3 = new Course.Builder("Programmieren 3").withOwner(testUser).build();
        CourseComponent prog3V = new CourseComponent.Builder(cProgrammieren3,CourseType.LECTURE).hasCreditPoints(3).build();
        Group prog3Vgroup = new Group.Builder().hasLecturer(testUser).hasSlots(100).inRoom(r14).inTerm(ws1920).onDayOfWeek(DayOfWeek.MONDAY)
                .withStartTime(LocalTime.of(10,00)).withEndTime(LocalTime.of(11,30)).build(prog3V,'A');

        uni.getFieldOfStudies().add(dcsm);
        dcsm.setUniversity(uni);
        dcsm.getStudyProgram().add(medieninformatik);
        po2017.getCurricula().add(curriculum);
        curriculum.getModules().add(mProgrammieren3);
        curriculum.setExamRegulation(po2017);
        mProgrammieren3.addCourse(cProgrammieren3);
        //mProgrammieren3.addCurriuculum(curriculum);
        cProgrammieren3.addCourseComponent(prog3V);
        cProgrammieren3.getModules().add(mProgrammieren3);
        prog3V.addGroup(prog3Vgroup);
        prog3Vgroup.addStudent(testStudent);

        ws1920.addCourse(cProgrammieren3);
        po2017.setStudyProgram(medieninformatik);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("uni.json"), uni);
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("JSON new File Error");
        }
    }
}