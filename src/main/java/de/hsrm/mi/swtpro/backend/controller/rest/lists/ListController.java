package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.CourseFilterFactory;
import de.hsrm.mi.swtpro.backend.service.filterfactories.SwapOfferFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/lists")
public class ListController {

    @Autowired
    CourseRepository courseRepository;

    @PostMapping(path = "/course", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCourse(@RequestBody Filter[] filters) {
        List<Course> allCourses = courseRepository.findAll();
        CourseFilterFactory filterFactory = CourseFilterFactory.builder().filters(filters).build();
        allCourses = filterFactory.filter(allCourses);
        return allCourses;
    }

    @Autowired
    FieldOfStudyRepository fieldOfStudyRepository;

    @GetMapping(path = "/fieldOfStudy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomFieldOfStudy> getFieldOfStudy() {
        return fieldOfStudyRepository.findAll().stream().map(CustomFieldOfStudy::fromOriginal).collect(Collectors.toList());
    }

    @Autowired
    SwapOfferRepository swapOfferRepository;

    @PostMapping(path = "/swapOffer", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SwapOffer> getSwapOffer(@RequestBody Filter[] filters) {
        List<SwapOffer> allSwapOffers = swapOfferRepository.findAll();
        SwapOfferFilterFactory filterFactory = SwapOfferFilterFactory.builder().filters(filters).build();
        allSwapOffers = filterFactory.filter(allSwapOffers);
        return allSwapOffers;
    }

}
