package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.LecturerNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Lecturer;
import de.hsrm.mi.swtpro.backend.service.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class LecturerCrudController {

    @Autowired
    LecturerRepository lecturerRepository;

    /**
     * Insert a Lecturer object into the Model
     *
     * @param lecturer recieves a Lecturer class via POST request
     * @return Lecturer object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/lecturer/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Lecturer createLecturer(@RequestBody Lecturer lecturer) throws URISyntaxException {
        lecturerRepository.save(lecturer);
        return lecturer;
    }

    /**
     * Update a Lecturer object into the Model
     *
     * @param lecturer recieves a Lecturer class via POST request
     * @return Lecturer object
     * @throws LecturerNotFoundException
     */
    @PostMapping(path = "/lecturer/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Lecturer updateLecturer(@RequestBody Lecturer lecturer) throws LecturerNotFoundException {
        return lecturerRepository.save(lecturer);

    }

    /**
     * Find a Lecturer object from the Model
     *
     * @param lecturerID recieves key from lecturer
     * @return Lecturer object
     * @throws LecturerNotFoundException
     */
    @GetMapping(path = "/lecturer/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Lecturer findLecturer(@RequestParam("lecturerID") long lecturerID) throws LecturerNotFoundException {
        if (lecturerRepository.findById(lecturerID) != null) {
            return lecturerRepository.findById(lecturerID);
        } else {
            throw new LecturerNotFoundException("Lecturer not found");
        }
    }

    /**
     * Remove a Lecturer object from the Model
     *
     * @param lecturer recieves a Lecturer class via POST request
     * @return Lecturer object or
     * @throws LecturerNotFoundException
     */
    @DeleteMapping(path = "/lecturer/delete", consumes = "application/json")
    public void deleteLecturer(@RequestBody Lecturer lecturer) throws LecturerNotFoundException {
        if (lecturerRepository.findById(lecturer.getId()) != null) {
            lecturerRepository.delete(lecturer);
        } else {
            throw new LecturerNotFoundException("Lecturer not found");
        }
    }

}
