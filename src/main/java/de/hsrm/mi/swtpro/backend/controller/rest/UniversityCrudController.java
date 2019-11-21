package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.UniversityNotFoundException;
import de.hsrm.mi.swtpro.backend.model.University;
import de.hsrm.mi.swtpro.backend.service.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class UniversityCrudController {

    @Autowired
    UniversityRepository universityRepository;

    /**
     * Insert a University object into the Model
     *
     * @param university recieves a University class via POST request
     * @return University object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/university/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public University createUniversity(@RequestBody University university) throws URISyntaxException {
        universityRepository.save(university);
        return university;
    }

    @PostMapping(path = "/university/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public University updateUniversity(@RequestBody University university) throws UniversityNotFoundException {
        return universityRepository.save(university);

    }

    @GetMapping(path = "/university/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public University findUniversity(@RequestParam("universityName") String universityName) throws UniversityNotFoundException {
        if (universityRepository.findByName(universityName) != null) {
            return universityRepository.findByName(universityName);
        } else {
            throw new UniversityNotFoundException("University not found");
        }
    }

    /**
     * Remove a University object from the Model
     * @param university recieves a University class via POST request
     * @return University object or
     * @throws UniversityNotFoundException
     */
    @DeleteMapping(path = "/university/delete", consumes = "application/json")
    public void deleteUniversity(@RequestBody University university) throws UniversityNotFoundException {
        if (universityRepository.findByName(university.getName()) != null) {
            universityRepository.delete(university);
        } else {
            throw new UniversityNotFoundException("University not found");
        }
    }

}
