package de.hsrm.mi.swtpro.backend.model.filter;

import lombok.*;

/**
 * Filter object defines a filterable attribute and its comparator
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Filter {
    @Getter
    @Setter
    private String attribute;
    @Getter
    @Setter
    private Comparator comparator;
}

