package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class ModuleInCurriculum {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @NotNull
    private int termPeriod;

    @Getter @Setter
    @ManyToOne
    @NotNull
    private Curriculum curriculum;

    @Getter @Setter
    @ManyToOne
    @NotNull
    private Module module;
}