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
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

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
                logger.warn("POSSIBLE MATCG SILKVE");
                //TODO: In den Gruppen die Studentenliste aktualisieren -> beide studis austauschen bei fragen @vespa001
                Student A = studentRepository.findByEnrollmentNumber(offer.getStudent().getEnrollmentNumber()).get();
                Student B = einzelnesSwapOffer.getStudent();

                Set<Group> aGroups = A.getGroups();
                aGroups.remove(groupRepository.findById(offer.getFromGroup().getId()).get());
                aGroups.add(groupRepository.findById(offer.getToGroup().getId()).get());
                A.setGroups(aGroups);

                Set<Group> bGroups = A.getGroups();
                bGroups.remove(groupRepository.findById(offer.getToGroup().getId()).get());
                bGroups.add(groupRepository.findById(offer.getFromGroup().getId()).get());
                B.setGroups(bGroups);

                studentRepository.save(A);
                studentRepository.save(B);
                logger.warn("About to remove swapoffer", einzelnesSwapOffer.getToGroup().toString());

                swapOfferRepository.delete(einzelnesSwapOffer);
                return true;
            }
        }
        return false;

    }

    public void swap(SwapOffer request , SwapOffer found){
        Student A = request.getStudent();
        Student B = found.getStudent();
        logger.warn("Student A "+ A.getMail() +" Student B "+ B.getMail());

        messageSender.sendPersonalSwapOfferMessage(request, A.getUser().getId());
        messageSender.sendPersonalSwapOfferMessage(found, B.getUser().getId());

        Set<Group> aGroups = A.getGroups();
        aGroups.remove(groupRepository.getOne(request.getFromGroup().getId()));
        aGroups.add(groupRepository.getOne(request.getToGroup().getId()));
        A.setGroups(aGroups);

        Set<Group> bGroups = A.getGroups();
        bGroups.remove(groupRepository.findById(found.getFromGroup().getId()).get());
        bGroups.add(groupRepository.findById(found.getToGroup().getId()).get());
        B.setGroups(bGroups);

      //  studentRepository.save(A);
    //    studentRepository.save(B);
        logger.warn("About to remove swapoffer"+ found.getId());

  //     swapOfferRepository.delete(found);
    //   swapOfferRepository.delete(request);


    }


    public boolean debugMe(SwapOffer requestOffer) {
        List<SwapOffer> swapofferList = swapOfferRepository.findAll();//findByFromGroup(groupRepository.findById(requestOffer.getToGroup().getId()).get());
        Collections.sort(swapofferList, new Comparator<SwapOffer>() {
            @Override
            public int compare(SwapOffer a, SwapOffer b) {
                return a.getTimestamp().compareTo(b.getTimestamp());
            }
        });
        logger.warn("TOGROUP: " + requestOffer.getToGroup().getId());
        SwapOffer foundOffer = null;
        foundOffer = swapofferList
                .stream()
                .filter(e -> e.getFromGroup().equals(requestOffer.getToGroup()))
                .filter(e -> e.getToGroup().equals(requestOffer.getFromGroup()))
                .findFirst().get();
        logger.warn("FOUND SWAP: " +requestOffer.getId() +" <> "+ foundOffer.getId());



        swap(requestOffer,foundOffer);
        logger.warn("SWAPPED");

        return false;

    }
}

