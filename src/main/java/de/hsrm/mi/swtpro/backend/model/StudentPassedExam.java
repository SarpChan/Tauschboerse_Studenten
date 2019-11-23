package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
class StudentPassedExam {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private boolean passed;

    @Getter @Setter
    @OneToOne
    private Student student;

    @Getter @Setter
    @OneToOne
    private CourseComponent courseComponent;


    @Deprecated
    public StudentPassedExam(Student student, CourseComponent courseComponent, boolean passed) {
        this.student = student;
        this.courseComponent = courseComponent;
        this.passed = passed;
    }

    @Deprecated
    private StudentPassedExam(Builder builder) {
        this.student = builder.student;
        this.courseComponent = builder.courseComponent;
        this.passed = builder.passed;
    }

    @Deprecated
    public static class Builder {
        private Student student;
        private CourseComponent courseComponent;
        private boolean passed;

        public Builder(Student student, CourseComponent courseComponent) {
            this.student = student;
            this.courseComponent = courseComponent;
        }

        public Builder hasPassed(boolean passed) {
            this.passed = passed;
            return this;
        }

        public StudentPassedExam build() {
            return new StudentPassedExam(this);
        }
    }
}