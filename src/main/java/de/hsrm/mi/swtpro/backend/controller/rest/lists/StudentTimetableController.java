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
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGetter;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGenerator;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGetter;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/lists")
public class StudentTimetableController {


    @Autowired
    TokenService tokenService;
    @Autowired
    ServiceGenerator serviceGenerator;
    @Autowired
    ServiceGetter serviceGetter;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository; 

    @GetMapping(path = "/student_timetable", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TimetableModule> getModules(HttpServletRequest request) {
        String username = tokenService.getUsernameFromRequest(request);
        Student student = serviceGetter.getStudentFromUsername(username);
        Set<StudentAttendsCourse> studentAttendsCourse = student.getAttendCourses();
        List<Module> allModules = new ArrayList<Module>();
        for(StudentAttendsCourse sAc : studentAttendsCourse){
            for(Module module: sAc.getCourse().getModules()){
                allModules.add(module);
            }
        }     
        return serviceGenerator.timetableModuleFromModules(allModules);
    }
}
