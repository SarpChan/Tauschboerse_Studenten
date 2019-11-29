package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

/**
 * A student is a user 
 * Each student has an enrolment number and an email adress
 * as well as a exam regulation and their enrolment term
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Student extends Role {

    @Getter @Setter
    private int enrolementNumber;

    @Getter @Setter
    private String mail;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private ExamRegulation examRegulation;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private Term enrolmentTerm;

    @Singular("attendCourse")
    @Getter @Setter
    @OneToMany(mappedBy = "student")
    private Set<StudentAttendsCourse> attendCourses;

    @Singular("prioritizeGroup")
    @Getter @Setter
    @OneToMany(mappedBy = "student")
    private Set<StudentPriorizesGroup> prioritizeGroups;

    @Singular("passedExam")
    @Getter @Setter
    @OneToMany(mappedBy = "student")
    private Set<StudentPassedExam> passedExams;

    @Singular("group")
    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "group_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;

    /*@Singular("swapOffer")
    @Getter @Setter
    @OneToMany(mappedBy = "student")
    private Set<SwapOffer> swapOffers;
     */
}