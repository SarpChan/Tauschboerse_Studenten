package de.hsrm.mi.swtpro.backend.controller.rest.crud;


import de.hsrm.mi.swtpro.backend.controller.exceptions.CampusNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Campus;
import de.hsrm.mi.swtpro.backend.service.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class CampusCrudController {

    @Autowired
    CampusRepository campusRepository;

    /**
     * Insert a Campus object into the Model
     *
     * @param campus recieves a Campus class via POST request
     * @return Campus object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/campus/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus createCampus(@RequestBody Campus campus) throws URISyntaxException {
        campusRepository.save(campus);
        return campus;
    }

    /**
     * Update a Campus object into the Model
     * @param campus recieves a Campus class via POST request
     * @return Campus object
     * @throws CampusNotFoundException
     */
    @PostMapping(path = "/campus/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus updateCampus(@RequestBody Campus campus) throws CampusNotFoundException {
        return campusRepository.save(campus);

    }

    /**
     * Find a Campus object from the Model
     *
     * @param campusID recieves key from campus
     * @return Campus object
     * @throws CampusNotFoundException
     */
    @GetMapping(path = "/campus/read/{campusID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus findCampus(@PathVariable long campusID) throws CampusNotFoundException {
        if (campusRepository.findById(campusID).isPresent()) {
            return campusRepository.findById(campusID).get();
        } else {
            throw new CampusNotFoundException("Campus not found");
        }
    }

    /**
     * Remove a Campus object from the Model
     *
     * @param campus recieves a Campus id via DELETE request
     * @return void
     * @throws CampusNotFoundException
     */
    @DeleteMapping(path = "/campus/delete/{campus}", consumes = "application/json")
    public void deleteCampus(@PathVariable long campus) throws CampusNotFoundException {
        if (campusRepository.findById(campus).isPresent()) {
            campusRepository.deleteById(campus);
        } else {
            throw new CampusNotFoundException("Campus not found");
        }
    }

}
