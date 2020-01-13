
package de.hsrm.mi.swtpro.backend.controller.rest.logic;

import de.hsrm.mi.swtpro.backend.controller.rest.crud.GroupCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.StudentCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.UserCrudController;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.requestModel.SwapOfferRequest;
import de.hsrm.mi.swtpro.backend.service.SwapOfferService;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/rest")
public class SwapOfferInterface {
    Logger logger = LoggerFactory.getLogger(SwapOfferInterface.class);
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SwapOfferRepository swapOfferRepository;
    @Autowired
    UserCrudController userCrudController;
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
    List<SwapOffer> swapOfferList = new ArrayList<SwapOffer>();

    @PostMapping(path = "/swapoffer/insert", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean checkAndInsertSwapOffer(@RequestBody SwapOfferRequest swapOfferRequest) {
        AtomicBoolean swapOfferExists = new AtomicBoolean(false);
        for (long swapOfferToGroupID : swapOfferRequest.getToGroupsID()) {
            SwapOffer offer = SwapOffer.builder().date(Timestamp.from(Instant.now()))
                    .fromGroup(groupRepository.findById(swapOfferRequest.getFromGroupsID()).get())
                    .student(studentRepository.findByEnrollmentNumber(swapOfferRequest.getUserID()).get())
                    .toGroup(groupRepository.findById(swapOfferToGroupID).get())
                    .build();

            swapOfferRepository.findByStudent(studentRepository.findByEnrollmentNumber(swapOfferRequest.getUserID()).get()).forEach(e -> {
                if (e.getFromGroup() == offer.getFromGroup() && e.getToGroup() == offer.getToGroup()) {
                    swapOfferExists.set(true);
                }

            });

            if (swapOfferService.isMatched(offer)) {
                logger.warn("MATCH FOUND");

            } else {

                if (swapOfferExists.get()) {
                    logger.warn("Offer already Exists");
                } else {
                    swapOfferList.add(offer);
                    swapOfferRepository.save(offer);
                }

            }
        }


        return false;
    }


    @PostMapping(path = "/swapoffer/debug", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object debug(@RequestBody SwapOfferRequest swapOfferRequest) {
        return swapOfferService.debugMe(swapOfferRequest);

/*
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

    }


    @GetMapping(path = "/swapoffer/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SwapOffer> selectAllSwapOffers(){
        //:TODO Hier sollen alle Tauschangebote an das Frontend gesendent werden
    }


}
