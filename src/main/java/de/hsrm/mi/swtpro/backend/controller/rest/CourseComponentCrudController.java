package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.CourseComponentNotFoundException;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.service.repository.CourseComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class CourseComponentCrudController {

    @Autowired
    CourseComponentRepository courseComponentRepository;

    @PostMapping(path = "/coursecomponent/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseComponent createCourseComponent(@RequestBody CourseComponent coursecomponent) throws URISyntaxException {
        courseComponentRepository.save(coursecomponent);
        return coursecomponent;
    }

    @PostMapping(path = "/coursecomponent/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseComponent updateCourseComponent(@RequestBody CourseComponent coursecomponent) throws CourseComponentNotFoundException {
        return courseComponentRepository.save(coursecomponent);

    }

    @GetMapping(path = "/coursecomponent/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseComponent findCourseComponent(@RequestParam("coursecomponentID") long coursecomponentID) throws CourseComponentNotFoundException {
        if (courseComponentRepository.findById(coursecomponentID) != null) {
            return courseComponentRepository.findById(coursecomponentID);
        } else {
            throw new CourseComponentNotFoundException("CourseComponent not found");
        }
    }

    @DeleteMapping(path = "/coursecomponent/delete", consumes = "application/json")
    public void deleteCourseComponent(@RequestBody CourseComponent coursecomponent) throws CourseComponentNotFoundException {
        if (courseComponentRepository.findById(coursecomponent.getId()) != null) {
            courseComponentRepository.delete(coursecomponent);
        } else {
            throw new CourseComponentNotFoundException("CourseComponent not found");
        }
    }

}
