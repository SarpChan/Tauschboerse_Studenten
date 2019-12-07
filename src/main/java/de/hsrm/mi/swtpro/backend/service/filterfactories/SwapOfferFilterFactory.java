package de.hsrm.mi.swtpro.backend.service.filterfactories;

import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SwapOfferFilterFactory {

    @Getter @Setter
    private Filter[] filters;

    private boolean isFiltersEmpty() {
        return filters.length == 0;
    }

    public List<SwapOffer> filterSwapOffers(List<SwapOffer> swapOffers) {
        final List<SwapOffer> filterableSwapOffers = new ArrayList<>(swapOffers);
        if(isFiltersEmpty()) {
            return swapOffers;
        }
        Arrays.stream(this.filters).forEach(filter -> {
            if(filter.getAttribute().equals("examRegulationId")) {
                List<SwapOffer> filterTmp = new ArrayList<>(filterableSwapOffer);
                filterableSwapOffer.clear();
                filterableSwapOffer.addAll(filterForExamRegulation(filterTmp,filter));
            }
        });
        return swapOffers;
    }
   

}
