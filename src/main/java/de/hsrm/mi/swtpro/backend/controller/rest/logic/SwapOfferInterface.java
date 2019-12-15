package de.hsrm.mi.swtpro.backend.controller.rest.logic;

import de.hsrm.mi.swtpro.backend.controller.rest.crud.GroupCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.StudentCrudController;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.requestModel.SwapOfferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@RequestMapping("/rest")
public class SwapOfferInterface {
    @Autowired
    SwapOfferRepository swapOfferRepository;
    @Autowired
    StudentCrudController studentCrudController;
    @Autowired
    GroupCrudController groupCrudController;

    /**
     * Iterates over list of groups to change, checks if recent iterations already created a match if not then create a SwapOffer for each Element
     * e.g. A->B , A->C , A->D ,....
     *
     * @param swapOfferRequest
     * @return boolean
     */
    @PostMapping(path = "/swapoffer/insert", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean checkAndInsertSwapOffer(@RequestBody SwapOfferRequest swapOfferRequest) {
        for (long swapOfferToGroupID : swapOfferRequest.getToGroupsID()) {
            if (swapOfferService.isMatched(swapOfferToGroupID)) {
                return true;
            } else {
                Timestamp date = Timestamp.from(Instant.now());
                Student student = studentCrudController.findStudent(swapOfferRequest.getOwnerEnrollmentNumber());
                swapOfferRepository.save(new SwapOffer().builder()
                        .date(Timestamp.from(Instant.now()))
                        .student(studentCrudController.findStudent(swapOfferRequest.getOwnerEnrollmentNumber()))
                        .fromGroup(groupCrudController.findGroup(swapOfferRequest.getFromGroupsID()))
                        .toGroup(groupCrudController.findGroup(swapOfferToGroupID))
                        .build());
            }
        }
        return true;
    }
}
