package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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
    private long id;

    @Getter @Setter
    private int termPeriod;

    @Getter @Setter
    @ManyToOne
    private ExamRegulation examRegulation;

    @Singular("moduleInCurriculum")
    @Getter @Setter
    @OneToMany(mappedBy = "curriculum",cascade= CascadeType.ALL)
    private Set<ModuleInCurriculum> modulesInCurriculum ;
}