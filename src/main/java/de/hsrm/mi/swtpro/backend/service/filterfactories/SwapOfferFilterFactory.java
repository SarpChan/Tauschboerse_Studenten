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
            if(filter.getAttribute().equals("toGroupId")) {
                List<SwapOffer> filterTmp = new ArrayList<>(filterableSwapOffer);
                filterableSwapOffer.clear();
                filterableSwapOffer.addAll(filterForToGroup(filterTmp,filter),filterForFromGroup(filterTmp,filter));
                filterableSwapOffer.addAll()
            }
        });
        return filterableSwapOffers;
    }
    private List<Course> filterForToGroup(List<Course> swapOffers, Filter forToGroupFilter) {
        return swapOffers.stream().filter(swapOffer -> swapOffer.getToGroup().stream().anyMatch(group -> {
            
                if (forToGroupFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return swapOffer.getToGroup() == (long) forToGroupFilter.getComparator().getComparatorValue();
                }
                return false;
            );
        })).collect(Collectors.toList());
    }
    
    private List<Course> filterForFromGroup(List<Course> swapOffers, Filter forFromGroupFilter) {
        return swapOffers.stream().filter(swapOffer -> swapOffer.getFromGroup().stream().anyMatch(group -> {
            
                if (forFromGroupFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return swapOffer.getFromGroup() == (long) forFromGroupFilter.getComparator().getComparatorValue();
                }
                return false;
            );
        })).collect(Collectors.toList());
    }

}
