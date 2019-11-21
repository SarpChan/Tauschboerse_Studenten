package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Represents an element of the Curriculum
 * A student must pass all Modules in their study program to graduate
 * Moduls describe a field of study and reward the student with a given amount of credit points
 * There may be multiple courses applicable to one module
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Module {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private int creditPoints;
    private int period;


    @ManyToMany(mappedBy = "modules")
    private Set<Curriculum> curriculums;


    @ManyToMany
    private Set<Course> courses;

    @Deprecated
    public Module(
        String title,
        int creditPoints,
        int period) {

        this.title = title;
        this.creditPoints = creditPoints;
        this.period = period;
        this.courses = new HashSet<Course>();
    }

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    private Module(Builder builder) {
        this.title = builder.title;
        this.creditPoints = builder.creditPoints;
        this.courses = builder.courses;
        this.period = builder.period;
    }

    /**
     * Builder class 
     * defines the parameters of the Module object to be built
     */
    public static class Builder {
        private String title;
        private int creditPoints;
        private Set<Course> courses;
        private int period;
        private Set<Curriculum> curriculums;

        public Builder(String title) {
            this.title = title;
            this.courses = new HashSet<Course>();
            this.curriculums = new HashSet<Curriculum>();
        }

        public Builder hasCreditPoints(int creditPoints) {
            this.creditPoints = creditPoints;
            return this;
        }

        public Builder hasCourses(HashSet<Course> courses) {
            this.courses = courses;
            return this;
        }

        public Builder withCourse(Course course) {
            this.courses.add(course);
            return this;
        }

        public Builder inPeriod(int period) {
            this.period = period;
            return this;
        }

        public Builder addPeriod(int period) {
            this.period &= (1 << period);
            return this;
        }

        public Builder inCurriculums(Set<Curriculum> curriculums) {
            this.curriculums = curriculums;
            return this;
        }

        public Builder inCurriculum(Curriculum curriculum) {
            this.curriculums.add(curriculum);
            return this;
        }

        public Module build() {
            return new Module(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Set<Curriculum> getCurriculums() {
        return curriculums;
    }

    public void setCurriculums(Set<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }
    
    /**
     * Adds course to the collection of courses fitting this module 
     * @param course
     */
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    /**
     * Removes course from the collection of courses fitting this module
     * @param course
     */
    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    /**
     * Empties collection of all courses fitting this module
     */
    public void clearCourses() {
        this.courses.clear();
    }

    /**
     * Checks if a given course fits this module
     * @param course
     * @return true if course fits this module
     */
    public boolean containsCourse(Course course) {
        return this.courses.contains(course);
    }
    
    /**
     * Adds curriculum to the collection of curriculums applicant to this module
     * @param curriculum
     */
    public void addCurriuculum(Curriculum curriculum) {
        this.curriculums.add(curriculum);
    }

    /**
     * Removes curriculum from the collection of curriculums applicant to this module
     * @param curriculum
     */
    public void removeCurriuculum(Curriculum curriculum) {
        this.curriculums.remove(curriculum);
    }

    /**
     * Empties collection of all curriculums applicant to this module
     */
    public void clearCurriuculums() {
        this.curriculums.clear();
    }

    /**
     * Checks if a given curriculum applies to this module
     * @param curriculum
     * @return true if given curriculum applies to this module
     */
    public boolean containsCurriuculum(Curriculum curriculum) {
        return this.curriculums.contains(curriculum);
    }
}