package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.CurriculumNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.service.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class CurriculumCrudController {

    @Autowired
    CurriculumRepository curriculumRepository;

    /**
     * Insert a Curriculum object into the Model
     *
     * @param curriculum recieves a Curriculum class via POST request
     * @return Curriculum object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/curriculum/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Curriculum createCurriculum(@RequestBody Curriculum curriculum) throws URISyntaxException {
        curriculumRepository.save(curriculum);
        return curriculum;
    }

    /**
     * Update a Curriculum object into the Model
     * @param curriculum recieves a Curriculum class via POST request
     * @return Curriculum object
     * @throws CurriculumNotFoundException
     */
    @PostMapping(path = "/curriculum/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Curriculum updateCurriculum(@RequestBody Curriculum curriculum) throws CurriculumNotFoundException {
        return curriculumRepository.save(curriculum);

    }

    /**
     * Find a Curriculum object from the Model
     * @param curriculumID recieves key from curriculum
     * @return Curriculum object
     * @throws CurriculumNotFoundException
     */
    @GetMapping(path = "/curriculum/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Curriculum findCurriculum(@RequestParam("curriculumID") long curriculumID) throws CurriculumNotFoundException {
        if (curriculumRepository.findById(curriculumID).isPresent()) {
            return curriculumRepository.findById(curriculumID).get();
        } else {
            throw new CurriculumNotFoundException("Curriculum not found");
        }
    }

    /**
     * Remove a Curriculum object from the Model
     * @param curriculum recieves a Curriculum class via POST request
     * @return Curriculum object or
     * @throws CurriculumNotFoundException
     */
    @DeleteMapping(path = "/curriculum/delete", consumes = "application/json")
    public void deleteCurriculum(@RequestBody Curriculum curriculum) throws CurriculumNotFoundException {
        if (curriculumRepository.findById(curriculum.getId()).isPresent()) {
            curriculumRepository.delete(curriculum);
        } else {
            throw new CurriculumNotFoundException("Curriculum not found");
        }
    }

}
