package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.ModuleNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class ModuleCrudController {

    @Autowired
    ModuleRepository moduleRepository;

    @PostMapping(path = "/module/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Module createModule(@RequestBody Module module) throws URISyntaxException {
        moduleRepository.save(module);
        return module;
    }

    @PostMapping(path = "/module/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Module updateModule(@RequestBody Module module) throws ModuleNotFoundException {
        return moduleRepository.save(module);

    }

    @GetMapping(path = "/module/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Module findModule(@RequestParam("moduleID") long moduleID) throws ModuleNotFoundException {
        if (moduleRepository.findById(moduleID) != null) {
            return moduleRepository.findById(moduleID);
        } else {
            throw new ModuleNotFoundException("Module not found");
        }
    }

    @DeleteMapping(path = "/module/delete", consumes = "application/json")
    public void deleteModule(@RequestBody Module module) throws ModuleNotFoundException {
        if (moduleRepository.findById(module.getId()) != null) {
            moduleRepository.delete(module);
        } else {
            throw new ModuleNotFoundException("Module not found");
        }
    }

}
