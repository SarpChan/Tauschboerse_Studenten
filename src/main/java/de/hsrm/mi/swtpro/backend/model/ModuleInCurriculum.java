package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    /**
     * Shows in which term the referred module takes place,
     * without regarding if it is a winter or summer term.
     */
    @Getter
    @Setter
    @NotNull
    private int termPeriod;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="curriculum_id")
    @NotNull
    private Curriculum curriculum;

    @Getter @Setter
    @ManyToOne(cascade= CascadeType.ALL)
    @NotNull
    private Module module;
}