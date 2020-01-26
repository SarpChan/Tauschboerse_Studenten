package de.hsrm.mi.swtpro.backend.service;

import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.filter.Comparator;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.SwapOfferFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServerApi {

    @Autowired
    SwapOfferRepository swapOfferRepository;

    private SwapOffer latestSwapOffer;

    public void setLatestSwapOffer(SwapOffer offer) {
        latestSwapOffer = offer;
    }

    public SwapOffer getLatestSwapOffer() {
        return latestSwapOffer;
    }

    public List<SwapOffer> getAllSwapOffers() {
        return this.swapOfferRepository.findAll();
    }

    public List<SwapOffer> getAllSwapOffersWithFromGroupId(long fromGroupId) {
        SwapOfferFilterFactory filterFactory = SwapOfferFilterFactory.builder()
                .filters(new Filter[]{Filter.builder()
                        .attribute("fromGroupId")
                        .comparator(Comparator.builder()
                                .comparatorType(ComparatorType.EQUALS)
                                .comparatorValue(Long.toString(fromGroupId))
                                .build()
                        ).build()
                }).build();
        return filterFactory.filter(this.getAllSwapOffers());
    }

    public List<SwapOffer> getAllSwapOffersWithToGroupId(long toGroupId) {
        SwapOfferFilterFactory filterFactory = SwapOfferFilterFactory.builder()
                .filters(new Filter[]{Filter.builder()
                        .attribute("toGroupId")
                        .comparator(Comparator.builder()
                                .comparatorType(ComparatorType.EQUALS)
                                .comparatorValue(Long.toString(toGroupId))
                                .build()
                        ).build()
                }).build();
        return filterFactory.filter(this.getAllSwapOffers());
    }

    public boolean tripleSwap(long[] offerIds) {
        //manually swap offers
        // from D to A

        // from A to B
        // from B to D
        return true;
    }

}
