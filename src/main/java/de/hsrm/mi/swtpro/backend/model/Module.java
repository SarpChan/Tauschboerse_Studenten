package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

/**
 * Represents an element of the Curriculum
 * A student must pass all Modules in their study program to graduate
 * Moduls describe a field of study and reward the student with a given amount of credit points
 * There may be multiple courses applicable to one module
 */
@Entity
@AllArgsConstructor
@Builder
public class Module {
    
    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private int creditPoints;

    @Getter @Setter
    private int period;

    @Singular("moduleInCurriculum")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "module")
    private Set<ModuleInCurriculum> modulesInCurriculum;

    @Singular("course")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(mappedBy = "modules")
    private Set<Course> courses;
    
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

}