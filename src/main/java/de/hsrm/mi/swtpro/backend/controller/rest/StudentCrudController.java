package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.StudentNotFoundException;
import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.repositories.StudentRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.Optional;
import java.util.function.Supplier;

@RestController
@RequestMapping("/rest")
public class StudentCrudController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping(path = "/student/create", consumes = "application/json", produces =  MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student student) throws URISyntaxException {
        studentRepository.save(student);
        return student;
    }

    @PostMapping(path = "/student/update", consumes = "application/json", produces =  MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudent(@RequestBody Student student) throws IOException {
        studentRepository.save(student);
        return student;
    }

    @GetMapping(path = "/student/read", produces =  MediaType.APPLICATION_JSON_VALUE)
    public Student findStudent(@RequestParam("matrNr") int matrNr) throws StudentNotFoundException {
        studentRepository.findByEnrolementNumber(matrNr);
       // Term t = new Term(2020,"WS", new Date(2020, 10, 01), new Date(2021, 03, 31));
        Student test = new Student("peter", "nicht lustig",  "Mi");
        //test.setEnrolementTerm(t);
        return  test;
    }

    @PostMapping(path = "/student/delete", produces =  MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudent(@RequestBody Student student) throws IOException {
        studentRepository.delete(student);
    }
}
