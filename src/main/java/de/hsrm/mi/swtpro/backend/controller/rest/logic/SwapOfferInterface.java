
package de.hsrm.mi.swtpro.backend.controller.rest.logic;

import de.hsrm.mi.swtpro.backend.controller.exceptions.GroupNotFoundException;
import de.hsrm.mi.swtpro.backend.controller.exceptions.StudentNotFoundException;
import de.hsrm.mi.swtpro.backend.controller.exceptions.SwapOfferNotFoundException;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.GroupCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.StudentCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.UserCrudController;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.model.requestModel.SwapOfferRequest;
import de.hsrm.mi.swtpro.backend.service.SwapOfferService;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/rest")
public class SwapOfferInterface {
    Logger logger = LoggerFactory.getLogger(SwapOfferInterface.class);
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;
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
            SwapOffer offer = SwapOffer.builder().timestamp(Timestamp.from(Instant.now()))
                    .fromGroup(groupRepository.findById(swapOfferRequest.getFromGroupsID()).get())
                    .student(studentRepository.findById(swapOfferRequest.getId()).get())
                    .toGroup(groupRepository.findById(swapOfferToGroupID).get())
                    .build();

            swapOfferRepository.findByStudent(studentRepository.findById(swapOfferRequest.getId()).get()).forEach(e -> {
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

    Student getStudent(long id) throws StudentNotFoundException{
        if(studentRepository.findById(id).isPresent()){
            return studentRepository.findById(id).get();
        }else{
            throw new StudentNotFoundException("Student not found");
        }
    }
    Group getGroup(long groupID) throws GroupNotFoundException {
        if(groupRepository.findById(groupID).isPresent()){
            return groupRepository.findById(groupID).get();
        }else{
            throw new GroupNotFoundException("Group not found");
        }
    }
    SwapOffer getSwapOffer(Student student, Group toGroup) throws SwapOfferNotFoundException {
        Optional<SwapOffer> offer = swapOfferRepository.findByStudent(student)
                .stream()
                .filter(e -> e.getToGroup().equals(toGroup)).findFirst();
        if(offer.isPresent()){
            return offer.get();
        }else{
            throw new SwapOfferNotFoundException("Swapoffer not found");
        }
    }

    boolean swapOfferExists(SwapOffer offer) {
        return swapOfferRepository.findByStudent(offer.getStudent())
                .stream()
                .filter(e -> e.getFromGroup().equals(offer.getFromGroup()))
                .anyMatch(e -> e.getToGroup().equals(offer.getToGroup()));
    }
    @PostMapping(path = "/swapoffer/debugme", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object debugme(@RequestBody SwapOfferRequest swapOfferRequest) {
        SwapOffer offer = SwapOffer.builder().timestamp(Timestamp.from(Instant.now()))
                .fromGroup(getGroup(swapOfferRequest.getFromGroupsID()))
                .student(getStudent(swapOfferRequest.getId()))
                .toGroup(getGroup(swapOfferRequest.getToGroupsID()[0]))
                .build();
         return swapOfferService.debugMe(offer);
    }


    @GetMapping(path = "/swapoffer/getdebug")
    public void debug() {
        for(SwapOffer offer:swapOfferRepository.findAll()) {
            if (swapOfferService.debugMe(offer)) {
                logger.warn("MATCH FOUND");
            }
        }
    }
    @PostMapping(path = "/swapoffer/debug", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean debug(@RequestBody SwapOfferRequest swapOfferRequest) {
        //   return swapOfferService.debugMe(swapOfferRequest);
        AtomicBoolean swapOfferExists = new AtomicBoolean(false);
        for (long swapOfferToGroupID : swapOfferRequest.getToGroupsID()) {
            SwapOffer offer = SwapOffer.builder().timestamp(Timestamp.from(Instant.now()))
                    .fromGroup(getGroup(swapOfferRequest.getFromGroupsID()))
                    .student(getStudent(swapOfferRequest.getId()))
                    .toGroup(getGroup(swapOfferToGroupID))
                    .build();
         //   logger.warn("STUDENT: "+ swapOfferService.debugMe(offer)) ;
        if (swapOfferService.debugMe(offer)) {
             logger.warn("MATCH FOUND");
        } /*else {
            if (swapOfferExists(offer)) {
                    logger.warn("Offer already Exists");
                } else {

                swapOfferList.add(offer);
                    swapOfferRepository.save(offer);
                logger.warn("Insert new swap offer id: "+getSwapOffer(offer.getStudent(),offer.getFromGroup()));

            }

            }*/
        }


        return false;
    }


    @GetMapping(path = "/swapoffer/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SwapOffer> selectAllSwapOffers(){
        //:TODO Hier sollen alle Tauschangebote an das Frontend gesendent werden
        return swapOfferRepository.findAll();
    }


    @GetMapping(path = "/swapoffer/{enrollmentnumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SwapOffer> selectByIDSwapOffers(@PathVariable int enrollmentnumber){
        //:TODO if Abfrage isPresent statt ".get()"
        return swapOfferRepository.findByStudent(studentRepository.findByEnrollmentNumber(enrollmentnumber).get());
    }



}
