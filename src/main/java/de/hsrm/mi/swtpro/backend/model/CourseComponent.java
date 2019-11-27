package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
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
 * Describes the type of lesson of a course
 * A course may be split into multiple types of lessons
 * For example: lecture, exercise and tutorial
 * The type of lesson must be unique for each course
 */
@Entity
@AllArgsConstructor
@Builder
public class CourseComponent {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private CourseType type;

    @Getter @Setter
    private int creditPoints;

    @Getter @Setter
    private String exam;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private Course course;

    @Singular("group")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "courseComponent")
    private Set<Group> groups;

    @Getter @Setter
    @OneToOne(mappedBy = "courseComponent")
    private StudentPassedExam studentPassedExam;

    /**
     * Adds group to the collection of groups, which belong to this lesson 
     * @param group
     */
    public void addGroup(Group group) {
        this.groups.add(group);
    }

    /**
     * Removes group from the collection of groups, which belong to this lesson 
     * @param group
     */
    public void removeGroup(Group group) {
        this.groups.remove(group);
    }

    /**
     * Empties collection of all groups belonging to this lesson 
     */
    public void clearGroups() {
        this.groups.clear();
    }

    /**
     * Checks if a given group belongs to this lesson
     * @param group
     * @return true if given lesson belongs to this lesson
     */
    public boolean containsGroup(Group group) {
        return this.groups.contains(group);
    }

}