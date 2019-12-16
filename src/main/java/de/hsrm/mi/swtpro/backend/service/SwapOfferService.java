package de.hsrm.mi.swtpro.backend.service;


import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import org.springframework.beans.factory.annotation.Autowired;

/* TODO: Methode isMatched imnplementieren, bekommmt als Paramteter den SwappOfferRequest


 */
@Autowired
SwapOfferRepository swapOfferRepository;

public class SwapOfferService {

    public boolean isMatched(int matrikelnummer, long startGruppe, long zielGruppe) {
        List<SwapOffer> swapofferList = swapOfferRepository.findByFromGroup(zielGruppe);
        Filter filter; //TODO: filter erstellen -> mit yen und olli reden
        filterFactory.filterForFromGroup(swapofferList, filter); //TODO: Bereits nach zielgruppe gefilterte List swapofferlist nach -> Liste an SwapOffers von Studenten die in der Zielgruppe sind Filtern ob diese in die Startgruppe möchten
        for(SwapOffer einzelnesSwapOffer: swapofferList){
            if(einzelnesSwapOffer.getToGroup().getId() == startGruppe){ // OB ID der Zielgruppe mit der Startgruppe matcht

                //TODO: WENN Match -> beiden Studenten ihre Gruppe in dem Modul auf die neue Gruppe setzen und den Studenten in der Datenbank updaten. (Siehe StudentCrudController) und  return true
            }

        }
        return false; // Falls kein Match in der Liste an gefilterten Swapoffern gefunden werde dann soll false returnt werden.


    }

}
