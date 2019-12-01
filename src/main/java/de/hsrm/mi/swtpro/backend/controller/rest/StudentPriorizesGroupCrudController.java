package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.StudentPriorizesGroupNotFoundException;
import de.hsrm.mi.swtpro.backend.model.StudentPriorizesGroup;
import de.hsrm.mi.swtpro.backend.service.repository.StudentPriorizesGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class StudentPriorizesGroupCrudController {

    @Autowired
    StudentPriorizesGroupRepository studentPriorizesGroupRepository;

    /**
     * Insert a StudentPriorizesGroup object into the Model
     *
     * @param studentPriorizesGroup recieves a StudentPriorizesGroup class via POST request
     * @return StudentPriorizesGroup object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/studentPriorizesGroup/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentPriorizesGroup createStudentPriorizesGroup(@RequestBody StudentPriorizesGroup studentPriorizesGroup) throws URISyntaxException {
        studentPriorizesGroupRepository.save(studentPriorizesGroup);
        return studentPriorizesGroup;
    }

    /**
     * Update a StudentPriorizesGroup object into the Model
     *
     * @param studentPriorizesGroup recieves a StudentPriorizesGroup class via POST request
     * @return StudentPriorizesGroup object
     * @throws StudentPriorizesGroupNotFoundException
     */
    @PostMapping(path = "/studentPriorizesGroup/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentPriorizesGroup updateStudentPriorizesGroup(@RequestBody StudentPriorizesGroup studentPriorizesGroup) throws StudentPriorizesGroupNotFoundException {
        return studentPriorizesGroupRepository.save(studentPriorizesGroup);

    }

    /**
     * Find a StudentPriorizesGroup object from the Model
     *
     * @param studentPriorizesGroupID recieves key from studentPriorizesGroup
     * @return StudentPriorizesGroup object
     * @throws StudentPriorizesGroupNotFoundException
     */
    @GetMapping(path = "/studentPriorizesGroup/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentPriorizesGroup findStudentPriorizesGroup(@RequestParam("studentPriorizesGroupID") long studentPriorizesGroupID) throws StudentPriorizesGroupNotFoundException {
        if (studentPriorizesGroupRepository.findById(studentPriorizesGroupID) != null) {
            return studentPriorizesGroupRepository.findById(studentPriorizesGroupID);
        } else {
            throw new StudentPriorizesGroupNotFoundException("StudentPriorizesGroup not found");
        }
    }

    /**
     * Remove a StudentPriorizesGroup object from the Model
     *
     * @param studentPriorizesGroup recieves a StudentPriorizesGroup class via POST request
     * @return StudentPriorizesGroup object or
     * @throws StudentPriorizesGroupNotFoundException
     */
    @DeleteMapping(path = "/studentPriorizesGroup/delete", consumes = "application/json")
    public void deleteStudentPriorizesGroup(@RequestBody StudentPriorizesGroup studentPriorizesGroup) throws StudentPriorizesGroupNotFoundException {
        if (studentPriorizesGroupRepository.findById(studentPriorizesGroup.getId()) != null) {
            studentPriorizesGroupRepository.delete(studentPriorizesGroup);
        } else {
            throw new StudentPriorizesGroupNotFoundException("StudentPriorizesGroup not found");
        }
    }

}
