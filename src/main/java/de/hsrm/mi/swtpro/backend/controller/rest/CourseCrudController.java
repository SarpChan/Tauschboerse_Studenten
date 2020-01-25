package de.hsrm.mi.swtpro.backend.controller.rest;

import de.hsrm.mi.swtpro.backend.controller.exceptions.CourseComponentNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.service.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class CourseCrudController {

    @Autowired
    CourseRepository courseRepository;

    /**
     * Insert a Course object into the Model
     *
     * @param coursecomponent recieves a Course Component class via POST request
     * @return Course Component object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/course/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course createCourseComponent(@RequestBody Course course) throws URISyntaxException {
        courseRepository.save(course);
        return course;
    }

    /**
     * Update a CourseComponent object into the Model
     * @param coursecomponent recieves a CourseComponent class via POST request
     * @return CourseComponent object
     * @throws CourseComponentNotFoundException
     */
    @PostMapping(path = "/course/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course updateCourse(@RequestBody Course course) throws CourseComponentNotFoundException {
        courseRepository.save(course);
        return course;
         

    }

    /**
     * Find a CourseComponent object from the Model
     *
     * @param coursecomponentID recieves key from coursecomponent
     * @return CourseComponent object
     * @throws CourseComponentNotFoundException
     */
    @GetMapping(path = "/course/read/{courseID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course findCourse(@PathVariable long courseID) throws CourseComponentNotFoundException {
        if (courseRepository.findById(courseID).isPresent()) {
            return courseRepository.findById(courseID).get();
        } else {
            throw new CourseComponentNotFoundException("Course not found");
        }
    }

    /**
     * Remove a CourseComponent object from the Model
     *
     * @param coursecomponentID recieves a CourseComponent id via DELETE request
     * @return void
     * @throws CourseComponentNotFoundException
     */
    @DeleteMapping(path = "/course/delete/{courseID}", consumes = "application/json")
    public void deleteCourse(@PathVariable long courseID) throws CourseComponentNotFoundException {
        if (courseRepository.findById(courseID).isPresent()) {
            courseRepository.deleteById(courseID);
        } else {
            throw new CourseComponentNotFoundException("Course not found");
        }
    }

}
