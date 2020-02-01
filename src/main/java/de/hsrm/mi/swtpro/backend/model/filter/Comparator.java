package de.hsrm.mi.swtpro.backend.model.filter;


import lombok.*;

/**
 * Class to define a comparator type and a value
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comparator {
    @Getter
    @Setter
    ComparatorType comparatorType;
    @Getter
    @Setter
    Object comparatorValue;
}
