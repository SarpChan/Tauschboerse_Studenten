package de.hsrm.mi.swtpro.backend.service.filterfactories;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseFilterFactory {

    @Getter @Setter
    private Filter[] filters;

    private boolean isFiltersEmpty() {
        return filters.length == 0;
    }

    public List<Course> filterCourses(List<Course> courses) {
        if(isFiltersEmpty()) {
            return courses;
        }
        return courses;
    }

    private List<Course> filterForExamRegulation(List<Course> courses) {
        //TODO FIlterLogik implementieren
        return courses;
    }
}
