package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.StudentAttendsCourseNotFoundException;
import de.hsrm.mi.swtpro.backend.model.StudentAttendsCourse;
import de.hsrm.mi.swtpro.backend.service.repository.StudentAttendsCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class StudentAttendsCourseCrudController {

    @Autowired
    StudentAttendsCourseRepository studentAttendsCourseRepository;

    /**
     * Insert a StudentAttendsCourse object into the Model
     *
     * @param studentAttendsCourse recieves a StudentAttendsCourse class via POST request
     * @return StudentAttendsCourse object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/studentAttendsCourse/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentAttendsCourse createStudentAttendsCourse(@RequestBody StudentAttendsCourse studentAttendsCourse) throws URISyntaxException {
        studentAttendsCourseRepository.save(studentAttendsCourse);
        return studentAttendsCourse;
    }

    /**
     * Update a StudentAttendsCourse object into the Model
     *
     * @param studentAttendsCourse recieves a StudentAttendsCourse class via POST request
     * @return StudentAttendsCourse object
     * @throws StudentAttendsCourseNotFoundException
     */
    @PostMapping(path = "/studentAttendsCourse/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentAttendsCourse updateStudentAttendsCourse(@RequestBody StudentAttendsCourse studentAttendsCourse) throws StudentAttendsCourseNotFoundException {
        return studentAttendsCourseRepository.save(studentAttendsCourse);

    }

    /**
     * Find a StudentAttendsCourse object from the Model
     *
     * @param studentAttendsCourseID recieves key from studentAttendsCourse
     * @return StudentAttendsCourse object
     * @throws StudentAttendsCourseNotFoundException
     */
    @GetMapping(path = "/studentAttendsCourse/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentAttendsCourse findStudentAttendsCourse(@RequestParam("studentAttendsCourseID") long studentAttendsCourseID) throws StudentAttendsCourseNotFoundException {
        if (studentAttendsCourseRepository.findById(studentAttendsCourseID) != null) {
            return studentAttendsCourseRepository.findById(studentAttendsCourseID);
        } else {
            throw new StudentAttendsCourseNotFoundException("StudentAttendsCourse not found");
        }
    }

    /**
     * Remove a StudentAttendsCourse object from the Model
     *
     * @param studentAttendsCourse recieves a StudentAttendsCourse class via POST request
     * @return StudentAttendsCourse object or
     * @throws StudentAttendsCourseNotFoundException
     */
    @DeleteMapping(path = "/studentAttendsCourse/delete", consumes = "application/json")
    public void deleteStudentAttendsCourse(@RequestBody StudentAttendsCourse studentAttendsCourse) throws StudentAttendsCourseNotFoundException {
        if (studentAttendsCourseRepository.findById(studentAttendsCourse.getId()) != null) {
            studentAttendsCourseRepository.delete(studentAttendsCourse);
        } else {
            throw new StudentAttendsCourseNotFoundException("StudentAttendsCourse not found");
        }
    }

}
