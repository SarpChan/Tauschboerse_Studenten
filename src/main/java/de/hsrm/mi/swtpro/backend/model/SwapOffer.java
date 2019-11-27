package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.TimeStamp;

/**
 * A swapOffer can be created by a student, who wants to swap form one group to another
 */
@Entity
@AllArgsConstructor
@Builder
public class SwapOffer {
    
    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    @GeneratedValue
    private TimeStamp date;

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