package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.CampusNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Campus;
import de.hsrm.mi.swtpro.backend.service.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class CampusCrudController {

    @Autowired
    CampusRepository campusRepository;

    @PostMapping(path = "/campus/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus createCampus(@RequestBody Campus campus) throws URISyntaxException {
        campusRepository.save(campus);
        return campus;
    }

    @PostMapping(path = "/campus/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus updateCampus(@RequestBody Campus campus) throws CampusNotFoundException {
        return campusRepository.save(campus);

    }

    @GetMapping(path = "/campus/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus findCampus(@RequestParam("campusAddress") String campusAddress) throws CampusNotFoundException {
        if (campusRepository.findByAdress(campusAddress) != null) {
            return campusRepository.findByAdress(campusAddress);
        } else {
            throw new CampusNotFoundException("Campus not found");
        }
    }

    @DeleteMapping(path = "/campus/delete", consumes = "application/json")
    public void deleteCampus(@RequestBody Campus campus) throws CampusNotFoundException {
        if (campusRepository.findByAdress(campus.getAddress()) != null) {
            campusRepository.delete(campus);
        } else {
            throw new CampusNotFoundException("Campus not found");
        }
    }

}
