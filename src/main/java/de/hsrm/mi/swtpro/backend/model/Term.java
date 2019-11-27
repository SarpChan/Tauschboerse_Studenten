package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

/**
 * Academic Term
 * the time interval in which an education facility holds classes
 * most common cycles are 2 terms per year, known as semester
 * the courses offered each term may vary
 */
@Entity
@AllArgsConstructor
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

    @Singular("course")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(mappedBy = "terms")
    private Set<Course> courses;

    @Singula("group")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "term")
    private Set<Group> groups;

    @Singular("studentAttendsCourse")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "term")
    private Set<StudentAttendsCourse> studentAttendsCourses;

    @Singular("enroledStudent")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "enrolementTerm")
    private Set<Student> enroledStudents;
    
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