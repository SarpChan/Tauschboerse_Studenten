package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.ModuleFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.CourseRepository;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/lists")
public class ListController {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @PostMapping(path = "/timetable", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Module> getModules(@RequestBody Filter[] filters) {
        List<Module> allModules = moduleRepository.findAll();
        ModuleFilterFactory filterFactory = ModuleFilterFactory.builder().filters(filters).build();
        allModules = filterFactory.filterModules(allModules);
        for (Module module: allModules) {
            module.setModulesInCurriculum(null);
            for (Course course:module.getCourses()) {
                course.setModules(null);
                course.setTerms(null);
                course.setStudentAttendsCourses(null);
                course.getOwner().setRoles(null);
                for(CourseComponent courseComponent:course.getCourseComponents()){
                    courseComponent.setCourse(null);
                    courseComponent.setStudentPassedExam(null);
                    for(Group group:courseComponent.getGroups()){
                        group.setStudents(null);
                        group.setPrioritizeGroups(null);
                        group.setTerm(null);
                        group.setCourseComponent(null);
                        group.getRoom().setBuilding(null);
                        group.getRoom().setGroups(null);
                        group.getLecturer().setGroups(null);
                        group.getLecturer().getUser().setRoles(null);
                    }
                }
            }
        }
        return allModules;
    }


}