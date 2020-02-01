package de.hsrm.mi.swtpro.backend.service.filterfactories;

import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Abstract class to create filter for a specific models
 * @param <T> type for model
 */
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class FilterFactory<T> {

    @Getter @Setter
    protected Filter[] filters;

    /**
     * Checks if filters are empty
     * @return true if filters are empty
     */
    protected boolean isFiltersEmpty() {
        return filters.length == 0;
    }

    /**
     * Abstract filter method
     * Filters given list of type T based on filters
     * @param toFilter list of type T
     * @return filtered list by filters of factory
     */
    abstract List<T> filter(List<T> toFilter);

}
