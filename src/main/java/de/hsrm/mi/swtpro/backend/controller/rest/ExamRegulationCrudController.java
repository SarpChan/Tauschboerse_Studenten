package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.ExamRegulationNotFoundException;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.service.repository.ExamRegulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class ExamRegulationCrudController {

    @Autowired
    ExamRegulationRepository examregulationRepository;

    @PostMapping(path = "/examregulation/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamRegulation createExamRegulation(@RequestBody ExamRegulation examregulation) throws URISyntaxException {
        examregulationRepository.save(examregulation);
        return examregulation;
    }

    @PostMapping(path = "/examregulation/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamRegulation updateExamRegulation(@RequestBody ExamRegulation examregulation) throws ExamRegulationNotFoundException {
        return examregulationRepository.save(examregulation);

    }

    @GetMapping(path = "/examregulation/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamRegulation findExamRegulation(@RequestParam("examregulationID") long examregulationID) throws ExamRegulationNotFoundException {
        if (examregulationRepository.findById(examregulationID) != null) {
            return examregulationRepository.findById(examregulationID);
        } else {
            throw new ExamRegulationNotFoundException("ExamRegulation not found");
        }
    }

    @DeleteMapping(path = "/examregulation/delete", consumes = "application/json")
    public void deleteExamRegulation(@RequestBody ExamRegulation examregulation) throws ExamRegulationNotFoundException {
        if (examregulationRepository.findById(examregulation.getId()) != null) {
            examregulationRepository.delete(examregulation);
        } else {
            throw new ExamRegulationNotFoundException("ExamRegulation not found");
        }
    }

}
