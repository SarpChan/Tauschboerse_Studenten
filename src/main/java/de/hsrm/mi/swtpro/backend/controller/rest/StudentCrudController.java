package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class StudentCrudController<Student> {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping(path = "/Student/create", consumes = "application/json", produces =  MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student student) throws IOException {
        studentRepository.save(student);
        return student;
    }

    @PostMapping(path = "/Student/update", consumes = "application/json", produces =  MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudent(@RequestBody Optional<Student> student) throws IOException {
        Optional<Student> fromDB = studentRepository.findById(student);
        fromDB = student;
        studentRepository.save(fromDB);
        return student.orElse(null);
    }

    @GetMapping(path = "/Student/read", produces =  MediaType.APPLICATION_JSON_VALUE)
    public Optional<Student> findStudent(@RequestParam("matrNr") int matrNr) throws IOException {
        return studentRepository.findById(matrNr);
    }

    @PostMapping(path = "/Student/delete", produces =  MediaType.APPLICATION_JSON_VALUE)
    public Object deleteStudent(@RequestBody int matrNr) throws IOException {
       Object fromDB = studentRepository.findByID(matrNr);
       studentRepository.delete(fromDB);
       return fromDB;
    }
}
