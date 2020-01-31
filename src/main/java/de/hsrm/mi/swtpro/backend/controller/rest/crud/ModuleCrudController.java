package de.hsrm.mi.swtpro.backend.controller.rest.crud;


import de.hsrm.mi.swtpro.backend.controller.exceptions.ModuleNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class ModuleCrudController {

    @Autowired
    ModuleRepository moduleRepository;

    /**
     * Insert a Module object into the Model
     *
     * @param module recieves a Module class via POST request
     * @return Module object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/module/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Module createModule(@RequestBody Module module) throws URISyntaxException {
        moduleRepository.save(module);
        return module;
    }

    /**
     * Update a Module object into the Model
     * @param module recieves a Module class via POST request
     * @return Module object
     * @throws ModuleNotFoundException
     */
    @PostMapping(path = "/module/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Module updateModule(@RequestBody Module module) throws ModuleNotFoundException {
        return moduleRepository.save(module);

    }

    /**
     * Find a Module object from the Model
     *
     * @param moduleID recieves key from module
     * @return Module object
     * @throws ModuleNotFoundException
     */
    @GetMapping(path = "/module/read/{moduleID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Module findModule(@PathVariable long moduleID) throws ModuleNotFoundException {
        if (moduleRepository.findById(moduleID).isPresent()) {
            return moduleRepository.findById(moduleID).get();
        } else {
            throw new ModuleNotFoundException("Module not found");
        }
    }

    /**
     * Remove a Module object from the Model
     *
     * @param moduleID recieves a Module class via DELETE request
     * @return void
     * @throws ModuleNotFoundException
     */
    @DeleteMapping(path = "/module/delete/{moduleID}", consumes = "application/json")
    public void deleteModule(@PathVariable long moduleID) throws ModuleNotFoundException {
        if (moduleRepository.findById(moduleID).isPresent()) {
            moduleRepository.deleteById(moduleID);
        } else {
            throw new ModuleNotFoundException("Module not found");
        }
    }

}
