package de.hsrm.mi.swtpro.backend.service.helper;

import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.Module;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ServiceGenerator {

    /**
     * Creates list of timetable modules based on multiple modules
     *
     * @param modules list of modules as base for creation
     * @return list of timetable modules
     */
    public List<TimetableModule> timetableModuleFromModules(List<Module> modules) {
        List<TimetableModule> timetableModules = new ArrayList<>();
        int term = 0;
        for (Module module : modules) {
            for (ModuleInCurriculum moduleInCurriculum : module.getModulesInCurriculum()) {
                if (moduleInCurriculum.getModule().equals(module)) {
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
                                .lecturerNameAbbreviation(group.getLecturer().getUser().getLastName().substring(0, Math.min(3, course.getTitle().length())))
                                .courseComponentID(courseComponent.getId())
                                .courseType(courseComponent.getType())
                                .courseTitle(course.getTitle())
                                .courseTitleAbbreviation(course.getTitle().substring(0, Math.min(3, course.getTitle().length())))
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

    public List<TimetableModule> timetableModuleFromModulesForStudent(List<Module> modules, Student student) {
        List<TimetableModule> timetableModules = new ArrayList<>();
        int term = 0;
        for (Module module : modules) {
            for (ModuleInCurriculum moduleInCurriculum : module.getModulesInCurriculum()) {
                if (moduleInCurriculum.getModule().equals(module)) {
                    term = moduleInCurriculum.getTermPeriod();
                }
            }
            for (Course course : module.getCourses()) {

                for (CourseComponent courseComponent : course.getCourseComponents()) {
                    for (Group group : courseComponent.getGroups()) {
                        if (group.getStudents().contains(student)) {
                            TimetableModule timetableModule = TimetableModule.builder()
                                    .groupID(group.getId())
                                    .groupChar(group.getGroupChar())
                                    .dayOfWeek(group.getDayOfWeek())
                                    .startTime(group.getStartTime())
                                    .endTime(group.getEndTime())
                                    .lecturerName(group.getLecturer().getUser().getLastName())
                                    .lecturerNameAbbreviation(group.getLecturer().getUser().getLastName().substring(0, Math.min(3, course.getTitle().length())))
                                    .courseComponentID(courseComponent.getId())
                                    .courseType(courseComponent.getType())
                                    .courseTitle(course.getTitle())
                                    .courseTitleAbbreviation(course.getTitle().substring(0, Math.min(3, course.getTitle().length())))
                                    .roomNumber(group.getRoom().getNumber())
                                    .term(term)
                                    .build();
                            timetableModules.add(timetableModule);
                        }
                    }
                }
            }
        }
        return timetableModules;
    }


    /**
     * Creates list of timetable modules based on one module
     *
     * @param module as base for creation
     * @return list of timetable modules
     */
    public List<TimetableModule> timetableModuleFromModule(Module module) {
        List<TimetableModule> timetableModules = new ArrayList<>();
        int term = 0;
        for (ModuleInCurriculum moduleInCurriculum : module.getModulesInCurriculum()) {
            if (moduleInCurriculum.getModule().equals(module)) {
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
                            .lecturerNameAbbreviation(group.getLecturer().getUser().getLastName().substring(0, Math.min(3, group.getLecturer().getUser().getLastName().length())))
                            .courseComponentID(courseComponent.getId())
                            .courseType(courseComponent.getType())
                            .courseTitle(course.getTitle())
                            .courseTitleAbbreviation(course.getTitle().substring(0, Math.min(3, course.getTitle().length())))
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
     * returns JSON style module selection items
     *
     * @param curriculum as for list creation
     * @return list of module selection items
     */
    public List<ModuleSelectionItem> moduleSelectionItemFromCurriculum(Curriculum curriculum) {
        List<ModuleSelectionItem> moduleSelectionItems = new ArrayList<>();

        List<CourseType> moduleTypes;

        for (ModuleInCurriculum moduleInCurriculum : curriculum.getModulesInCurriculum()) {
            Set<CourseType> moduleTypeSet = new HashSet<>();
            Module module = moduleInCurriculum.getModule();

            for (Course course : module.getCourses()) {
                for (CourseComponent courseComponent : course.getCourseComponents()) {
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


    /**
     * returns JSON style display course components
     *
     * @param student as base for list creation
     * @return list of display course components
     */
    public List<DisplayCourseComponent> swapOfferCoursesFromStudent(Student student) {

        List<DisplayCourseComponent> dpCourses = new ArrayList<>();
        List<CourseComponent> attendCourses = new ArrayList<>();

        for (Group group : student.getGroups()) {
            CourseComponent courseComponent = group.getCourseComponent();
            Course course = courseComponent.getCourse();

            if (!attendCourses.contains(courseComponent)) {

                if (courseComponent.getGroups().size() > 1) {

                    List<DisplayGroup> dpGroups = new ArrayList<>();

                    for (Group grouplist : courseComponent.getGroups()) {
                        if (grouplist.getGroupChar() != group.getGroupChar()) {
                            DisplayGroup dpGroup = DisplayGroup.builder()
                                    .id(grouplist.getId())
                                    .groupChar(grouplist.getGroupChar())
                                    .dayOfWeek(grouplist.getDayOfWeek())
                                    .startTime(grouplist.getStartTime())
                                    .endTime(grouplist.getEndTime())
                                    .lecturer(grouplist.getLecturer().getUser().getLastName())
                                    .room(grouplist.getRoom().getNumber())
                                    .build();

                            dpGroups.add(dpGroup);
                        }
                    }

                    DisplayCourseComponent dpCourseComponent = DisplayCourseComponent.builder()
                            .id(courseComponent.getId())
                            .myGroupId(group.getId())
                            .courseId(course.getId())
                            .title(course.getTitle())
                            .type(courseComponent.getType().toString())
                            .myGroupChar(group.getGroupChar())
                            .creditPoints(courseComponent.getCreditPoints())
                            .groups(dpGroups)
                            .build();

                    dpCourses.add(dpCourseComponent);

                }
                attendCourses.add(courseComponent);
            }
        }

        return dpCourses;
    }

}
