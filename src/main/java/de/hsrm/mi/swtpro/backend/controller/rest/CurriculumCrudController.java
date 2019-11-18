package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.CurriculumNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.service.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class CurriculumCrudController {

    @Autowired
    CurriculumRepository curriculumRepository;

    @PostMapping(path = "/curriculum/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Curriculum createCurriculum(@RequestBody Curriculum curriculum) throws URISyntaxException {
        curriculumRepository.save(curriculum);
        return curriculum;
    }

    @PostMapping(path = "/curriculum/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Curriculum updateCurriculum(@RequestBody Curriculum curriculum) throws CurriculumNotFoundException {
        return curriculumRepository.save(curriculum);

    }

    @GetMapping(path = "/curriculum/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Curriculum findCurriculum(@RequestParam("curriculumID") long curriculumID) throws CurriculumNotFoundException {
        if (curriculumRepository.findById(curriculumID) != null) {
            return curriculumRepository.findById(curriculumID);
        } else {
            throw new CurriculumNotFoundException("Curriculum not found");
        }
    }

    @DeleteMapping(path = "/curriculum/delete", consumes = "application/json")
    public void deleteCurriculum(@RequestBody Curriculum curriculum) throws CurriculumNotFoundException {
        if (curriculumRepository.findById(curriculum.getId()) != null) {
            curriculumRepository.delete(curriculum);
        } else {
            throw new CurriculumNotFoundException("Curriculum not found");
        }
    }

}
