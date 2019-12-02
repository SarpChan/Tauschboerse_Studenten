package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

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
    private long id;

    @Getter @Setter
    private int priority;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

}