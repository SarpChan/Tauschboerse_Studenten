package de.hsrm.mi.swtpro.backend.model;

import lombok.*;

import javax.persistence.*;

/** 
    Relationship between a student and a group 
    The priority indicates
*/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentPriorizesGroup {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private int priority;

    @Getter @Setter
    @ManyToOne
    private Student student;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

}