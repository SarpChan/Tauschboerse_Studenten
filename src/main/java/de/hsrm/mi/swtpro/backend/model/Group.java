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

/**
 * A group is part of a course and takes place at a given time and place
 * Each lesson is held by a lecturer in a room, and has a limited capacity of seats
 * There may be multipe lessons for each course, split into different groups
 * A student may only attend one group for each lesson of the same type
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @OneToOne
    private Term term;

    @Getter @Setter

    @ManyToOne
    private CourseComponent courseComponent;

    @Getter @Setter

    @ManyToOne
    private User lecturer;

    @Getter @Setter

    @ManyToOne
    private Room room;

    @Getter @Setter

    @ManyToMany
    private Set<Student> students;

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

    @Deprecated
    public Group(char group, CourseComponent courseComponent) {
        this.group = group;
        this.courseComponent = courseComponent;
        this.students = new HashSet<Student>();
    }

    @Deprecated
    public Group(char group, int slots, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime,
            CourseComponent courseComponent, User lecturer, Room room) {
        this.group = group;
        this.slots = slots;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseComponent = courseComponent;
        this.lecturer = lecturer;
        this.room = room;
        this.students = new HashSet<Student>();
    }
    
    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private Group(Builder builder) {
        this.group = builder.group;
        this.slots = builder.slots;
        this.dayOfWeek = builder.dayOfWeek;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.courseComponent = builder.courseComponent;
        this.lecturer = builder.lecturer;
        this.room = builder.room;
        this.students = builder.students;
        this.term = builder.term;
    }

    /**
     * Builder class 
     * defines the parameters of the Group object to be built
     */
    @Deprecated
    public static class Builder {
        private int slots;
        private DayOfWeek dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;
        private User lecturer;
        private Room room;    
        private Term term;
        private Set<Student> students;

        private CourseComponent courseComponent;
        private char group;
        public Group build(CourseComponent courseComponent, char group) {
            this.courseComponent = courseComponent;
            this.group = group;
            this.students = new HashSet<Student>();
            return new Group(this);
        }

        public Builder hasSlots(int slots) {
            this.slots = slots;
            return this;
        }

        public Builder withStartTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder onDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public Builder hasLecturer(User lecturer) {
            this.lecturer = lecturer;
            return this;
        }

        public Builder hasStudents(Set<Student> students) {
            this.students = students;
            return this;
        }

        public Builder withStudent(Student student) {
            this.students.add(student);
            return this;
        }

        public Builder inRoom(Room room) {
            this.room = room;
            return this;
        }

        public Builder inTerm(Term term) {
            this.term = term;
            return this;
        }
    }
}