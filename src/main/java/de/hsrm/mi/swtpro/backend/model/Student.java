package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

/**
 * A student is a user 
 * Each student has an enrolment number and an email adress
 * as well as a exam regulation and their enrolment term
 */
@Entity
@Builder
public class Student extends Role {

    @Getter @Setter
    @GeneratedValue
    private int enrolementNumber;

    @Getter @Setter
    private String mail;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne
    private ExamRegulation examRegulation;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
     /**
     * Builder class 
     * defines the parameters of the student object to be built
     */
    @Deprecated
    public static class Builder {
        private ExamRegulation examRegulation;
        private String mail;
        private Term enrolmentTerm;
        private int enrolementNumber;
        private Set<StudentAttendsCourse> attendsCourses;
        private Set<StudentPassedExam> passedExams;
        private Set<Group> attendsGroups;

        public Builder() {
            this.attendsCourses = new HashSet<StudentAttendsCourse>();
            this.attendsGroups = new HashSet<Group>();
            this.passedExams = new HashSet<StudentPassedExam>();
        }

        public Builder hasEnrolementNumber(int enrolementNumber) {
            this.enrolementNumber = enrolementNumber;
            return this;
        }

        public Builder inEnrolementTerm(Term enrolmentTerm) {
            this.enrolmentTerm = enrolmentTerm;
            return this;
        }

        public Builder hasMail(String mail) {
            this.mail = mail;
            return this;
        }

        public Builder withPassedExams(Set<StudentPassedExam> passedExams) {
            this.passedExams = passedExams;
            return this;
        }
    }
}