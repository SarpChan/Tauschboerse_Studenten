package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

/**
 * A group is part of a course and takes place at a given time and place
 * Each lesson is held by a lecturer in a room, and has a limited capacity of seats
 * There may be multipe lessons for each course, split into different groups
 * A student may only attend one group for each lesson of the same type
 */
@Entity
@AllArgsConstructor
@Builder
public class Group {    
    
    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private char group;

    @Getter @Setter
    private int slots;

    @Getter @Setter
    private DayOfWeek dayOfWeek;

    @Getter @Setter
    private LocalTime startTime;

    @Getter @Setter
    private LocalTime endTime;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(mappedBy = "groups")
    private Term term;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(mappedBy = "groups")
    private CourseComponent courseComponent;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(mappedBy = "groups")
    private Lecturer lecturer;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(mappedBy = "groups")
    private Room room;

    @Singular("student")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(mappedBy = "groups")
    private Set<Student> students;

    @Singular("prioritezeGroup")
    @Getter @Setter
    @OneToMany(mappedBy = "group")
    private Set<StudentPriorizesGroup> prioritizeGroups;

    @Singular("swapOffer")
    @Getter @Setter
    @OneToMany(mappedBy = "group")
    private Set<SwapOffer> swapOffers;

    @Singular("swapRequest")
    @Getter @Setter
    @OneToMany(mappedBy = "group")
    private Set<SwapOffer> swapRequests;

    /**
     * Adds student to the collection of students attending this group 
     * @param student
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Removes student from the collection of students attending this group 
     * @param student
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    /**
     * Empties collection of all students attending this group
     */
    public void clearStudents() {
        this.students.clear();
    }

    /**
     * Checks if a given student attends this group
     * @param student
     * @return true if student attends this group
     */
    public boolean containsStudent(Student student) {
        return this.students.contains(student);
    }

}