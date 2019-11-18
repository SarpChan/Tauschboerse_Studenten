package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.TermNotFoundException;
import de.hsrm.mi.swtpro.backend.controller.exceptions.TermNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Term;
import de.hsrm.mi.swtpro.backend.service.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class TermCrudController {

    @Autowired
    TermRepository termRepository;

    @PostMapping(path = "/term/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Term createTerm(@RequestBody Term term) throws URISyntaxException {
        termRepository.save(term);
        return term;
    }

    @PostMapping(path = "/term/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Term updateTerm(@RequestBody Term term) throws TermNotFoundException {
        return termRepository.save(term);

    }

    @GetMapping(path = "/term/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Term findTerm(@RequestParam("termID") long termID) throws TermNotFoundException {
        if (termRepository.findById(termID) != null) {
            return termRepository.findById(termID);
        } else {
            throw new TermNotFoundException("Term not found");
        }
    }

    @DeleteMapping(path = "/term/delete", consumes = "application/json")
    public void deleteTerm(@RequestBody Term term) throws TermNotFoundException {
        if (termRepository.findById(term.getId()) != null) {
            termRepository.delete(term);
        } else {
            throw new TermNotFoundException("Term not found");
        }
    }

}
