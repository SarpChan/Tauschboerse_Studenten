package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.CourseFilterFactory;
import de.hsrm.mi.swtpro.backend.service.filterfactories.SwapOfferFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.CourseRepository;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/lists")
public class ListController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping(path = "/course", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCourse(@RequestBody Filter[] filters) {
        List<Course> allCourses = courseRepository.findAll();
        CourseFilterFactory filterFactory = CourseFilterFactory.builder().filters(filters).build();
        allCourses = filterFactory.filter(allCourses);
        return allCourses;
    }


    @Autowired
    SwapOfferRepository swapOfferRepository;

    @GetMapping(path = "/swapOffer", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SwapOffer> getSwapOffer(@RequestBody Filter[] filters) {
        List<SwapOffer> allSwapOffers = swapOfferRepository.findAll();
        SwapOfferFilterFactory filterFactory = SwapOfferFilterFactory.builder().filters(filters).build();
        allSwapOffers = filterFactory.filter(allSwapOffers);
        return allSwapOffers;
    }


}