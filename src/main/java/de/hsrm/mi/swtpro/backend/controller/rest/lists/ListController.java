package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/lists")
public class ListController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping(path = "/course", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCourse(@RequestBody Object filter) {
        ArrayList<Course> allCourses = new ArrayList<>(courseRepository.findAll());
        return allCourses;
    }

}
