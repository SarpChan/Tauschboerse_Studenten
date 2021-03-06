package de.hsrm.mi.swtpro.backend.controller.rest.crud;


import de.hsrm.mi.swtpro.backend.controller.exceptions.StudentNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class StudentCrudController {

    @Autowired
    StudentRepository studentRepository;

    /**
     * Insert a Student object into the Model
     *
     * @param student recieves a Student class via POST request
     * @return Student object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/student/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student student) throws URISyntaxException {
        studentRepository.save(student);
        return student;
    }

    /**
     * Update a Student object into the Model
     * @param student recieves a Student class via POST request
     * @return Student object
     * @throws StudentNotFoundException
     */
    @PostMapping(path = "/student/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudent(@RequestBody Student student) throws StudentNotFoundException {
        return studentRepository.save(student);

    }

    /**
     * Find a Student object from the Model
     *
     * @param EnrollmentNumber recieves key from student
     * @return Student object
     * @throws StudentNotFoundException
     */
    @GetMapping(path = "/student/read/{enrolmentNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findStudent(@PathVariable int enrolmentNumber) throws StudentNotFoundException {
        if (studentRepository.findByEnrollmentNumber(enrolmentNumber).isPresent()) {
            return studentRepository.findByEnrollmentNumber(enrolmentNumber).get();
        } else {
            throw new StudentNotFoundException("Student not found");
        }

    }

    /**
     * Remove a Student object from the Model
     *
     * @param EnrollmentNumber recieves a Student id via DELETE request
     * @return void
     * @throws StudentNotFoundException
     */
    @DeleteMapping(path = "/student/delete/{enrolmentNumber}", consumes = "application/json")
    public void deleteStudent(@PathVariable int enrolmentNumber) throws StudentNotFoundException {
        if (studentRepository.findByEnrollmentNumber(enrolmentNumber).isPresent()) {
            studentRepository.delete(studentRepository.findByEnrollmentNumber(enrolmentNumber).get());
        } else {
            throw new StudentNotFoundException("Student not found");
        }
    }

}
