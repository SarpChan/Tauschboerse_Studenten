package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
    Relationship between a student and a group
    The priority indicates
*/
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class StudentPrioritizesGroup {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @NotNull
    private int priority;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="student_id")
    @NotNull
    private Student student;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="group_id")
    @NotNull
    private Group group;

}