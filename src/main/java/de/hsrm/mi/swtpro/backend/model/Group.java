package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
/**
 * A group is part of a course and takes place at a given time and place
 * Each lesson is held by a lecturer in a room, and has a limited capacity of seats
 * There may be multipe lessons for each course, split into different groups
 * A student may only attend one group for each lesson of the same type
 */
public class Group {
    
    @Id
    @GeneratedValue
    private long id;
    private char group;
    private int slots;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne
    private Term term;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private CourseComponent courseComponent;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private User lecturer;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private Room room;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany
    private Set<Student> students;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char getGroup() {
        return group;
    }

    public void setGroup(char group) {
        this.group = group;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }    

    public CourseComponent getCourseComponent() {
        return courseComponent;
    }

    public void setCourseComponent(CourseComponent courseComponent) {
        this.courseComponent = courseComponent;
    }

    public User getLecturer() {
        return lecturer;
    }

    public void setLecturer(User lecturer) {
        this.lecturer = lecturer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

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