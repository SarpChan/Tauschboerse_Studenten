package de.hsrm.mi.swtpro.backend.controller.rest;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;
import de.hsrm.mi.swtpro.backend.model.Script;
import de.hsrm.mi.swtpro.backend.service.repository.PythonScriptRepository;

@RestController
@RequestMapping("/rest")
public class PythonScriptCrudController{
    @Autowired 
    PythonScriptRepository pyScriptRepo;

    @PostMapping(path="/pyScript/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Script createScript(@RequestBody Script script) throws URISyntaxException{
        pyScriptRepo.save(script);
        return script;
    }
    

 /**
     * Remove a Script object from the Model
     *
     * @param scriptId recieves a Module class via DELETE request
     * @return void
     */
    @DeleteMapping(path = "/script/delete/{scriptId}", consumes = "application/json")
    public void deleteModule(@PathVariable long scriptId) {
        if (pyScriptRepo.findById(scriptId).isPresent()) {
            pyScriptRepo.deleteById(scriptId);
        } else {
            
        }
    }
}