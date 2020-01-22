package de.hsrm.mi.swtpro.backend.service.helper;

import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.service.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Generator {

    public List<TimetableModule> timetableModuleFromModules(List<Module> modules) {
        List<TimetableModule> timetableModules = new ArrayList<>();

        for(Module module: modules) {
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
                                .build();
                        timetableModules.add(timetableModule);
                    }
                }
            }
        }
        return timetableModules;
    }

    public List<ModuleSelectionItem> moduleSelectionItemFromModule(List<Module> modules) {
        List<ModuleSelectionItem> moduleSelectionItems = new ArrayList<>();
        return moduleSelectionItems;
    }

    @Autowired
    TermRepository termRepository;

    /**
     * get the running term depending on current date
     * @return current term
     */
    public Term getCurrentTerm() {
        Date today = Calendar.getInstance().getTime();
        List<Term> terms = termRepository.findByEndDateBefore(today);
        return terms.get(0);
    }
}
