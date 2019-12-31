package de.hsrm.mi.swtpro.backend.service;


import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* TODO: Methode isMatched imnplementieren, bekommmt als Paramteter den SwappOfferRequest


 */
@Service
public class SwapOfferService {
    @Autowired
    SwapOfferRepository swapOfferRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;

    public boolean isMatched(int matrikelnummer, long startGruppe, long zielGruppe) {
        List<SwapOffer> swapofferList = swapOfferRepository.findByFromGroup(groupRepository.findById(zielGruppe).get());
        Collections.sort(swapofferList, new Comparator<SwapOffer>() {
            @Override
            public int compare(SwapOffer a, SwapOffer b) {
                return a.getDate().compareTo(b.getDate());
            }
        });
      /*  Filter filter = Filter.builder().attribute("A").comparator(new Comparator<S>().setComparatorValue(1)).build(); //TODO: filter erstellen -> mit yen und olli reden
        filterFactory.filterForFromGroup(swapofferList, filter); //TODO: Bereits nach zielgruppe gefilterte List swapofferlist nach -> Liste an SwapOffers von Studenten die in der Zielgruppe sind Filtern ob diese in die Startgruppe möchten
        */
        for(SwapOffer einzelnesSwapOffer: swapofferList){

            if(einzelnesSwapOffer.getToGroup().getId() == startGruppe){ // OB ID der Zielgruppe mit der Startgruppe matcht
                Student A = studentRepository.findByEnrollmentNumber(matrikelnummer).get();
                Student B = einzelnesSwapOffer.getStudent();
                A.getGroups().forEach(group -> {
                    if (group.getId() == startGruppe) group = groupRepository.findById(zielGruppe).get();
                }); //TODO: Setzt des anfragenden Studis auf seine Zielgruppe
                B.getGroups().forEach(group -> {
                    if (group.getId() == startGruppe) group = groupRepository.findById(startGruppe).get();
                });
                //TODO: WENN Match -> beiden Studenten ihre Gruppe in dem Modul auf die neue Gruppe setzen und den Studenten in der Datenbank updaten. (Siehe StudentCrudController) und  return true
                studentRepository.save(A);
                studentRepository.save(B);

            }

        }
        return false; // Falls kein Match in der Liste an gefilterten Swapoffern gefunden werde dann soll false returnt werden.


    }

}

