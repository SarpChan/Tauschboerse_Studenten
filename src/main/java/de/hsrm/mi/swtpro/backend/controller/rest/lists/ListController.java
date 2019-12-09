package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.ModuleFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.CourseRepository;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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

    /*@GetMapping(path = "/course", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCourse(@RequestBody Filter[] filters) {
        List<Course> allCourses = courseRepository.findAll();
        CourseFilterFactory filterFactory = CourseFilterFactory.builder().filters(filters).build();
        allCourses = filterFactory.filterCourses(allCourses);
        return allCourses;
    }*/
    @GetMapping(path = "/timetable", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Module> getModules(@RequestBody Filter[] filters) {
        List<Module> allModules = moduleRepository.findAll();
        ModuleFilterFactory filterFactory = ModuleFilterFactory.builder().filters(filters).build();
        allModules = filterFactory.filterModules(allModules);
        return allModules;
    }


}