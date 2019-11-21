package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Academic Term
 * the time interval in which an education facility holds classes
 * most common cycles are 2 terms per year, known as semester
 * the courses offered each term may vary
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Term {
    @Id
    @GeneratedValue
    private long id;
    private Date startDate;
    private Date endDate;
    private int period;

    @ManyToMany
    private Set<Course> courses;

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
    private Term(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.period = builder.period;
        this.courses = builder.courses;
    }

    /**
     * Builder class 
     * defines the parameters of the Term object to be built
     */
    public static class Builder {
        private Date startDate;
        private Date endDate;
        private int period;
        private Set<Course> courses;

        public Builder() {
            this.courses = new HashSet<Course>();
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

        public Term build() {
            return new Term(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }    

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
}