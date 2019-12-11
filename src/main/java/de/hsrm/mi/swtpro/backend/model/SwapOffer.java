package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * A swapOffer can be created by a student, who wants to swap form one group to another
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class SwapOffer {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    @GeneratedValue
    private Timestamp date;

    @Getter @Setter
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    private Student student;

    @Getter @Setter
    @ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
    private Group fromGroup;

    @Getter @Setter
    @ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
    private Group toGroup;

}