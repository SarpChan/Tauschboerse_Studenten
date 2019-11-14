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
import java.util.Optional;
import java.util.function.Supplier;

@RestController
@RequestMapping("/rest")
public class StudentCrudController<Student> {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping(path = "/Student/create", consumes = "application/json", produces =  MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student student) throws URISyntaxException {
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
    public Student findStudent(@RequestParam("matrNr") int matrNr) throws Throwable {
        return (Student)studentRepository.findById(matrNr)
                .orElseThrow(()->new StudentNotFoundException(matrNr + "not found"));
    }

    @PostMapping(path = "/Student/delete", produces =  MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudent(@RequestBody int matrNr) throws IOException {
       studentRepository.delete(matrNr);
    }
}
