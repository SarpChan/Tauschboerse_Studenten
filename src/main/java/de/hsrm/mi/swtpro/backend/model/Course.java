package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

/**
 * Course offered at the University
 * Applicable to one or more modules of one or more study programs
 * Is made up of multipe Course Components
 * When passed, the student is rewarded with the specified amount of credit points
 */
@Entity
@AllArgsConstructor
@Builder
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

    @Singular("module")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(mappedBy = "courses")
    private Set<Module> modules;

    @Singular("term")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(mappedBy = "courses")
    private Set<Term> terms;

    @Singular("courseComponent")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "course")
    private Set<CourseComponent> courseComponents;

    @Singular("studentAttendsCourse")
    @Getter @Setter
    @OneToMany(mappedBy = "course")
    private Set<StudentAttendsCourse> studentAttendsCourses;

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

}