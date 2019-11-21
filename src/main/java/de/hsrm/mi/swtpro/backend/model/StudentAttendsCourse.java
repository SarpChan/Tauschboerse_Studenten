package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StudentAttendsCourse {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Student student;

    @OneToOne
    private Course course;

    @OneToOne
    private Term term;


    @Deprecated
    public StudentAttendsCourse(Student student, Course course, Term term) {
        this.student = student;
        this.course = course;
        this.term = term;
    }

    private StudentAttendsCourse(Builder builder) {
        this.student = builder.student;
        this.course = builder.course;
        this.term = builder.term;
    }

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

}