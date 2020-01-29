package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.controller.rest.CampusCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.CourseComponentCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.CourseCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.GroupCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.LecturerCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.RoomCrudController;
import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.ModuleInCurriculum;
import de.hsrm.mi.swtpro.backend.model.TimetableModule;
import de.hsrm.mi.swtpro.backend.model.filter.Comparator;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.ModuleFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.CourseRepository;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGenerator;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleInCurriculumRepository;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/lists")
public class TimetableController {

    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    GroupRepository groupRepository;
    GroupCrudController groupCrudController;
    LecturerCrudController lecturerCrudController;
    CourseComponentCrudController courseComponentCrudController;
    CourseCrudController courseCrudController;
    RoomCrudController roomCrudController;
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

    @GetMapping(path = "/date_timetable", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TimetableModule> getCurrentModules(HttpServletRequest request) {
        List<Module> allModules = moduleRepository.findAll();
        Comparator comparator = Comparator.builder()
        .comparatorType(ComparatorType.EQUALS)
        .comparatorValue(1)
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
     * @param timetableModuleList
     * @return
     */
    @PostMapping(path = "/timetableUpdate", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<String> updateTimetable(@RequestBody TimetableModule timetableModule) {
        try{
            Optional<Group> group = groupRepository.findById(timetableModule.getGroupID());
            groupCrudController.updateGroup(group.get());
            lecturerCrudController.updateLecturer(group.get().getLecturer());
            courseCrudController.updateCourse(group.get().getCourseComponent().getCourse());
            courseComponentCrudController.updateCourseComponent(group.get().getCourseComponent());
            roomCrudController.updateRoom(group.get().getRoom());
            return new ResponseEntity<>("timetableUpdate Succses",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}