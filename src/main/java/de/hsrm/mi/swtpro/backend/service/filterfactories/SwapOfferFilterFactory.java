package de.hsrm.mi.swtpro.backend.service.filterfactories;

import de.hsrm.mi.swtpro.backend.model.SwapOffer;
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
            if(filter.getAttribute().equals("toGroupId")) {
                List<SwapOffer> filterTmp = new ArrayList<>(filterableSwapOffers);
                filterableSwapOffers.clear();
                filterableSwapOffers.addAll(filterForToGroup(filterTmp,filter));
                filterableSwapOffers.addAll(filterForFromGroup(filterTmp,filter));
                filterableSwapOffers.addAll(filterForOwner(filterTmp,filter));
                //filterableSwapOffers.addAll(
            }
        });
        return filterableSwapOffers;
    }
    private List<SwapOffer> filterForToGroup(List<SwapOffer> swapOffers, Filter forToGroupFilter) {
        return swapOffers.filter(swapOffer -> swapOffer.getToGroup().anyMatch(group -> {
            
                if (forToGroupFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return swapOffer.getToGroup() ==  forToGroupFilter.getComparator().getComparatorValue();
                }
                return false;
        })).collect(Collectors.toList());
    }

    private List<SwapOffer> filterForFromGroup(List<SwapOffer> swapOffers, Filter forFromGroupFilter) {
        return swapOffers.filter(swapOffer -> swapOffer.getFromGroup().anyMatch(group -> {
            
                if (forFromGroupFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return swapOffer.getFromGroup() ==  forFromGroupFilter.getComparator().getComparatorValue();
                }
                return false;
        })).collect(Collectors.toList());
    }

    private List<SwapOffer> filterForOwner(List<SwapOffer> swapOffers, Filter forOwnerFilter) {
        return swapOffers.filter(swapOffer -> swapOffer.getStudent().anyMatch(group -> {
            
                if (forOwnerFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return swapOffer.getStudent() ==  forOwnerFilter.getComparator().getComparatorValue();
                }
                return false;
        })).collect(Collectors.toList());
    }

    
/*
    private List<SwapOffer> filterForCourse(List<SwapOffer> swapOffers, Filter forCourseFilter) {
        return swapOffers.stream().filter(swapOffers -> swapOffers.getModules().stream().anyMatch(module -> {
            return module.getModulesInCurriculum().stream().anyMatch(moduleInCurriculum -> {
                if (forCourseFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return moduleInCurriculum.getCurriculum().getExamRegulation().getId() == (long) forCourseFilter.getComparator().getComparatorValue();
                }
                return false;
            });
        })).collect(Collectors.toList());
    }
    */

}
