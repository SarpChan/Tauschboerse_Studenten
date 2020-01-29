package de.hsrm.mi.swtpro.backend.service;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.filter.Comparator;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.SwapOfferFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ServerApi {

    @Autowired
    SwapOfferRepository swapOfferRepository;
    @Autowired
    StudentRepository studentRepository;

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
        assert offerIds.length == 3 : "size for offers is incorrect";
        List<Long> ids = new ArrayList<>();
        Arrays.stream(offerIds).forEach(ids::add);
        List<SwapOffer> offers = this.swapOfferRepository.findAllById(ids);
        Map<Long, SwapOffer> offerMap = offers.stream().collect(Collectors.toMap(swapOffer -> swapOffer.getStudent().getId(), swapOffer -> swapOffer));
        List<Student> students = offers.stream().map((SwapOffer::getStudent)).collect(Collectors.toList());
        students.forEach(student -> {
            SwapOffer offer = offerMap.get(student.getId());
            Set<Group> next = student.getGroups().stream().filter(group -> group.getId() == offer.getFromGroup().getId()).collect(Collectors.toSet());
            next.add(offer.getToGroup());
            student.setGroups(next);
            studentRepository.save(student);
        });
        offers.forEach(swapOffer -> swapOfferRepository.delete(swapOffer));
        return true;
    }

}
