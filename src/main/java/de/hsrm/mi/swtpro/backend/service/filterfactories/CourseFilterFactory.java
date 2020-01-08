package de.hsrm.mi.swtpro.backend.service.filterfactories;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CourseFilterFactory extends FilterFactory<Course> {

    @Override
    public List<Course> filter(List<Course> toFilter) {
        final List<Course> filterableCourses = new ArrayList<>(toFilter);
        if (isFiltersEmpty()) {
            return toFilter;
        }
        Arrays.stream(this.filters).forEach(filter -> {
            if(filter.getAttribute().equals("examRegulationId")) {
                List<Course> filterTmp = new ArrayList<>(filterableCourses);
                filterableCourses.clear();
                filterableCourses.addAll(filterForExamRegulation(filterTmp,filter));
            }
        });
        return filterableCourses;
    }

    private List<Course> filterForExamRegulation(List<Course> courses, Filter forExamRegulationFilter) {
        return courses.stream().filter(course -> course.getModules().stream().anyMatch(module -> {
            return module.getModulesInCurriculum().stream().anyMatch(moduleInCurriculum -> {
                if (forExamRegulationFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return moduleInCurriculum.getCurriculum().getExamRegulation().getId() == (long) forExamRegulationFilter.getComparator().getComparatorValue();
                }
                return false;
            });
        })).collect(Collectors.toList());
    }
}
