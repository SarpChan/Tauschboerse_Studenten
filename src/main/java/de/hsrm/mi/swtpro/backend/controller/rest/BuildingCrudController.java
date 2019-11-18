package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.BuildingNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.service.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class BuildingCrudController {

    @Autowired
    BuildingRepository buildingRepository;

    @PostMapping(path = "/building/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Building createBuilding(@RequestBody Building building) throws URISyntaxException {
        buildingRepository.save(building);
        return building;
    }

    @PostMapping(path = "/building/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Building updateBuilding(@RequestBody Building building) throws BuildingNotFoundException {
        return buildingRepository.save(building);

    }

    @GetMapping(path = "/building/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Building findBuilding(@RequestParam("buildingID") long buildingID) throws BuildingNotFoundException {
        if (buildingRepository.findById(buildingID) != null) {
            return buildingRepository.findById(buildingID);
        } else {
            throw new BuildingNotFoundException("Building not found");
        }
    }

    @DeleteMapping(path = "/building/delete", consumes = "application/json")
    public void deleteBuilding(@RequestBody Building building) throws BuildingNotFoundException {
        if (buildingRepository.findById(building.getId()) != null) {
            buildingRepository.delete(building);
        } else {
            throw new BuildingNotFoundException("Building not found");
        }
    }

}
