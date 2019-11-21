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
     * @param fieldofstudyID recieves key from fieldofstudy
     * @return FieldOfStudy object
     * @throws FieldOfStudyNotFoundException
     */
    @GetMapping(path = "/fieldofstudy/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldOfStudy findFieldOfStudy(@RequestParam("fieldofstudyID") long fieldofstudyID) throws FieldOfStudyNotFoundException {
        if (fieldofstudyRepository.findById(fieldofstudyID) != null) {
            return fieldofstudyRepository.findById(fieldofstudyID);
        } else {
            throw new FieldOfStudyNotFoundException("FieldOfStudy not found");
        }
    }

    /**
     * Remove a FieldOfStudy object from the Model
     * @param fieldofstudy recieves a FieldOfStudy class via POST request
     * @return FieldOfStudy object or
     * @throws FieldOfStudyNotFoundException
     */
    @DeleteMapping(path = "/fieldofstudy/delete", consumes = "application/json")
    public void deleteFieldOfStudy(@RequestBody FieldOfStudy fieldofstudy) throws FieldOfStudyNotFoundException {
        if (fieldofstudyRepository.findById(fieldofstudy.getId()) != null) {
            fieldofstudyRepository.delete(fieldofstudy);
        } else {
            throw new FieldOfStudyNotFoundException("FieldOfStudy not found");
        }
    }

}
