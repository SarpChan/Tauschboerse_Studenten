package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Course offered at the University
 * Applicable to one or more modules of one or more study programs
 * Is made up of multipe Course Components
 * When passed, the student is rewarded with the specified amount of credit points
 */
@Entity
@Builder
@AllArgsConstructor
public class Course {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private User owner;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "course")
    private Set<CourseComponent> courseComponents;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(mappedBy = "courses")
    private Set<Module> modules;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(mappedBy = "courses")
    private Set<Term> terms;

    @Getter @Setter
    @OneToMany
    private Set<StudentAttendsCourse> studentsAttendCourse;

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

    @Deprecated
    public Course(String title) {
        this.title = title;
        this.modules = new HashSet<Module>();
    }

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private Course(Builder builder) {
        this.title = builder.title;
        this.owner = builder.owner;
        this.courseComponents = builder.courseComponents;
        this.modules = builder.modules;
        this.studentsAttendCourse = builder.studentsAttendCourse;
    }

    /**
     * Builder class 
     * defines the parameters of the Course object to be built
     */
    @Deprecated
    public static class Builder {
        private String title;
        private User owner;
        private Set<CourseComponent> courseComponents;
        private Set<Module> modules;
        private Set<StudentAttendsCourse> studentsAttendCourse;

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
}