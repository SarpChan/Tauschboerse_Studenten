package de.hsrm.mi.swtpro.backend.service.filterfactories;

import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            List<SwapOffer> filterTmp = new ArrayList<>(filterableSwapOffers);
            if(filter.getAttribute().equals("toGroupId")) {
                filterableSwapOffers.clear();
                filterableSwapOffers.addAll(filterForToGroup(filterTmp,filter));
            }
            if(filter.getAttribute().equals("fromGroupId")){
                filterableSwapOffers.clear();
                filterableSwapOffers.addAll(filterForFromGroup(filterTmp,filter));
            }
            if(filter.getAttribute().equals("forOwnerId")){
                filterableSwapOffers.clear();
                filterableSwapOffers.addAll(filterForOwner(filterTmp,filter));
            }
            if(filter.getAttribute().equals("forCourseId")){
                filterableSwapOffers.clear();
                filterableSwapOffers.addAll(filterForCourse(filterTmp,filter));
            }
        });
        return filterableSwapOffers;
    }
    private List<SwapOffer> filterForToGroup(List<SwapOffer> swapOffers, Filter forToGroupFilter) {
        return swapOffers.stream().filter(swapOffer -> {
            if (forToGroupFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                return swapOffer.getToGroup().getId() ==  (long) forToGroupFilter.getComparator().getComparatorValue();
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<SwapOffer> filterForFromGroup(List<SwapOffer> swapOffers, Filter forFromGroupFilter) {
        return swapOffers.stream().filter(swapOffer -> {
            
                if (forFromGroupFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return swapOffer.getFromGroup() ==  forFromGroupFilter.getComparator().getComparatorValue();
                }
                return false;
        }).collect(Collectors.toList());
    }

    private List<SwapOffer> filterForOwner(List<SwapOffer> swapOffers, Filter forOwnerFilter) {
        return swapOffers.stream().filter(swapOffer -> {
            
                if (forOwnerFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return swapOffer.getStudent() ==  forOwnerFilter.getComparator().getComparatorValue();
                }
                return false;
        }).collect(Collectors.toList());
    }

    private List<SwapOffer> filterForCourse(List<SwapOffer> swapOffers, Filter forCourseFilter) {
        return swapOffers.stream().filter(swapOffer -> {
            if (forCourseFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                return swapOffer.getFromGroup().getCourseComponent().getCourse().getId() == (long) forCourseFilter.getComparator().getComparatorValue();
            }
            return false;
        }).collect(Collectors.toList());
    }
    

}
