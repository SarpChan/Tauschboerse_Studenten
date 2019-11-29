package de.hsrm.mi.swtpro.backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class ModuleInCurriculum {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private int termPeriod;

    @Getter @Setter
    @ManyToOne
    private Curriculum curriculum;

    @Getter @Setter
    @ManyToOne
    private Module module;
}