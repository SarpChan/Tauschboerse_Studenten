package de.hsrm.mi.swtpro.backend.service;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.requestModel.SwapOfferRequest;
import de.hsrm.mi.swtpro.backend.service.messagebroker.MessageSender;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Service for executing major database operations, while maintaining ACID principles.
 * Partially seperated from the associated REST endpoint.
 */
@Service
public class SwapOfferService {
    Logger logger = LoggerFactory.getLogger(SwapOfferService.class);
    @Autowired
    SwapOfferRepository swapOfferRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageSender messageSender;

    /**
     * Method for checking and executing swapoffer if available.
     * Filtered by offers starting group and sorted by timestamp. Older offers should be preferred.
     *
     * @param offer
     * @return boolean because the internal process of matching offers should be hidden from the outside
     */
    public boolean isMatched(SwapOffer offer) {
        List<SwapOffer> swapofferList = swapOfferRepository.findByFromGroup(groupRepository.findById(offer.getToGroup().getId()).get());
        Collections.sort(swapofferList, new Comparator<SwapOffer>() {
            @Override
            public int compare(SwapOffer a, SwapOffer b) {
                return a.getTimestamp().compareTo(b.getTimestamp());
            }
        });
        for (SwapOffer einzelnesSwapOffer : swapofferList) {
            if (einzelnesSwapOffer.getToGroup().getId() == offer.getFromGroup().getId()) { // OB ID der Zielgruppe mit der Startgruppe matcht
                swap(einzelnesSwapOffer, offer);
                return true;
            }
        }
        return false;
    }

    /**
     * Core logic of swapping offers between students. Seperated from the REST endpoint and with @Transactional it will maintain ACID.
     * Retrieving both students and their groups, remove both students from their groups and insert them vice versa.
     * After this procedure both groups will be saved back to the repository. And their corresponding swapoffers removed from the database.
     *
     * @param request SwapOffer from the requesting student.
     * @param found   SwapOffer found inside the database.
     */
    @Transactional
    public void swap(SwapOffer request, SwapOffer found) {
        Student A = request.getStudent();
        Student B = found.getStudent();
        logger.warn("Student A " + A.getMail() + " Student B " + B.getMail());

        messageSender.sendPersonalSwapOfferMessage(request, A.getUser().getId());
        messageSender.sendPersonalSwapOfferMessage(found, B.getUser().getId());

        Group fromGroup = request.getFromGroup();
        Group toGroup = request.getToGroup();

        fromGroup.removeStudent(A);
        toGroup.addStudent(A);

        toGroup.removeStudent(B);
        fromGroup.addStudent(B);

        groupRepository.save(fromGroup);
        groupRepository.save(toGroup);

        logger.warn("REMOVE: " + found.getId());

        if (swapOfferRepository.findById(found.getId()).isPresent()) {
            messageSender.sendSwapOfferMessage(found, "delete");
            swapOfferRepository.delete(found);
        }   
        if (swapOfferRepository.findById(request.getId()).isPresent()) {
            messageSender.sendSwapOfferMessage(request, "delete");
            swapOfferRepository.delete(request);
        }
    }
}

