package de.hsrm.mi.swtpro.backend.controller.rest.logic;

import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class SwapOfferInterface {
    @Autowired
    GroupRepository groupRepository;


    @PostMapping(path = "/swapoffer/insert", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean checkAndInsertSwapOffer(@RequestBody List<SwapOffer> swapOfferList) throws URISyntaxException {

        return true;
    }
}
