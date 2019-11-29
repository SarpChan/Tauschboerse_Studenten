package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

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
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @ManyToOne
    private User owner;

    @Builder.Default
    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "course_module",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "module_id", referencedColumnName = "id"))
    private Set<Module> modules = new HashSet<>();

    @Builder.Default
    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "course_term",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "term_id", referencedColumnName = "id"))
    private Set<Term> terms = new HashSet<>();

    @Builder.Default
    @Getter @Setter
    @OneToMany(mappedBy = "course")
    private Set<CourseComponent> courseComponents = new HashSet<>();

    @Builder.Default
    @Getter @Setter
    @OneToMany(mappedBy = "course")
    private Set<StudentAttendsCourse> studentAttendsCourses = new HashSet<>();

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