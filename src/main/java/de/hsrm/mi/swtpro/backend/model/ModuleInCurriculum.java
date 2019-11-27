package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Builder
public class ModuleInCurriculum {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private int termPeriod;

    @Getter @Setter
    @ManyToOne(mappedBy = "modulesInCurriculum")
    private Curriculum curriculum;

    @Getter @Setter
    @ManyToOne(mappedBy = "modulesInCurriculum")
    private Module module;
}