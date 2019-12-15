package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.FieldOfStudyNotFoundException;
import de.hsrm.mi.swtpro.backend.model.FieldOfStudy;
import de.hsrm.mi.swtpro.backend.service.repository.FieldOfStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class FieldOfStudyCrudController {

    @Autowired
    FieldOfStudyRepository fieldofstudyRepository;

    /**
     * Insert a FieldOfStudy object into the Model
     *
     * @param fieldofstudy recieves a FieldOfStudy class via POST request
     * @return FieldOfStudy object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/fieldofstudy/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldOfStudy createFieldOfStudy(@RequestBody FieldOfStudy fieldofstudy) throws URISyntaxException {
        fieldofstudyRepository.save(fieldofstudy);
        return fieldofstudy;
    }

    /**
     * Update a FieldOfStudy object into the Model
     * @param fieldofstudy recieves a FieldOfStudy class via POST request
     * @return FieldOfStudy object
     * @throws FieldOfStudyNotFoundException
     */
    @PostMapping(path = "/fieldofstudy/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldOfStudy updateFieldOfStudy(@RequestBody FieldOfStudy fieldofstudy) throws FieldOfStudyNotFoundException {
        return fieldofstudyRepository.save(fieldofstudy);

    }

    /**
     * Find a FieldOfStudy object from the Model
     *
     * @param fieldofstudyID recieves key from fieldofstudy
     * @return FieldOfStudy object
     * @throws FieldOfStudyNotFoundException
     */
    @GetMapping(path = "/fieldofstudy/read/{fieldofstudyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldOfStudy findFieldOfStudy(@PathVariable long fieldofstudyID) throws FieldOfStudyNotFoundException {
        if (fieldofstudyRepository.findById(fieldofstudyID).isPresent()) {
            return fieldofstudyRepository.findById(fieldofstudyID).get();
        } else {
            throw new FieldOfStudyNotFoundException("FieldOfStudy not found");
        }
    }

    /**
     * Remove a FieldOfStudy object from the Model
     *
     * @param fieldofstudyID recieves a FieldOfStudy class via DELETE request
     * @return void
     * @throws FieldOfStudyNotFoundException
     */
    @DeleteMapping(path = "/fieldofstudy/delete/{fieldofstudyID}", consumes = "application/json")
    public void deleteFieldOfStudy(@PathVariable long fieldofstudyID) throws FieldOfStudyNotFoundException {
        if (fieldofstudyRepository.findById(fieldofstudyID).isPresent()) {
            fieldofstudyRepository.deleteById(fieldofstudyID);
        } else {
            throw new FieldOfStudyNotFoundException("FieldOfStudy not found");
        }
    }

}
