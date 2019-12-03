package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.StudentPrioritizesGroupNotFoundException;
import de.hsrm.mi.swtpro.backend.model.StudentPrioritizesGroup;
import de.hsrm.mi.swtpro.backend.service.repository.StudentPrioritizesGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class StudentPrioritizesGroupCrudController {

    @Autowired
    StudentPrioritizesGroupRepository studentPrioritizesGroupRepository;

    /**
     * Insert a StudentPrioritizesGroup object into the Model
     *
     * @param studentPrioritizesGroup recieves a StudentPrioritizesGroup class via POST request
     * @return StudentPrioritizesGroup object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/studentPrioritizesGroup/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentPrioritizesGroup createStudentPrioritizesGroup(@RequestBody StudentPrioritizesGroup studentPrioritizesGroup) throws URISyntaxException {
        studentPrioritizesGroupRepository.save(studentPrioritizesGroup);
        return studentPrioritizesGroup;
    }

    /**
     * Update a StudentPrioritizesGroup object into the Model
     *
     * @param studentPrioritizesGroup recieves a StudentPrioritizesGroup class via POST request
     * @return StudentPrioritizesGroup object
     * @throws StudentPrioritizesGroupNotFoundException
     */
    @PostMapping(path = "/studentPrioritizesGroup/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentPrioritizesGroup updateStudentPrioritizesGroup(@RequestBody StudentPrioritizesGroup studentPrioritizesGroup) throws StudentPrioritizesGroupNotFoundException {
        return studentPrioritizesGroupRepository.save(studentPrioritizesGroup);

    }

    /**
     * Find a StudentPrioritizesGroup object from the Model
     *
     * @param studentPrioritizesGroupID recieves key from studentPrioritizesGroup
     * @return StudentPrioritizesGroup object
     * @throws StudentPrioritizesGroupNotFoundException
     */
    @GetMapping(path = "/studentPrioritizesGroup/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentPrioritizesGroup findStudentPrioritizesGroup(@RequestParam("studentPrioritizesGroupID") long studentPrioritizesGroupID) throws StudentPrioritizesGroupNotFoundException {
        if (studentPrioritizesGroupRepository.findById(studentPrioritizesGroupID).isPresent()) {
            return studentPrioritizesGroupRepository.findById(studentPrioritizesGroupID).get();
        } else {
            throw new StudentPrioritizesGroupNotFoundException("StudentPrioritizesGroup not found");
        }
    }

    /**
     * Remove a StudentPrioritizesGroup object from the Model
     *
     * @param studentPrioritizesGroup recieves a StudentPrioritizesGroup class via POST request
     * @return StudentPrioritizesGroup object or
     * @throws StudentPrioritizesGroupNotFoundException
     */
    @DeleteMapping(path = "/studentPrioritizesGroup/delete", consumes = "application/json")
    public void deleteStudentPrioritizesGroup(@RequestBody StudentPrioritizesGroup studentPrioritizesGroup) throws StudentPrioritizesGroupNotFoundException {
        if (studentPrioritizesGroupRepository.findById(studentPrioritizesGroup.getId()).isPresent()) {
            studentPrioritizesGroupRepository.delete(studentPrioritizesGroup);
        } else {
            throw new StudentPrioritizesGroupNotFoundException("StudentPrioritizesGroup not found");
        }
    }

}
