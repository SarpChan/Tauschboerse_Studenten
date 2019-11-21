package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.CourseComponentNotFoundException;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.service.repository.CourseComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class CourseComponentCrudController {

    @Autowired
    CourseComponentRepository courseComponentRepository;

    /**
     * Insert a Course Component object into the Model
     *
     * @param coursecomponent recieves a Course Component class via POST request
     * @return Course Component object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/coursecomponent/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseComponent createCourseComponent(@RequestBody CourseComponent coursecomponent) throws URISyntaxException {
        courseComponentRepository.save(coursecomponent);
        return coursecomponent;
    }

    /**
     * Update a CourseComponent object into the Model
     * @param coursecomponent recieves a CourseComponent class via POST request
     * @return CourseComponent object
     * @throws CourseComponentNotFoundException
     */
    @PostMapping(path = "/coursecomponent/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseComponent updateCourseComponent(@RequestBody CourseComponent coursecomponent) throws CourseComponentNotFoundException {
        return courseComponentRepository.save(coursecomponent);

    }

    /**
     * Find a CourseComponent object from the Model
     * @param coursecomponentID recieves key from coursecomponent
     * @return CourseComponent object
     * @throws CourseComponentNotFoundException
     */
    @GetMapping(path = "/coursecomponent/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseComponent findCourseComponent(@RequestParam("coursecomponentID") long coursecomponentID) throws CourseComponentNotFoundException {
        if (courseComponentRepository.findById(coursecomponentID) != null) {
            return courseComponentRepository.findById(coursecomponentID);
        } else {
            throw new CourseComponentNotFoundException("CourseComponent not found");
        }
    }

    /**
     * Remove a CourseComponent object from the Model
     * @param coursecomponent recieves a CourseComponent class via POST request
     * @return CourseComponent object or
     * @throws CourseComponentNotFoundException
     */
    @DeleteMapping(path = "/coursecomponent/delete", consumes = "application/json")
    public void deleteCourseComponent(@RequestBody CourseComponent coursecomponent) throws CourseComponentNotFoundException {
        if (courseComponentRepository.findById(coursecomponent.getId()) != null) {
            courseComponentRepository.delete(coursecomponent);
        } else {
            throw new CourseComponentNotFoundException("CourseComponent not found");
        }
    }

}
