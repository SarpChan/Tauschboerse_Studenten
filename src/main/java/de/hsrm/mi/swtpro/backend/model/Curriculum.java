package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

/**
 * A Curriculum has a maximum number of terms, belongs to one examRegulation 
 * and has a list of modules that are part of this Curriculum
 */
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
    private int maxTerms;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="exam_regulation_id")
    @NotNull
    private ExamRegulation examRegulation;

    @Singular("moduleInCurriculum")
    @Getter @Setter
    @OneToMany(mappedBy = "curriculum",cascade= CascadeType.ALL)
    private Set<ModuleInCurriculum> modulesInCurriculum ;
}