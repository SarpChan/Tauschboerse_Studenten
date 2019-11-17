package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 */
@Entity
class StudentPassedExam {
    @Id
    @GeneratedValue
    private long id;
    private boolean passed;

    @OneToOne
    private Student student;

    @OneToOne
    private CourseComponent courseComponent;


    @Deprecated
    public StudentPassedExam(Student student, CourseComponent courseComponent, boolean passed) {
        this.student = student;
        this.courseComponent = courseComponent;
        this.passed = passed;
    }

    private StudentPassedExam(Builder builder) {
        this.student = builder.student;
        this.courseComponent = builder.courseComponent;
        this.passed = builder.passed;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public CourseComponent getCourseComponent() {
        return courseComponent;
    }

    public void setCourseComponent(CourseComponent courseComponent) {
        this.courseComponent = courseComponent;
    }

}