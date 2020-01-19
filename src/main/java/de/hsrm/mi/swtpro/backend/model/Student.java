package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.*;

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
    //@Pattern(regexp = "[0-9]*")
    @NotNull
    private int enrolmentNumber;

    @Getter @Setter
    //@Pattern(regexp = "[a-zA-Z0-9-+_.]+@[a-z0-9-+_]+")
    @NotEmpty
    private String mail;

    @Getter @Setter
    @ManyToOne
    @NotNull
    private ExamRegulation examRegulation;

    @Getter @Setter
    @ManyToOne
    @NotNull
    private Term enrolmentTerm;

    @Singular("attendCourse")
    @Getter @Setter
    @OneToMany(mappedBy = "student",cascade= CascadeType.ALL)
    private Set<StudentAttendsCourse> attendCourses;

    @Singular("prioritizeGroup")
    @Getter @Setter
    @OneToMany(mappedBy = "student")
    private Set<StudentPrioritizesGroup> prioritizeGroups;

    @Singular("passedExam")
    @Getter @Setter
    @OneToMany(mappedBy = "student",cascade= CascadeType.ALL)
    private Set<StudentPassedExam> passedExams;

    @Singular("group")
    @Getter @Setter
    @ManyToMany(mappedBy = "students",cascade= CascadeType.ALL)
    private Set<Group> groups;

    @Singular("swapOffer")
    @Getter @Setter
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<SwapOffer> swapOffers;
}
