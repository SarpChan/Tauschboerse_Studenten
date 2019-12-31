
package de.hsrm.mi.swtpro.backend.controller.rest.logic;

import de.hsrm.mi.swtpro.backend.controller.rest.crud.GroupCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.StudentCrudController;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.requestModel.SwapOfferRequest;
import de.hsrm.mi.swtpro.backend.service.SwapOfferService;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class SwapOfferInterface {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SwapOfferRepository swapOfferRepository;
    @Autowired
    StudentCrudController studentCrudController;
    @Autowired
    GroupCrudController groupCrudController;

    @Autowired
    SwapOfferService swapOfferService;
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
            if (swapOfferService.isMatched(swapOfferRequest.getOwnerEnrollmentNumber(),swapOfferRequest.getFromGroupsID(),swapOfferToGroupID)) {
                return true;
            } else {
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


    @PostMapping(path = "/swapoffer/debug", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SwapOffer> debug(@RequestBody SwapOfferRequest swapOfferRequest) {
        List<SwapOffer> swapofferList = swapOfferRepository.findByFromGroup(groupRepository.findById(swapOfferRequest.getToGroupsID()[0]).get());
        Collections.sort(swapofferList, new Comparator<SwapOffer>() {
            @Override
            public int compare(SwapOffer a, SwapOffer b) {
                return a.getDate().compareTo(b.getDate());
            }
        });
    /*    swapOfferService.isMatched(swapOfferRequest.getOwnerEnrollmentNumber(),swapOfferRequest.getFromGroupsID(),swapOfferToGroupID)
        Student A = studentRepository.findByEnrollmentNumber(swapOfferRequest.getOwnerEnrollmentNumber()).get();
        Student B =  einzelnesSwapOffer.getStudent();*/
        return swapofferList;
    }

}
