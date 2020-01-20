package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.controller.login.security.JwtAuthentication;
import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.CourseFilterFactory;
import de.hsrm.mi.swtpro.backend.service.filterfactories.ModuleFilterFactory;
import de.hsrm.mi.swtpro.backend.service.filterfactories.SwapOfferFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.hsrm.mi.swtpro.backend.controller.login.security.TokenService;
import sun.tools.jstat.Token;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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


    @Autowired
    ModuleRepository moduleRepository;
    ModuleInCurriculumRepository moduleInCurriculumRepository;
    CourseComponentRepository courseComponentRepository;
    TermRepository termRepository;
    GroupRepository groupRepository;
    TokenService tokenService;
    UserRepository userRepository;
    StudentRepository studentRepository;

    @GetMapping(path = "/module/prioritize", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ModuleItem> getModuleItem(HttpServletRequest request) {

        String username = tokenService.getUsername(request);
        Optional<User> users = userRepository.findByLoginName(username);

        if(users.isPresent()) {
            Optional<Student> students = studentRepository.findByUser(users.get());
            if (students.isPresent()) {
                Student student = students.get();

//                List<Module> filteredModules = moduleRepository.findAll();
//                ModuleFilterFactory moduleFilterFactory = ModuleFilterFactory.builder().filters(filters).build();
//                filteredModules = moduleFilterFactory.filter(filteredModules);

                List<ModuleItem> moduleItems = new ArrayList<>();

                Date today = Calendar.getInstance().getTime();
                List<Term> terms = termRepository.findByEndDateBefore(today);
                Term term = terms.get(0);

                ExamRegulation po = student.getExamRegulation();
                Set<Curriculum> curriculums = po.getCurriculums();
                Curriculum curriculum = curriculums.iterator().next();
                List<ModuleInCurriculum> modulesInCurriculum = moduleInCurriculumRepository.findByCurriculum(curriculum);

                for (ModuleInCurriculum moduleInCurriculum : modulesInCurriculum) {

                    List<Module> modules = moduleRepository.findByModulesInCurriculum(moduleInCurriculum);

                    for (Module module : modules) {

                        ModuleItem moduleItem = ModuleItem.builder()
                                .id(module.getId())
                                .title(module.getTitle())
                                .creditPoints(module.getCreditPoints())
                                .semester(moduleInCurriculum.getTermPeriod())
                                .build();

                        List<Course> courses = courseRepository.findByModuleAndTerm(module, term);

                        for (Course course : courses) {
                            List<CourseComponent> courseComponents = courseComponentRepository.findByCourse(course);

                            for (CourseComponent courseComponent : courseComponents) {
                                List<Group> groups = groupRepository.findByCourseComponent(courseComponent);

                                for (Group group : groups) {
                                    TimetableModule timetableModule = TimetableModule.builder()
                                            .courseComponentID(courseComponent.getId())
                                            .groupID(group.getId())
                                            .startTime(group.getStartTime())
                                            .endTime(group.getEndTime())
                                            .dayOfWeek(group.getDayOfWeek())
                                            .lecturerName(group.getLecturer().getUser().getLastName())
                                            .lecturerNameAbbreviation(group.getLecturer().getUser().getLastName().substring(0, 3))
                                            .courseTitle(course.getTitle())
                                            .courseTitleAbbreviation(course.getTitle().substring(0, 12))
                                            .groupChar(group.getGroupChar())
                                            .roomNumber(group.getRoom().getNumber())
                                            .build();

                                    moduleItem.getTimetableModules().add(timetableModule);
                                }
                            }
                        }
                        moduleItems.add(moduleItem);
                    }
                }
                return moduleItems;
            }
        }
        return null;
    }

}
