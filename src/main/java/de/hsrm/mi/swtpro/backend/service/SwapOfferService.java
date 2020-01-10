package de.hsrm.mi.swtpro.backend.service;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.requestModel.SwapOfferRequest;
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

    public boolean isMatched(SwapOffer offer) {
        List<SwapOffer> swapofferList = swapOfferRepository.findByFromGroup(groupRepository.findById(offer.getToGroup().getId()).get());
        Collections.sort(swapofferList, new Comparator<SwapOffer>() {
            @Override
            public int compare(SwapOffer a, SwapOffer b) {
                return a.getDate().compareTo(b.getDate());
            }
        });
        for(SwapOffer einzelnesSwapOffer: swapofferList){
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

    public Object debugMe(SwapOfferRequest swapOfferRequest) {
        List<SwapOffer> swapOfferList = new ArrayList<SwapOffer>();
        logger.warn("HEY: " + studentRepository.findByEnrollmentNumber(swapOfferRequest.getUserID()).get());
        for (long gID : swapOfferRequest.getToGroupsID()) {
            SwapOffer offer = SwapOffer.builder().date(Timestamp.from(Instant.now()))
                    .fromGroup(groupRepository.findById(swapOfferRequest.getFromGroupsID()).get())
                    .student(studentRepository.findByEnrollmentNumber(swapOfferRequest.getUserID()).get())
                    .toGroup(groupRepository.findById(gID).get())
                    .build();
            swapOfferList.add(offer);
            swapOfferRepository.save(offer);
        }
        return swapOfferList;
    }

}

