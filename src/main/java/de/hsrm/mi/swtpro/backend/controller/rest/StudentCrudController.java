package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.StudentNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class StudentCrudController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping(path = "/student/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student student) throws URISyntaxException {
        studentRepository.save(student);
        return student;
    }

    @PostMapping(path = "/student/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudent(@RequestBody Student student) throws StudentNotFoundException {
        return studentRepository.save(student);

    }

    @GetMapping(path = "/student/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findStudent(@RequestParam("enrolementNumber") int enrolementNumber) throws StudentNotFoundException {
        if (studentRepository.findByEnrolementNumber(enrolementNumber) != null) {
            return studentRepository.findByEnrolementNumber(enrolementNumber);
        } else {
            throw new StudentNotFoundException("Student not found");
        }
    }

    @DeleteMapping(path = "/student/delete", consumes = "application/json")
    public void deleteStudent(@RequestBody Student student) throws StudentNotFoundException {
        if (studentRepository.findByEnrolementNumber(student.getEnrolementNumber()) != null) {
            studentRepository.delete(student);
        } else {
            throw new StudentNotFoundException("Student not found");
        }
    }

}
