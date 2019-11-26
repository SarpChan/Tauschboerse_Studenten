package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;

import java.util.Set;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * A student is a user 
 * Each student has an enrolment number and an email adress
 * as well as a exam regulation and their enrolment term
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@SuperBuilder
public class Student extends Role {

    @Getter @Setter
    private int enrolementNumber;

    @Getter @Setter
    private String mail;

    @Getter @Setter

    @OneToOne
    private ExamRegulation examRegulation;

    @Getter @Setter

    @OneToOne
    private Term enrolmentTerm;

    @Getter @Setter
    @OneToMany
    private Set<StudentAttendsCourse> attendsCourses;

    @Getter @Setter
    @OneToMany
    private Set<StudentPassedExam> passedExams;

    @Getter @Setter
    @OneToMany
    private Set<Group> attendsGroups;

    @Getter @Setter
    @OneToMany(mappedBy = "creator")
    private List<SwapOffer> swapOffers;

}