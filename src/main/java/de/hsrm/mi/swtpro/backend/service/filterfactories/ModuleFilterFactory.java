package de.hsrm.mi.swtpro.backend.service.filterfactories;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import lombok.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleFilterFactory {

        @Getter
        @Setter
        private Filter[] filters;

        private boolean isFiltersEmpty() {
            return filters.length == 0;
        }

        public List<Module> filterModules(List<Module> courses) {
            final List<Module> filterableModules = new ArrayList<>(courses);
            if (isFiltersEmpty()) {
                return courses;
            }
            Arrays.stream(this.filters).forEach(filter -> {
                if(filter.getAttribute().equals("curriculumId")) {
                    List<Module> filterTmp = new ArrayList<>(filterableModules);
                    filterableModules.clear();
                    filterableModules.addAll(filterForCurriculum(filterTmp,filter));
                }
            });
            return filterableModules;
        }

        /*private List<Course> filterForExamRegulation(List<Course> courses, Filter forExamRegulationFilter) {
            return courses.stream().filter(course -> course.getModules().stream().anyMatch(module -> {
                return module.getModulesInCurriculum().stream().anyMatch(moduleInCurriculum -> {
                    if (forExamRegulationFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                        return moduleInCurriculum.getCurriculum().getExamRegulation().getId() == (long) forExamRegulationFilter.getComparator().getComparatorValue();
                    }
                    return false;
                });
            })).collect(Collectors.toList());
        }*/

        private List<Module> filterForCurriculum(List<Module> modules, Filter forCurriculumFilter){
            return modules.stream().filter(module -> module.getModulesInCurriculum().stream().anyMatch(moduleInCurriculum -> {
                if(forCurriculumFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return moduleInCurriculum.getCurriculum().getId() == (long) forCurriculumFilter.getComparator().getComparatorValue();
                }
                return false;
            })).collect(Collectors.toList());
        }
    }


