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
     *
     * @param examregulationID recieves key from examregulation
     * @return ExamRegulation object
     * @throws ExamRegulationNotFoundException
     */
    @GetMapping(path = "/examregulation/read/{examregulationID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamRegulation findExamRegulation(@PathVariable long examregulationID) throws ExamRegulationNotFoundException {
        if (examregulationRepository.findById(examregulationID).isPresent()) {
            return examregulationRepository.findById(examregulationID).get();
        } else {
            throw new ExamRegulationNotFoundException("ExamRegulation not found");
        }
    }

    /**
     * Remove a ExamRegulation object from the Model
     *
     * @param examregulationID recieves a ExamRegulation class via DELETE request
     * @return void
     * @throws ExamRegulationNotFoundException
     */
    @DeleteMapping(path = "/examregulation/delete/{examregulationID}", consumes = "application/json")
    public void deleteExamRegulation(@PathVariable long examregulationID) throws ExamRegulationNotFoundException {
        if (examregulationRepository.findById(examregulationID).isPresent()) {
            examregulationRepository.deleteById(examregulationID);
        } else {
            throw new ExamRegulationNotFoundException("ExamRegulation not found");
        }
    }

}
