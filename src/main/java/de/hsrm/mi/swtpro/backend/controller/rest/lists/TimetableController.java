package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.TimetableModule;
import de.hsrm.mi.swtpro.backend.model.filter.Comparator;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import de.hsrm.mi.swtpro.backend.service.filterfactories.ModuleFilterFactory;
import de.hsrm.mi.swtpro.backend.service.repository.CourseRepository;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/lists")
public class TimetableController {

    @Autowired
    ModuleRepository moduleRepository;
    @Autowired 
    GroupRepository groupRepository;

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
        List<TimetableModule> timetable = new ArrayList<TimetableModule>();
        for(Module module: allModules){
            for(Course course: module.getCourses()){
                for(CourseComponent courseComponent : course.getCourseComponents()){
                    for(Group group: courseComponent.getGroups()){
                        TimetableModule timetableModule = TimetableModule.builder()
                        .groupID(group.getId())
                        .groupChar(group.getGroupChar())
                        .dayOfWeek(group.getDayOfWeek())
                        .startTime(group.getStartTime())
                        .endTime(group.getEndTime())
                        .lecturerName(group.getLecturer().getUser().getLastName())
                        .lecturerNameAbbreviation("Placeholder Abbreviation")
                        .courseComponentID(courseComponent.getId())
                        .courseType(courseComponent.getType())
                        .moduleTitle(module.getTitle())
                        .moduleTitleAbbreviation("Placeholder Abbreviation")
                        .build();
                        timetable.add(timetableModule);
                    }
                }
            }
        }
        return timetable;
    }


    @PostMapping(path = "/timetableUpdate", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TimetableModule> updateTimetable(@RequestBody List<TimetableModule> timetableModuleList) {
     for(TimetableModule module : timetableModuleList){
        
        groupRepository.findById( module.getGroupID());
       
        }

        return null;
        /*List<Module> allModules = moduleRepository.findAll();

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
        List<TimetableModule> timetable = new ArrayList<TimetableModule>();
        for(Module module: allModules){
            for(Course course: module.getCourses()){
                for(CourseComponent courseComponent : course.getCourseComponents()){
                    for(Group group: courseComponent.getGroups()){
            

                        TimetableModule timetableModule = TimetableModule.builder()
                        .groupID(group.getId())
                        .groupChar(group.getGroupChar())
                        .dayOfWeek(group.getDayOfWeek())
                        .startTime(group.getStartTime())
                        .endTime(group.getEndTime())
                        .lecturerName(group.getLecturer().getUser().getLastName())
                        .lecturerNameAbbreviation("Placeholder Abbreviation")
                        .courseComponentID(courseComponent.getId())
                        .courseType(courseComponent.getType())
                        .moduleTitle(module.getTitle())
                        .moduleTitleAbbreviation("Placeholder Abbreviation")
                        .build();
                        timetable.add(timetableModule);
                    }
                }
            }
        }
        
        return timetable;*/
    }
}