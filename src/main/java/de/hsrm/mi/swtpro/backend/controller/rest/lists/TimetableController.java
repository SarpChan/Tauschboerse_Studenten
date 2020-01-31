package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.filter.Comparator;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.ModuleFilterFactory;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGenerator;
import de.hsrm.mi.swtpro.backend.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/lists")
public class TimetableController {

    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseComponentRepository courseComponentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ServiceGenerator serviceGenerator;

    /**
     * The methode handles the POST request
     * to get the modules of a timetable for a given exam regulation
     * @param examRegulation
     * @return list of timetable modules
     */

    @PostMapping(path = "/timetable", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TimetableModule> getModules(@RequestBody ExamRegulation examRegulation) {
        List<Module> allModules = moduleRepository.findAll();
        Comparator comparator = Comparator.builder()
        .comparatorType(ComparatorType.EQUALS)
        .comparatorValue(examRegulation.getId())
        .build();
        Filter filter = Filter.builder()
        .attribute("examRegulationId")
        .comparator(comparator)
        .build();
        Filter [] filters = {filter};
        ModuleFilterFactory filterFactory = ModuleFilterFactory.builder().filters(filters).build();
        allModules = filterFactory.filter(allModules);

        return serviceGenerator.timetableModuleFromModules(allModules);
    }

    /**
     * The methode handles the POST request
     * to get the modules of a timetable for a specific term
     * @param 
     * @return list of timetable modules
     */

    @GetMapping(path = "/date_timetable/{term}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TimetableModule> getModulesForTerm(@PathVariable int term) {
        List<Module> allModules = moduleRepository.findAll();
        Comparator comparator = Comparator.builder()
        .comparatorType(ComparatorType.EQUALS)
        .comparatorValue(term)
        .build();
        Filter filter = Filter.builder()
        .attribute("term")
        .comparator(comparator)
        .build();
        Filter [] filters = {filter};
        ModuleFilterFactory filterFactory = ModuleFilterFactory.builder().filters(filters).build();
        allModules = filterFactory.filter(allModules);
        return serviceGenerator.timetableModuleFromModules(allModules);
    }

    /**
     * The methode handles the POST request
     * to update the modules of a timetable
     * @param
     * @return
     */
    @PostMapping(path = "/timetableUpdate", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<String> updateTimetable(@RequestBody TimetableModule timetableModule) {
        try{
            Optional<Group> optionalGroup = groupRepository.findById(timetableModule.getGroupID());
            if(!optionalGroup.isPresent())
                return new ResponseEntity<>("Cant find GroupID in Database",HttpStatus.CONFLICT);

            Group group = optionalGroup.get();
            CourseComponent courseComponent = group.getCourseComponent();
            Course course = group.getCourseComponent().getCourse();
            Room room = group.getRoom();
            group.setStartTime(timetableModule.getStartTime());
            group.setEndTime(timetableModule.getEndTime());
            group.setDayOfWeek(timetableModule.getDayOfWeek().minus(1));
            group.setGroupChar(timetableModule.getGroupChar());
            course.setTitle(timetableModule.getCourseTitle());
            courseComponent.setType(timetableModule.getCourseType());
            room.setNumber(timetableModule.getRoomNumber());

            groupRepository.saveAndFlush(group);
        return new ResponseEntity<>("timetableUpdate Succses",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}