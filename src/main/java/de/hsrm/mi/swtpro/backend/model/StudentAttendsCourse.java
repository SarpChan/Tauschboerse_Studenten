package de.hsrm.mi.swtpro.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@Builder
public class StudentAttendsCourse {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    @ManyToOne
    private Student student;

    @Getter @Setter
    @ManyToOne
    private Course course;

    @Getter @Setter
    @ManyToOne
    private Term term;


    @Deprecated
    public StudentAttendsCourse(Student student, Course course, Term term) {
        this.student = student;
        this.course = course;
        this.term = term;
    }

    @Deprecated
    private StudentAttendsCourse(Builder builder) {
        this.student = builder.student;
        this.course = builder.course;
        this.term = builder.term;
    }

    @Deprecated
    public static class Builder {
        private Student student;
        private Course course;
        private Term term;

        public Builder withStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder forCourse(Course course) {
            this.course = course;
            return this;
        }

        public Builder inTerm(Term term) {
            this.term = term;
            return this;
        }

        public StudentAttendsCourse build() {
            return new StudentAttendsCourse(this);
        }
    }
}