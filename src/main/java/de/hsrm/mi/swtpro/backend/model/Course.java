package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Course offered at the University
 * Applicable to one or more modules of one or more study programs
 * Is made up of multipe Course Components
 * When passed, the student is rewarded with the specified amount of credit points
 */
@Entity
public class Course {
    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    private String title;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private User owner;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "course")
    private Set<CourseComponent> courseComponents;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(mappedBy = "courses")
    private Set<Module> modules;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(mappedBy = "courses")
    private Set<Term> terms;

    @Deprecated
    public Course(String title) {
        this.title = title;
        this.modules = new HashSet<Module>();
    }

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    private Course(Builder builder) {
        this.title = builder.title;
        this.owner = builder.owner;
        this.courseComponents = builder.courseComponents;
        this.modules = builder.modules;
    }

    /**
     * Builder class 
     * defines the parameters of the Course object to be built
     */
    public static class Builder {
        private String title;
        private User owner;
        private Set<CourseComponent> courseComponents;
        private Set<Module> modules;

        public Builder(String title) {
            this.title = title;
            this.courseComponents = new HashSet<CourseComponent>();
            this.modules = new HashSet<Module>();
        }

        public Builder withOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public Builder hasCourseComponents(Set<CourseComponent> courseComponents) {
            this.courseComponents = courseComponents;
            return this;
        }

        public Builder withCourseComponent(CourseComponent courseComponent) {
            this.courseComponents.add(courseComponent);
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<CourseComponent> getCourseComponents() {
        return courseComponents;
    }

    public void setCourseComponents(Set<CourseComponent> courseComponents) {
        this.courseComponents = courseComponents;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    /**
     * Adds course component to the collection of course components, which belong to this course 
     * @param courseComponent
     */
    public void addCourseComponent(CourseComponent courseComponent) {
        this.courseComponents.add(courseComponent);
    }

    /**
     * Removes course component from the collection of course components, which belong to this course 
     * @param courseComponent
     */
    public void removeCourseComponent(CourseComponent courseComponent) {
        this.courseComponents.remove(courseComponent);
    }

    /**
     * Empties collection of course components, which belong to this course
     */
    public void clearCourseComponents() {
        this.courseComponents.clear();
    }

    /**
     * Checks if the collection of course components contains given component
     * @param courseComponent true if collection contains given component
     */
    public boolean containsCourseComponent(CourseComponent courseComponent) {
        return this.courseComponents.contains(courseComponent);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Term> getTerms() {
        return terms;
    }

    public void setTerms(Set<Term> terms) {
        this.terms = terms;
    }
}