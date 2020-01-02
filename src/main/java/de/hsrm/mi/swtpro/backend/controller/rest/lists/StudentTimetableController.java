package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.controller.rest.StudentCrudController;
import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentAttendsCourse;
import de.hsrm.mi.swtpro.backend.model.TimetableModule;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/lists")
public class StudentTimetableController {

    StudentCrudController studentCrudController;

    @GetMapping(path = "/student_timetable", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TimetableModule> getModules(@RequestHeader("token") long token) {
        Student student = studentCrudController.findStudent((int)token);
        Set<StudentAttendsCourse> studentAttendsCourse = student.getAttendCourses();
        List<Module> allModules = new ArrayList<Module>();
        studentAttendsCourse.forEach(course -> course.getCourse().getModules().forEach(module -> allModules.add(module)));
        List<TimetableModule> timetable = new ArrayList<TimetableModule>();
        for(Module module: allModules){
            for(Course course: module.getCourses()){
                for(CourseComponent courseComponent : course.getCourseComponents()){
                    for(Group group: courseComponent.getGroups()){
                        TimetableModule timetableModule = TimetableModule.builder()
                        .groupID(group.getId())
                        .groupChar(group.getGroupChar())
                        .dayOfWeek(group.getDayOfWeek())
                        .startTime(group.getStartTime())
                        .endTime(group.getEndTime())
                        .lecturerName(group.getLecturer().getUser().getLastName())
                        .lecturerNameAbbreviation("Placeholder Abbreviation")
                        .courseComponentID(courseComponent.getId())
                        .courseType(courseComponent.getType())
                        .moduleTitle(module.getTitle())
                        .moduleTitleAbbreviation("Placeholder Abbreviation")
                        .build();
                        timetable.add(timetableModule);
                    }
                }
            }
        }
        return timetable;
    }


}