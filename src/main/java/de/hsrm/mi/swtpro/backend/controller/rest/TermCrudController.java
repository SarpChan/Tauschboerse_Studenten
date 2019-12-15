package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.TermNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Term;
import de.hsrm.mi.swtpro.backend.service.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class TermCrudController {

    @Autowired
    TermRepository termRepository;

    /**
     * Insert a Term object into the Model
     *
     * @param term recieves a Term class via POST request
     * @return Term object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/term/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Term createTerm(@RequestBody Term term) throws URISyntaxException {
        termRepository.save(term);
        return term;
    }

    /**
     * Update a Term object into the Model
     * @param term recieves a Term class via POST request
     * @return Term object
     * @throws TermNotFoundException
     */
    @PostMapping(path = "/term/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Term updateTerm(@RequestBody Term term) throws TermNotFoundException {
        return termRepository.save(term);

    }

    /**
     * Find a Term object from the Model
     *
     * @param termID recieves key from term
     * @return Term object
     * @throws TermNotFoundException
     */
    @GetMapping(path = "/term/read/{termID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Term findTerm(@PathVariable long termID) throws TermNotFoundException {
        if (termRepository.findById(termID).isPresent()) {
            return termRepository.findById(termID).get();
        } else {
            throw new TermNotFoundException("Term not found");
        }
    }

    /**
     * Remove a Term object from the Model
     *
     * @param termID recieves a Term class via DELETE request
     * @return void
     * @throws TermNotFoundException
     */
    @DeleteMapping(path = "/term/delete/{termID}", consumes = "application/json")
    public void deleteTerm(@PathVariable long termID) throws TermNotFoundException {
        if (termRepository.findById(termID).isPresent()) {
            termRepository.deleteById(termID);
        } else {
            throw new TermNotFoundException("Term not found");
        }
    }

}
