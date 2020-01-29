package de.hsrm.mi.swtpro.backend.service.filterfactories;

import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.filter.ComparatorType;
import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@SuperBuilder
public class ModuleFilterFactory extends FilterFactory<Module> {

    /**
    * This list contains all filterable modules 
    * @param toFilter the list of modules to be filtered
    * @return returns a list of the filtered modules
    */
        
    @Override
    public List<Module> filter(List<Module> toFilter) {
        final List<Module> filterableModules = new ArrayList<>(toFilter);
        if (isFiltersEmpty()) {
            return toFilter;
        }            
        Arrays.stream(this.filters).forEach(filter -> {
            if(filter.getAttribute().equals("examRegulationId")) {
                List<Module> moduleFilter = new ArrayList<>(filterableModules);
                filterableModules.clear();
                filterableModules.addAll(filterForExamRegulation(moduleFilter,filter));
            }
            else if(filter.getAttribute().equals("term")){
                List<Module> moduleFilter = new ArrayList<>(filterableModules);
                filterableModules.clear();
                filterableModules.addAll(filterForTerm(moduleFilter,filter));
            }
        });
        return filterableModules;
    }

    /**
    * This list contains all modules for a given exam regulation
    * @param modules the list of modules to be filtered
    * @param forExamRegulationFilter exam regulation filter containing the id to be compared
    * @return returns a list of the filtered modules
    */

    private List<Module> filterForExamRegulation(List<Module> modules, Filter forExamRegulationFilter){
       return modules.stream().filter(module -> module.getModulesInCurriculum().stream().anyMatch(moduleInCurriculum -> {
            if(forExamRegulationFilter.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                return moduleInCurriculum.getCurriculum().getExamRegulation().getId() == Long.parseLong(forExamRegulationFilter.getComparator().getComparatorValue().toString());
            }
            return false;
        })).collect(Collectors.toList());
    }

    /**
    * This list contains all modules for a given term
    * @param modules the list of modules to be filtered
    * @param forTerm term filter containing the term to be compared
    * @return returns a list of the filtered modules
    */

    private List<Module> filterForTerm(List<Module> modules, Filter forTerm){
        return modules.stream().filter(module -> module.getModulesInCurriculum().stream().anyMatch(moduleInCurriculum -> {
             if(forTerm.getComparator().getComparatorType() == ComparatorType.EQUALS) {
                 return moduleInCurriculum.getTermPeriod() == Integer.parseInt(forTerm.getComparator().getComparatorValue().toString());
             }
             return false;
         })).collect(Collectors.toList());
     }
}
