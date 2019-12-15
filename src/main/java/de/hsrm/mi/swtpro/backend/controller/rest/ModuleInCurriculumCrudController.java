package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.ModuleInCurriculumNotFoundException;
import de.hsrm.mi.swtpro.backend.model.ModuleInCurriculum;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleInCurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class ModuleInCurriculumCrudController {

    @Autowired
    ModuleInCurriculumRepository moduleInCurriculumRepository;

    /**
     * Insert a ModuleInCurriculum object into the Model
     *
     * @param moduleInCurriculum recieves a ModuleInCurriculum class via POST request
     * @return ModuleInCurriculum object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/moduleInCurriculum/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModuleInCurriculum createModuleInCurriculum(@RequestBody ModuleInCurriculum moduleInCurriculum) throws URISyntaxException {
        moduleInCurriculumRepository.save(moduleInCurriculum);
        return moduleInCurriculum;
    }

    /**
     * Update a ModuleInCurriculum object into the Model
     *
     * @param moduleInCurriculum recieves a ModuleInCurriculum class via POST request
     * @return ModuleInCurriculum object
     * @throws ModuleInCurriculumNotFoundException
     */
    @PostMapping(path = "/moduleInCurriculum/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModuleInCurriculum updateModuleInCurriculum(@RequestBody ModuleInCurriculum moduleInCurriculum) throws ModuleInCurriculumNotFoundException {
        return moduleInCurriculumRepository.save(moduleInCurriculum);

    }

    /**
     * Find a ModuleInCurriculum object from the Model
     *
     * @param moduleInCurriculumID recieves key from moduleInCurriculum
     * @return ModuleInCurriculum object
     * @throws ModuleInCurriculumNotFoundException
     */
    @GetMapping(path = "/moduleInCurriculum/read/{moduleInCurriculumID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModuleInCurriculum findModuleInCurriculum(@PathVariable long moduleInCurriculumID) throws ModuleInCurriculumNotFoundException {
        if (moduleInCurriculumRepository.findById(moduleInCurriculumID).isPresent()) {
            return moduleInCurriculumRepository.findById(moduleInCurriculumID).get();
        } else {
            throw new ModuleInCurriculumNotFoundException("ModuleInCurriculum not found");
        }
    }

    /**
     * Remove a ModuleInCurriculum object from the Model
     *
     * @param moduleInCurriculumID recieves a ModuleInCurriculum class via DELETE request
     * @return void
     * @throws ModuleInCurriculumNotFoundException
     */
    @DeleteMapping(path = "/moduleInCurriculum/delete/{moduleInCurriculumID}", consumes = "application/json")
    public void deleteModuleInCurriculum(@PathVariable long moduleInCurriculumID) throws ModuleInCurriculumNotFoundException {
        if (moduleInCurriculumRepository.findById(moduleInCurriculumID).isPresent()) {
            moduleInCurriculumRepository.deleteById(moduleInCurriculumID);
        } else {
            throw new ModuleInCurriculumNotFoundException("ModuleInCurriculum not found");
        }
    }

}
