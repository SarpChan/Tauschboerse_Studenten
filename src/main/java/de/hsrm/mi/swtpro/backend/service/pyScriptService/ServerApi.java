package de.hsrm.mi.swtpro.backend.service.pyScriptService;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.filter.Comparator;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.SwapOfferFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ServerApi {

    @Autowired
    SwapOfferRepository swapOfferRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;

    public List<SwapOffer> getAllSwapOffers() {
        return this.swapOfferRepository.findAll();
    }

    public List<SwapOffer> getAllSwapOffersFromMe(long ownId) {
        SwapOfferFilterFactory filterFactory = SwapOfferFilterFactory.builder()
                .filters(new Filter[]{Filter.builder()
                        .attribute("forOwnerId")
                        .comparator(Comparator.builder()
                                .comparatorType(ComparatorType.EQUALS)
                                .comparatorValue(Long.toString(ownId))
                                .build()
                        ).build()
                }).build();
        return filterFactory.filter(this.getAllSwapOffers());
    }    public List<SwapOffer> getAllSwapOffersWithFromGroupId(long fromGroupId) {
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

    @Transactional
    public boolean tripleSwap(long[] offerIds) {
        assert offerIds.length == 3 : "size for offers is incorrect";
        List<Long> ids = new ArrayList<>();
        Arrays.stream(offerIds).forEach(ids::add);
        List<SwapOffer> offers = this.swapOfferRepository.findAllById(ids);
        Map<Long, SwapOffer> offerMap = offers.stream().collect(Collectors.toMap(swapOffer -> swapOffer.getStudent().getId(), swapOffer -> swapOffer));
        List<Student> students = offers.stream().map((SwapOffer::getStudent)).collect(Collectors.toList());
        students.forEach(student -> {
            SwapOffer offer = offerMap.get(student.getId());
            Group fromGroup = offer.getFromGroup();
            Group toGroup = offer.getToGroup();
            fromGroup.removeStudent(student);
            toGroup.addStudent(student);
            groupRepository.save(fromGroup);
            groupRepository.save(toGroup);
        });
        offers.forEach(swapOffer -> swapOfferRepository.delete(swapOffer));
        return true;
    }

}
