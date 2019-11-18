package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.FieldOfStudyNotFoundException;
import de.hsrm.mi.swtpro.backend.model.FieldOfStudy;
import de.hsrm.mi.swtpro.backend.service.repository.FieldOfStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class FieldOfStudyCrudController {

    @Autowired
    FieldOfStudyRepository fieldofstudyRepository;

    @PostMapping(path = "/fieldofstudy/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldOfStudy createFieldOfStudy(@RequestBody FieldOfStudy fieldofstudy) throws URISyntaxException {
        fieldofstudyRepository.save(fieldofstudy);
        return fieldofstudy;
    }

    @PostMapping(path = "/fieldofstudy/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldOfStudy updateFieldOfStudy(@RequestBody FieldOfStudy fieldofstudy) throws FieldOfStudyNotFoundException {
        return fieldofstudyRepository.save(fieldofstudy);

    }

    @GetMapping(path = "/fieldofstudy/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldOfStudy findFieldOfStudy(@RequestParam("fieldofstudyID") long fieldofstudyID) throws FieldOfStudyNotFoundException {
        if (fieldofstudyRepository.findById(fieldofstudyID) != null) {
            return fieldofstudyRepository.findById(fieldofstudyID);
        } else {
            throw new FieldOfStudyNotFoundException("FieldOfStudy not found");
        }
    }

    @DeleteMapping(path = "/fieldofstudy/delete", consumes = "application/json")
    public void deleteFieldOfStudy(@RequestBody FieldOfStudy fieldofstudy) throws FieldOfStudyNotFoundException {
        if (fieldofstudyRepository.findById(fieldofstudy.getId()) != null) {
            fieldofstudyRepository.delete(fieldofstudy);
        } else {
            throw new FieldOfStudyNotFoundException("FieldOfStudy not found");
        }
    }

}
