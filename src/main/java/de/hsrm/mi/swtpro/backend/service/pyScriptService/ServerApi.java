package de.hsrm.mi.swtpro.backend.service.pyScriptService;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.filter.Comparator;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.SwapOfferFilterFactory;
import de.hsrm.mi.swtpro.backend.service.messagebroker.MessageSender;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ServerApi intended to be used in loadable python scripts
 */
@Component
public class ServerApi {

    @Autowired
    SwapOfferRepository swapOfferRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    MessageSender messageSender;

    /**
     * Gets every persisted swap offer
     *
     * @return list of swap offers
     */
    public List<SwapOffer> getAllSwapOffers() {
        return this.swapOfferRepository.findAll();
    }

    /**
     * Gets every swap offer with owner id equalling ownId parameter
     *
     * @param ownId matching id
     * @return list of filtered swap offers
     */
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
    }

    /**
     * Gets every swap offer based on fromGroupId matching
     *
     * @param fromGroupId matching id
     * @return list of filtered swap offers
     */
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

    /**
     * Gets every swap offer based on toGroupId matching
     *
     * @param toGroupId matching id
     * @return list of filtered swap offers
     */
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

    /**
     * Swaps 3 swap offers
     * Updates list of students in the equivalent groups
     *
     * @param offerIds ids of matching swap offers
     * @return true if swap was successful
     */
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
            messageSender.sendPersonalSwapOfferMessage(offer, student.getUser().getId());

        });
        offers.forEach(swapOffer -> {
            swapOfferRepository.delete(swapOffer);
            messageSender.sendSwapOfferMessage(swapOffer, "delete");
        });
        offers.forEach(swapOffer -> {
            swapOfferRepository.findByStudentAndFromGroup(swapOffer.getStudent(), swapOffer.getFromGroup()).forEach(
                    swapOffer1 -> {
                        swapOfferRepository.delete(swapOffer1);
                        messageSender.sendSwapOfferMessage(swapOffer1, "delete");
                    }
            );
        });
        return true;
    }

}
