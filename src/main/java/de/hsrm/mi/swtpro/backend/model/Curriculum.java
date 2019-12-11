package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class Curriculum {
    
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
    private ExamRegulation examRegulation;

    @Singular("moduleInCurriculum")
    @Getter @Setter
    @OneToMany(mappedBy = "curriculum")
    private Set<ModuleInCurriculum> modulesInCurriculum;
}