package de.hsrm.mi.swtpro.backend.controller.rest;


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
     * @param campusAddress recieves key from campus
     * @return Campus object
     * @throws CampusNotFoundException
     */
    @GetMapping(path = "/campus/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus findCampus(@RequestParam("campusAddress") String campusAddress) throws CampusNotFoundException {
        if (campusRepository.findByAddress(campusAddress) != null) {
            return campusRepository.findByAddress(campusAddress);
        } else {
            throw new CampusNotFoundException("Campus not found");
        }
    }

    /**
     * Remove a Campus object from the Model
     * @param campus recieves a Campus class via POST request
     * @return Campus object or
     * @throws CampusNotFoundException
     */
    @DeleteMapping(path = "/campus/delete", consumes = "application/json")
    public void deleteCampus(@RequestBody Campus campus) throws CampusNotFoundException {
        if (campusRepository.findById(campus.getId()) != null) {
            campusRepository.delete(campus);
        } else {
            throw new CampusNotFoundException("Campus not found");
        }
    }

}
