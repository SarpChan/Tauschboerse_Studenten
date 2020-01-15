package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.controller.login.security.JwtAuthentication;
import de.hsrm.mi.swtpro.backend.controller.login.security.TokenService;
import de.hsrm.mi.swtpro.backend.controller.rest.StudentCrudController;
import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.Role;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentAttendsCourse;
import de.hsrm.mi.swtpro.backend.model.TimetableModule;
import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/lists")
public class StudentTimetableController {

    UserRepository userRepository;
    TokenService tokenService;
    StudentRepository studentRepository;

    @Value("${security.jwt.token.header:Authorization}")
    private String tokenHeader;

    @GetMapping(path = "/student_timetable", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TimetableModule> getModules(HttpServletRequest request) {
        final String requestHeader = request.getHeader(this.tokenHeader);
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String  authenticationToken = requestHeader.substring(7);
            Optional<User> user = userRepository.findByLoginName(tokenService.getUsernameFromToken(authenticationToken));
            List<Role> roles;
            user.get().getRoles().forEach(role -> roles.add(role));
            Optional<Student> student = studentRepository.findById(id);
            user.get().getRoles().forEach(role -> studentRepository.findById(role.getId()));
            }
            
        }

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
                        .courseTitle(course.getTitle())
                        .courseTitleAbbreviation("Placeholder Abbreviation")
                        .roomNumber(group.getRoom().getNumber())
                        .build();
                        timetable.add(timetableModule);
                    }
                }
            }
        }
        return timetable;
    }


}