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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import de.hsrm.mi.swtpro.backend.controller.login.security.TokenService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.*;

/**
 * Controller containing endpoints for basic lists
 */
@RestController
@RequestMapping("/rest/lists")
public class ListController {

    @Autowired
    CourseRepository courseRepository;

    /**
     * POST request for Courses
     * Returns all courses filtered by the provided filter array.
     * @param filters - array of filters for course list
     * @return filtered list of courses
     */
    @PostMapping(path = "/course", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCourse(@RequestBody Filter[] filters) {
        List<Course> allCourses = courseRepository.findAll();
        CourseFilterFactory filterFactory = CourseFilterFactory.builder().filters(filters).build();
        allCourses = filterFactory.filter(allCourses);
        return allCourses;
    }

    @Autowired
    FieldOfStudyRepository fieldOfStudyRepository;

    /**
     * GET request for FieldOfStudy
     * Method maps original persisted FieldOfStudy entities to custom entities
     * requested by front end
     * @return list of FieldOfStudy
     */
    @GetMapping(path = "/fieldOfStudy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomFieldOfStudy> getFieldOfStudy() {
        return fieldOfStudyRepository.findAll().stream().map(CustomFieldOfStudy::fromOriginal).collect(Collectors.toList());
    }

    @Autowired
    SwapOfferRepository swapOfferRepository;

    /**
     * POST request for SwapOffer
     * Returns all swap offer filtered by the provided filter array.
     * @param filters - array of filters for swap offer list
     * @return filtered list of SwapOffers
     */
    @PostMapping(path = "/swapOffer", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SwapOffer> getSwapOffer(@RequestBody Filter[] filters) {
        List<SwapOffer> allSwapOffers = swapOfferRepository.findAll();
        SwapOfferFilterFactory filterFactory = SwapOfferFilterFactory.builder().filters(filters).build();
        allSwapOffers = filterFactory.filter(allSwapOffers);
        return allSwapOffers;
    }

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;


    /**
     * GET request for every SwapOffer
     * Method maps original persisted SwapOffer entities to custom entities
     * @return list of frontend specific SwapOffer models
     */
    @GetMapping(path = "/swapOffer/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SwapOfferFront> getSwapOffer() {
        List<SwapOffer> allSwapOffers = swapOfferRepository.findAll();
        List<SwapOfferFront> swapOffers = new ArrayList<SwapOfferFront>();
        for (SwapOffer swapOffer : allSwapOffers) {
            SwapOfferFront swapOfferFront = SwapOfferFront.builder()
                    .Id(swapOffer.getId())
                    .fromGroup(swapOffer.getFromGroup().getGroupChar())
                    .toGroup(swapOffer.getToGroup().getGroupChar())
                    .courseName(swapOffer.getToGroup().getCourseComponent().getCourse().getTitle())
                    .courseType(swapOffer.getToGroup().getCourseComponent().getType().toString())
                    .toStartTime(swapOffer.getToGroup().getStartTime())
                    .toEndTime(swapOffer.getToGroup().getEndTime())
                    .fromStartTime(swapOffer.getFromGroup().getStartTime())
                    .fromEndTime(swapOffer.getFromGroup().getEndTime())
                    .fromDay(swapOffer.getFromGroup().getDayOfWeek())
                    .toDay(swapOffer.getToGroup().getDayOfWeek())
                    .build();

            swapOffers.add(swapOfferFront);
        }


        return swapOffers;
    }

    /**
     * GET request for SwapOffers created by requesting user
     * Method maps original persisted SwapOffer entities to custom entities
     * and filters all SwapOffers to be created by the requesting user
     * @param request to extract username from
     * @return list of frontend specific SwapOffer models
     */
    @GetMapping(path = "/swapOffer/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SwapOfferFront> getSwapOffer(HttpServletRequest request) {
        String username = tokenService.getUsernameFromRequest(request);
        List<SwapOffer> allSwapOffers = null;
        Optional<User> users = userRepository.findByLoginName(username);

        if (users.isPresent()) {
            Optional<Student> students = studentRepository.findByUser(users.get());
            if (students.isPresent()) {
                Student student = students.get();
                allSwapOffers = swapOfferRepository.findByStudent(student);
            }
        }
        List<SwapOfferFront> frontSwapOffers = new ArrayList<SwapOfferFront>();

        for (SwapOffer swapOffer : allSwapOffers) {
            SwapOfferFront swapOfferFront = SwapOfferFront.builder()
                    .Id(swapOffer.getId())
                    .fromGroup(swapOffer.getFromGroup().getGroupChar())
                    .toGroup(swapOffer.getToGroup().getGroupChar())
                    .courseName(swapOffer.getToGroup().getCourseComponent().getCourse().getTitle())
                    .courseType(swapOffer.getToGroup().getCourseComponent().getType().toString())
                    .toStartTime(swapOffer.getToGroup().getStartTime())
                    .toEndTime(swapOffer.getToGroup().getEndTime())
                    .fromStartTime(swapOffer.getFromGroup().getStartTime())
                    .fromEndTime(swapOffer.getFromGroup().getEndTime())
                    .fromDay(swapOffer.getFromGroup().getDayOfWeek())
                    .toDay(swapOffer.getToGroup().getDayOfWeek())
                    .build();

            frontSwapOffers.add(swapOfferFront);
        }
        return frontSwapOffers;
    }

}
