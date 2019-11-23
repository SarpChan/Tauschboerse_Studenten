package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;
import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Academic Term
 * the time interval in which an education facility holds classes
 * most common cycles are 2 terms per year, known as semester
 * the courses offered each term may vary
 */
@Entity
@Builder
public class Term {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private Date startDate;

    @Getter @Setter
    private Date endDate;

    @Getter @Setter
    private int period;

    @Getter @Setter
    @ManyToMany
    private Set<Course> courses;

    @Getter @Setter
    @OneToMany
    private Set<StudentAttendsCourse> studentsAttendCourses;
    
    /**
     * Adds course to the collection of courses offered this term 
     * @param course
     */
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    /**
     * Removes course from the collection of courses offered this term
     * @param course
     */
    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    /**
     * Empties collection of all courses offered this term
     */
    public void clearCourses() {
        this.courses.clear();
    }

    /**
     * Checks if a given course is being offered this term
     * @param course
     * @return true if course is being offered this term
     */
    public boolean containsCourse(Course course) {
        return this.courses.contains(course);
    }

    @Deprecated
    public Term(Date startDate, Date endDate, int period) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = period;
        this.courses = new HashSet<Course>();
    }

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private Term(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.period = builder.period;
        this.courses = builder.courses;
        this.studentsAttendCourses = builder.studentsAttendCourses;
    }

    /**
     * Builder class 
     * defines the parameters of the Term object to be built
     */
    @Deprecated
    public static class Builder {
        private Date startDate;
        private Date endDate;
        private int period;
        private Set<Course> courses;
        private Set<StudentAttendsCourse> studentsAttendCourses;

        public Builder() {
            this.courses = new HashSet<Course>();
            this.studentsAttendCourses = new HashSet<StudentAttendsCourse>();
        }

        public Builder withStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder offersCourses(HashSet<Course> courses) {
            this.courses = courses;
            return this;
        }

        public Builder offersCourse(Course course) {
            this.courses.add(course);
            return this;
        }

        public Builder inPeriod(int period) {
            this.period = period;
            return this;
        }

        public Builder withStudentsAttendCourses(Set<StudentAttendsCourse> studentsAttendsCourses) {
            this.studentsAttendCourses = studentsAttendsCourses;
            return this;
        }

        public Term build() {
            return new Term(this);
        }
    }
}