package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

/**
 * A group is part of a course and takes place at a given time and place
 * Each lesson is held by a lecturer in a room, and has a limited capacity of seats
 * There may be multipe lessons for each course, split into different groups
 * A student may only attend one group for each lesson of the same type
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
@Table(name =  "group_table")
public class Group {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @NotNull
    private char groupChar;

    @Getter @Setter
    @NotNull
    private int slots;

    @Getter @Setter
    @NotNull
    private DayOfWeek dayOfWeek;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    @Getter @Setter
    @NotNull
    private LocalTime startTime;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    @Getter @Setter
    @NotNull
    private LocalTime endTime;

    @Getter @Setter
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="term_id")
    @NotNull
    private Term term;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="course_component_id")
    @NotNull
    private CourseComponent courseComponent;

    @Getter @Setter
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="lecturer_id")
    @NotNull
    private Lecturer lecturer;

    @Getter @Setter
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="room_id")
    @NotNull
    private Room room;

    @Singular("student")
    @Getter @Setter
    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name = "group_student",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private Set<Student> students;

    @Singular("prioritezeGroup")
    @Getter @Setter
    @OneToMany(mappedBy = "group",cascade= CascadeType.ALL)
    private Set<StudentPrioritizesGroup> prioritizeGroups;

    @Singular("swapOffer")
    @Getter @Setter
    @OneToMany(mappedBy = "fromGroup", cascade = CascadeType.ALL)
    private Set<SwapOffer> swapOffers;

    @Singular("swapRequest")
    @Getter @Setter
    @OneToMany(mappedBy = "toGroup", cascade = CascadeType.ALL)
    private Set<SwapOffer> swapRequests;

    /**
     * Adds student to the collection of students attending this group
     * @param student true if the set didn't already contain the student
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Removes student from the collection of students attending this group
     * @param student true if the set contained the student
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
     * @param student the student to search in the set
     * @return true if student attends this group
     */
    public boolean containsStudent(Student student) {
        return this.students.contains(student);
    }

}
