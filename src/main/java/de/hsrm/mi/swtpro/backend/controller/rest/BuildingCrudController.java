package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.BuildingNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.service.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class BuildingCrudController {

    @Autowired
    BuildingRepository buildingRepository;

    /**
     * Insert a Building object into the Model
     *
     * @param building recieves a Building class via POST request
     * @return Building object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/building/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Building createBuilding(@RequestBody Building building) throws URISyntaxException {
        buildingRepository.save(building);
        return building;
    }

    /**
     * Update a Building object into the Model
     * @param building recieves a Building class via POST request
     * @return Building object
     * @throws BuildingNotFoundException
     */
    @PostMapping(path = "/building/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Building updateBuilding(@RequestBody Building building) throws BuildingNotFoundException {
        return buildingRepository.save(building);

    }

    /**
     * Find a Building object from the Model
     *
     * @param buildingID recieves key from building
     * @return Building object
     * @throws BuildingNotFoundException
     */
    @GetMapping(path = "/building/read/{buildingID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Building findBuilding(@PathVariable long buildingID) throws BuildingNotFoundException {
        if (buildingRepository.findById(buildingID).isPresent()) {
            return buildingRepository.findById(buildingID).get();
        } else {
            throw new BuildingNotFoundException("Building not found");
        }
    }

    /**
     * Remove a Building object from the Model
     *
     * @param building recieves a Building id via DELETE request
     * @return void
     * @throws BuildingNotFoundException
     */
    @DeleteMapping(path = "/building/delete/{buildingID}", consumes = "application/json")
    public void deleteBuilding(@PathVariable long building) throws BuildingNotFoundException {
        if (buildingRepository.findById(building).isPresent()) {
            buildingRepository.deleteById(building);
        } else {
            throw new BuildingNotFoundException("Building not found");
        }
    }

}
