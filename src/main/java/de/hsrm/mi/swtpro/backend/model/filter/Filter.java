package de.hsrm.mi.swtpro.backend.model.filter;

import lombok.*;

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
