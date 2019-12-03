package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.StudentPassedExamNotFoundException;
import de.hsrm.mi.swtpro.backend.model.StudentPassedExam;
import de.hsrm.mi.swtpro.backend.service.repository.StudentPassedExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class StudentPassedExamCrudController {

    @Autowired
    StudentPassedExamRepository studentPassedExamRepository;

    /**
     * Insert a StudentPassedExam object into the Model
     *
     * @param studentPassedExam recieves a StudentPassedExam class via POST request
     * @return StudentPassedExam object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/studentPassedExam/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentPassedExam createStudentPassedExam(@RequestBody StudentPassedExam studentPassedExam) throws URISyntaxException {
        studentPassedExamRepository.save(studentPassedExam);
        return studentPassedExam;
    }

    /**
     * Update a StudentPassedExam object into the Model
     *
     * @param studentPassedExam recieves a StudentPassedExam class via POST request
     * @return StudentPassedExam object
     * @throws StudentPassedExamNotFoundException
     */
    @PostMapping(path = "/studentPassedExam/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentPassedExam updateStudentPassedExam(@RequestBody StudentPassedExam studentPassedExam) throws StudentPassedExamNotFoundException {
        return studentPassedExamRepository.save(studentPassedExam);

    }

    /**
     * Find a StudentPassedExam object from the Model
     *
     * @param studentPassedExamID recieves key from studentPassedExam
     * @return StudentPassedExam object
     * @throws StudentPassedExamNotFoundException
     */
    @GetMapping(path = "/studentPassedExam/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentPassedExam findStudentPassedExam(@RequestParam("studentPassedExamID") long studentPassedExamID) throws StudentPassedExamNotFoundException {
        if (studentPassedExamRepository.findById(studentPassedExamID).isPresent()) {
            return studentPassedExamRepository.findById(studentPassedExamID).get();
        } else {
            throw new StudentPassedExamNotFoundException("StudentPassedExam not found");
        }
    }

    /**
     * Remove a StudentPassedExam object from the Model
     *
     * @param studentPassedExam recieves a StudentPassedExam class via POST request
     * @return StudentPassedExam object or
     * @throws StudentPassedExamNotFoundException
     */
    @DeleteMapping(path = "/studentPassedExam/delete", consumes = "application/json")
    public void deleteStudentPassedExam(@RequestBody StudentPassedExam studentPassedExam) throws StudentPassedExamNotFoundException {
        if (studentPassedExamRepository.findById(studentPassedExam.getId()).isPresent()) {
            studentPassedExamRepository.delete(studentPassedExam);
        } else {
            throw new StudentPassedExamNotFoundException("StudentPassedExam not found");
        }
    }

}
