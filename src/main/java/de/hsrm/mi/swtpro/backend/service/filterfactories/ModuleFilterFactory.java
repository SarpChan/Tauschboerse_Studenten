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

        /**
        * This list contains all filterable modules 
        * @param modules the list of modules to be filtered
        * @return returns a list of the filtered modules
        */
        public List<Module> filterModules(List<Module> modules) {
            final List<Module> filterableModules = new ArrayList<>(modules);
            if (isFiltersEmpty()) {
                return modules;
            }
            Arrays.stream(this.filters).forEach(filter -> {
                if(filter.getAttribute().equals("examRegulationId")) {
                    List<Module> moduleFilter = new ArrayList<>(filterableModules);
                    filterableModules.clear();
                    filterableModules.addAll(filterForExamRegulation(moduleFilter,filter));
                }
            });
            return filterableModules;
        }

        /**
        * This list contains all the modules for a given exam regulation. 
        * @param modules the list of modules to be filtered
        * @param forExamRegulationFilter the exam regulation filter
        * @return returns a list of the filtered modules
        */
        private List<Module> filterForExamRegulation(List<Module> modules, Filter forExamRegulationFilter){
            return modules.stream().filter(module -> module.getModulesInCurriculum().stream().anyMatch(moduleInCurriculum -> {
                if(forExamRegulationFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                    return moduleInCurriculum.getCurriculum().getExamRegulation().getId() ==  new Long(forExamRegulationFilter.getComparator().getComparatorValue().toString());
                }
                return false;
            })).collect(Collectors.toList());
        }
    }
    


