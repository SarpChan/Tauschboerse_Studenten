package de.hsrm.mi.swtpro.backend.model.filter;


import lombok.*;

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
