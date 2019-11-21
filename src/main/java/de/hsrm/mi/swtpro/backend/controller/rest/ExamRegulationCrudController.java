package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.ExamRegulationNotFoundException;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.service.repository.ExamRegulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class ExamRegulationCrudController {

    @Autowired
    ExamRegulationRepository examregulationRepository;

    /**
     * Insert a ExamRegulation object into the Model
     *
     * @param examregulation recieves a ExamRegulation class via POST request
     * @return ExamRegulation object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/examregulation/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamRegulation createExamRegulation(@RequestBody ExamRegulation examregulation) throws URISyntaxException {
        examregulationRepository.save(examregulation);
        return examregulation;
    }

    /**
     * Update a ExamRegulation object into the Model
     * @param examregulation recieves a ExamRegulation class via POST request
     * @return ExamRegulation object
     * @throws ExamRegulationNotFoundException
     */
    @PostMapping(path = "/examregulation/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamRegulation updateExamRegulation(@RequestBody ExamRegulation examregulation) throws ExamRegulationNotFoundException {
        return examregulationRepository.save(examregulation);

    }

    /**
     * Find a ExamRegulation object from the Model
     * @param examregulationID recieves key from examregulation
     * @return ExamRegulation object
     * @throws ExamRegulationNotFoundException
     */
    @GetMapping(path = "/examregulation/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamRegulation findExamRegulation(@RequestParam("examregulationID") long examregulationID) throws ExamRegulationNotFoundException {
        if (examregulationRepository.findById(examregulationID) != null) {
            return examregulationRepository.findById(examregulationID);
        } else {
            throw new ExamRegulationNotFoundException("ExamRegulation not found");
        }
    }

    /**
     * Remove a ExamRegulation object from the Model
     * @param examregulation recieves a ExamRegulation class via POST request
     * @return ExamRegulation object or
     * @throws ExamRegulationNotFoundException
     */
    @DeleteMapping(path = "/examregulation/delete", consumes = "application/json")
    public void deleteExamRegulation(@RequestBody ExamRegulation examregulation) throws ExamRegulationNotFoundException {
        if (examregulationRepository.findById(examregulation.getId()) != null) {
            examregulationRepository.delete(examregulation);
        } else {
            throw new ExamRegulationNotFoundException("ExamRegulation not found");
        }
    }

}
