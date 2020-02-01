
package de.hsrm.mi.swtpro.backend.controller.rest.logic;

import de.hsrm.mi.swtpro.backend.controller.exceptions.GroupNotFoundException;
import de.hsrm.mi.swtpro.backend.controller.exceptions.StudentNotFoundException;
import de.hsrm.mi.swtpro.backend.controller.exceptions.SwapOfferAlreadyExistsException;
import de.hsrm.mi.swtpro.backend.controller.exceptions.SwapOfferNotFoundException;
import de.hsrm.mi.swtpro.backend.controller.login.security.TokenService;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.GroupCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.StudentCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.UserCrudController;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Script;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.requestModel.SwapOfferRequest;
import de.hsrm.mi.swtpro.backend.service.SwapOfferService;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGetter;
import de.hsrm.mi.swtpro.backend.service.messagebroker.MessageSender;
import de.hsrm.mi.swtpro.backend.service.pyScriptService.PythonEvaluator;
import de.hsrm.mi.swtpro.backend.service.pyScriptService.ScriptManager;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * REST endpoint for recieving swapoffers and inserting them with several conditional checks.
 * Logic is partially separated in SwapOfferService.
 */
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
    TokenService tokenService;
    @Autowired
    ServiceGetter serviceGetter;
    @Autowired
    SwapOfferService swapOfferService;
    @Autowired
    MessageSender messageSender;
    @Autowired
    ScriptManager scriptManager;
    @Autowired
    PythonEvaluator pythonEvaluator;

    List<SwapOffer> swapOfferList = new ArrayList<SwapOffer>();

    /**
     * Method for accepting existing swapoffers filtered by id.
     * Depends on SwapOfferService swap method.
     *
     * @param request
     * @param id      from SwapOffer's autogernerated DB id
     * @return boolean
     */
    @GetMapping(path = "/swapoffer/accept/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean acceptSwapOffer(HttpServletRequest request, @PathVariable long id) {
        SwapOffer offer = null;
        SwapOffer matchingOffer;
        String loginname = tokenService.getUsernameFromRequest(request);

        if (swapOfferRepository.findById(id).isPresent())
            offer = swapOfferRepository.findById(id).get();

        if (offer != null) {
            matchingOffer = SwapOffer.builder().timestamp(Timestamp.from(Instant.now()))
                    .fromGroup(offer.getToGroup())
                    .student(serviceGetter.getStudentFromUsername(loginname))
                    .toGroup(offer.getFromGroup())
                    .build();
            swapOfferService.swap(offer, matchingOffer);
            return true;
        }
        return false;
    }

    /**
     * Wiping all remaining swapoffers selected by loginname and fromGroupID. Keep's the database clean and prevents
     * swaps with invalidated offers.
     *
     * @param offer
     */
    @Transactional
    void wipeInvalidatedSwapOffers(SwapOffer offer) {
        logger.warn("WIPE OLD OFFERS WITH FROMGROUP ID: " + offer.getFromGroup().getId() + " STUDENT: " + offer.getStudent().getMail());
        swapOfferRepository.findByStudent(offer.getStudent()).stream()
                .filter(e -> e.getFromGroup() == offer.getFromGroup())
                .forEach(e -> {
                    swapOfferRepository.delete(e);
                    messageSender.sendSwapOfferMessage(e, "delete");
                });
    }

    /**
     * Iterates over list of groups to change, checks if recent iterations already created a match if not then create a SwapOffer for each Element
     * e.g. A->B , A->C , A->D ,....
     * Handles cases like :
     * - already existing swapoffers
     * - multiple target groups
     * - matching correlating groups
     * - invalidating offers identical to start group
     * - wiping remaining orphants
     *
     * @param swapOfferRequest
     * @return boolean
     */

    @PostMapping(path = "/swapoffer/insert", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public SwapOffer checkAndInsertSwapOffer(HttpServletRequest request, @RequestBody SwapOfferRequest swapOfferRequest) {
        String username = tokenService.getUsernameFromRequest(request);
        Student student = serviceGetter.getStudentFromUsername(username);
        for (long swapOfferToGroupID : swapOfferRequest.getToGroupsID()) {
            if (swapOfferToGroupID != swapOfferRequest.getFromGroupsID()) {
                AtomicBoolean swapOfferExists = new AtomicBoolean(false);
                SwapOffer offer = SwapOffer.builder().timestamp(Timestamp.from(Instant.now()))
                        .fromGroup(groupRepository.findById(swapOfferRequest.getFromGroupsID()).get())
                        .student(student)
                        .toGroup(groupRepository.findById(swapOfferToGroupID).get())
                        .build();

                swapOfferRepository.findByStudent(student).forEach(e -> {
                    if (e.getFromGroup() == offer.getFromGroup() && e.getToGroup() == offer.getToGroup()) {
                        swapOfferExists.set(true);
                    }
                });
                if (swapOfferService.isMatched(offer)) {
                    logger.warn("MATCH FOUND");
                    wipeInvalidatedSwapOffers(offer);
                    return null;
                } else {
                    if (swapOfferExists.get()) {
                        logger.warn("Offer already Exists");
                        throw new SwapOfferAlreadyExistsException("Swap Offer already exists");
                    } else {
                        logger.warn("Insert new Swapoffer ID: " + offer.getId());
                        swapOfferList.add(offer);
                        swapOfferRepository.save(offer);
                        
                        messageSender.sendSwapOfferMessage(offer, "add");

                        List<Script> matchingScripts = scriptManager.loadAllMatchingScriptsFor("def onNewSwapOffer():");
                        matchingScripts.forEach(s -> pythonEvaluator.runScriptForSwapOffer(s));

                        return swapOfferRepository.getOne(offer.getId());
                    }
                }
            } else {
                logger.warn("You cant swap inside one group :(");
            }
        }
        return null;
    }

    Student getStudent(long id) throws StudentNotFoundException {
        if (studentRepository.findById(id).isPresent()) {
            return studentRepository.findById(id).get();
        } else {
            throw new StudentNotFoundException("Student not found");
        }
    }

    Group getGroup(long groupID) throws GroupNotFoundException {
        if (groupRepository.findById(groupID).isPresent()) {
            return groupRepository.findById(groupID).get();
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

    /**
     * Finding swapoffer if only a student and target group is provided
     *
     * @param student
     * @param toGroup
     * @return the found offer or throw a not found exception
     * @throws SwapOfferNotFoundException
     */
    SwapOffer getSwapOffer(Student student, Group toGroup) throws SwapOfferNotFoundException {
        Optional<SwapOffer> offer = swapOfferRepository.findByStudent(student)
                .stream()
                .filter(e -> e.getToGroup().equals(toGroup)).findFirst();
        if (offer.isPresent()) {
            return offer.get();
        } else {
            throw new SwapOfferNotFoundException("Swapoffer not found");
        }
    }

    /**
     * Checks if a offer already exists in repository
     * @param offer to be checked
     * @return true if swap offer already exists
     */
    boolean swapOfferExists(SwapOffer offer) {
        return swapOfferRepository.findByStudent(offer.getStudent())
                .stream()
                .filter(e -> e.getFromGroup().equals(offer.getFromGroup()))
                .anyMatch(e -> e.getToGroup().equals(offer.getToGroup()));
    }

    @GetMapping(path = "/swapoffer/removematches")
    public void debug() {
        for (SwapOffer offer : swapOfferRepository.findAll()) {
            if (swapOfferService.isMatched(offer)) {
                logger.warn("MATCH REMOVED");
            }
        }
    }

}
