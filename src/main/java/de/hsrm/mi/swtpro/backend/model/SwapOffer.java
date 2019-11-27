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

    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @Getter @Setter
    private Student creator;

    @ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
    @JoinColumn(name="from_group_id")
    @Getter @Setter
    private Group fromGroup;

    @ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
    @JoinColumn(name="to_group_id")
    @Getter @Setter
    private Group toGroup;

    }