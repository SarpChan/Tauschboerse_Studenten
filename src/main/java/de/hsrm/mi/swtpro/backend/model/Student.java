package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * A student is a user 
 * Each student has an enrolment number and an email adress
 * as well as a exam regulation and their enrolment term
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@SuperBuilder
public class Student extends Role {

    @Getter @Setter
    private int enrolementNumber;

    @Getter @Setter
    private String mail;

    @Getter @Setter
    @ManyToOne
    private ExamRegulation examRegulation;

    @Getter @Setter
    @ManyToOne
    private Term enrolmentTerm;

    @Builder.Default
    @Getter @Setter
    @OneToMany(mappedBy = "student")
    private Set<StudentAttendsCourse> attendCourses = new HashSet<>();

    @Builder.Default
    @Getter @Setter
    @OneToMany(mappedBy = "student")
    private Set<StudentPriorizesGroup> prioritizeGroups = new HashSet<>();

    @Builder.Default
    @Getter @Setter
    @OneToMany(mappedBy = "student")
    private Set<StudentPassedExam> passedExams = new HashSet<>();

    @Builder.Default
    @Getter @Setter
    @ManyToMany(mappedBy = "students")
    private Set<Group> groups = new HashSet<>();

    @Builder.Default
    @Getter @Setter
    @OneToMany(mappedBy = "student")
    private Set<SwapOffer> swapOffers = new HashSet<>();

}