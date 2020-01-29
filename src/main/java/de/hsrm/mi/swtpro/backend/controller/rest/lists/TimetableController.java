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
import de.hsrm.mi.swtpro.backend.model.Lecturer;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.Room;
import de.hsrm.mi.swtpro.backend.model.TimetableModule;
import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.model.filter.Comparator;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.ModuleFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.CourseComponentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.CourseRepository;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.LecturerRepository;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGenerator;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleRepository;
import de.hsrm.mi.swtpro.backend.service.repository.RoomRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    

    ServiceGenerator serviceGenerator;

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
     * to update the modules of a timetable
     * @param timetableModuleList
     * @return
     */
    @PostMapping(path = "/timetableUpdate", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<String> updateTimetable(@RequestBody TimetableModule timetableModule) {
        try{
            Group group = groupRepository.getOne(timetableModule.getGroupID());
            CourseComponent courseComponent = courseComponentRepository.getOne(group.getCourseComponent().getId());
            Course course = courseRepository.getOne(group.getCourseComponent().getCourse().getId());
            Room room = roomRepository.getOne(group.getRoom().getId());
            User user = userRepository.getOne(group.getLecturer().getId());
            group.setStartTime(timetableModule.getStartTime());
            group.setEndTime(timetableModule.getEndTime());
            group.setDayOfWeek(timetableModule.getDayOfWeek());
            group.setGroupChar(timetableModule.getGroupChar());
            groupRepository.save(group);
            course.setTitle(timetableModule.getCourseTitle());
            courseRepository.save(course);
            courseComponent.setType(timetableModule.getCourseType());
            courseComponentRepository.save(courseComponent);
            room.setNumber(timetableModule.getRoomNumber());
            roomRepository.save(room);
            user.setFirstName(timetableModule.getLecturerName());
            userRepository.save(user);
        return new ResponseEntity<>("timetableUpdate Succses",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}