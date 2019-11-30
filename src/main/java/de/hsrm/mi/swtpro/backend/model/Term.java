package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Academic Term
 * the time interval in which an education facility holds classes
 * most common cycles are 2 terms per year, known as semester
 * the courses offered each term may vary
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @ManyToMany(mappedBy = "terms")
    private Set<Course> courses;

    @Singular("group")
    @Getter @Setter
    @OneToMany(mappedBy = "term")
    private Set<Group> groups;

    @Singular("studentAttendsCourse")
    @Getter @Setter
    @OneToMany(mappedBy = "term")
    private Set<StudentAttendsCourse> studentAttendsCourses;

    @Singular("enroledStudent")
    @Getter @Setter
    @OneToMany(mappedBy = "enrolmentTerm")
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