package de.hsrm.mi.swtpro.backend.service.filterfactories;

import de.hsrm.mi.swtpro.backend.model.filter.Filter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class FilterFactory<T> {

    @Getter
    @Setter
    protected Filter[] filters;

    protected boolean isFiltersEmpty() {
        return filters.length == 0;
    }

    abstract List<T> filter(List<T> toFilter);

}
