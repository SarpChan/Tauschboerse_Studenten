package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.Singular;

/**
 * A student is a user 
 * Each student has an enrolment number and an email adress
 * as well as a exam regulation and their enrolment term
 */
@Entity
@SuperBuilder
public class Student extends Role {

    @Getter @Setter
    private int enrolementNumber;

    @Getter @Setter
    private String mail;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(mappedBy = "students")
    private ExamRegulation examRegulation;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(mappedBy = "enroledStudents")
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
    @ManyToMany(mappedBy = "students")
    private Set<Group> groups;

    @Singular("swapOffer")
    @Getter @Setter
    @OneToMany(mappedBy = "student")
    private Set<SwapOffer> swapOffers;

}