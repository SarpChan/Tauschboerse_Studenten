package de.hsrm.mi.swtpro.backend.service.helper;

import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.Module;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ServiceGenerator {

    public List<TimetableModule> timetableModuleFromModules(List<Module> modules) {
        List<TimetableModule> timetableModules = new ArrayList<>();
        int term = 0;
        for(Module module: modules) {
            for(ModuleInCurriculum moduleInCurriculum : module.getModulesInCurriculum()){
                if(moduleInCurriculum.getModule().equals(module)){
                    term = moduleInCurriculum.getTermPeriod();
                }
            }
            for (Course course : module.getCourses()) {
                
                for (CourseComponent courseComponent : course.getCourseComponents()) {
                    for (Group group : courseComponent.getGroups()) {
                        TimetableModule timetableModule = TimetableModule.builder()
                                .groupID(group.getId())
                                .groupChar(group.getGroupChar())
                                .dayOfWeek(group.getDayOfWeek())
                                .startTime(group.getStartTime())
                                .endTime(group.getEndTime())
                                .lecturerName(group.getLecturer().getUser().getLastName())
                                .lecturerNameAbbreviation(group.getLecturer().getUser().getLastName().substring(0, 3))
                                .courseComponentID(courseComponent.getId())
                                .courseType(courseComponent.getType())
                                .courseTitle(course.getTitle())
                                .courseTitleAbbreviation(course.getTitle().substring(0, 3))
                                .roomNumber(group.getRoom().getNumber())
                                .term(term)
                                .build();
                        timetableModules.add(timetableModule);
                    }
                }
            }
        }
        return timetableModules;
    }

    public List<TimetableModule> timetableModuleFromModule(Module module) {
        List<TimetableModule> timetableModules = new ArrayList<>();
        int term = 0;
        for(ModuleInCurriculum moduleInCurriculum : module.getModulesInCurriculum()){
            if(moduleInCurriculum.getModule().equals(module)){
                term = moduleInCurriculum.getTermPeriod();
            }
        }
        for (Course course : module.getCourses()) {   
            for (CourseComponent courseComponent : course.getCourseComponents()) {
                for (Group group : courseComponent.getGroups()) {
                    TimetableModule timetableModule = TimetableModule.builder()
                            .groupID(group.getId())
                            .groupChar(group.getGroupChar())
                            .dayOfWeek(group.getDayOfWeek())
                            .startTime(group.getStartTime())
                            .endTime(group.getEndTime())
                            .lecturerName(group.getLecturer().getUser().getLastName())
                            .lecturerNameAbbreviation(group.getLecturer().getUser().getLastName().substring(0, 3))
                            .courseComponentID(courseComponent.getId())
                            .courseType(courseComponent.getType())
                            .courseTitle(course.getTitle())
                            .courseTitleAbbreviation(course.getTitle().substring(0, 12))
                            .roomNumber(group.getRoom().getNumber())
                            .term(term)
                            .build();
                    timetableModules.add(timetableModule);
                }
            }
        }
        return timetableModules;
    }

    /**
     * returns JSON style module table
     * @param curriculum
     * @return
     */
    public List<ModuleSelectionItem> moduleSelectionItemFromCurriculum(Curriculum curriculum) {
        List<ModuleSelectionItem> moduleSelectionItems = new ArrayList<>();
        
        List<CourseType> moduleTypes;

        for (ModuleInCurriculum moduleInCurriculum : curriculum.getModulesInCurriculum()) {
            Set<CourseType> moduleTypeSet = new HashSet<>();
            Module module = moduleInCurriculum.getModule();

            for(Course course :module.getCourses()){
                for(CourseComponent courseComponent: course.getCourseComponents()){
                    moduleTypeSet.add(courseComponent.getType());
                }
            }
            moduleTypes = new ArrayList<>(moduleTypeSet);

            ModuleSelectionItem moduleSelectionItem = ModuleSelectionItem.builder()
                .id(module.getId())
                .title(module.getTitle())
                .creditPoints(module.getCreditPoints())
                .semester(moduleInCurriculum.getTermPeriod())
                .timetableModules(timetableModuleFromModule(module))
                .moduleTypes(moduleTypes)
                .build();

            moduleSelectionItems.add(moduleSelectionItem);
        }
        return moduleSelectionItems;
    }

}
