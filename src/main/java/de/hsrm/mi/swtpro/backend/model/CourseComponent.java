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

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Describes the type of lesson of a course
 * A course may be split into multiple types of lessons
 * For example: lecture, exercise and tutorial
 * The type of lesson must be unique for each course
 */
@Entity
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

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "courseComponent")
    private Set<Group> groups;

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

    @Deprecated
    public CourseComponent(
        Course course, 
        CourseType type, 
        int creditPoints, 
        String exam) {

        this.course = course;
        this.type = type;
        this.creditPoints = creditPoints;
        this.exam = exam;
        this.groups = new HashSet<Group>();
    }

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private CourseComponent(Builder builder) {
        this.course = builder.course;
        this.type = builder.type;
        this.creditPoints = builder.creditPoints;
        this.exam = builder.exam;
        this.groups = builder.groups;
    }

    /**
     * Builder class 
     * defines the parameters of the Group object to be built
     */
    @Deprecated
    public static class Builder {
        private int creditPoints;
        private String exam;
        private Set<Group> groups;

        private Course course;
        private CourseType type;
        public Builder(Course course, CourseType type) {
            this.course = course;
            this.type = type;
            this.groups = new HashSet<Group>();
        }

        public Builder hasCreditPoints(int creditPoints) {
            this.creditPoints = creditPoints;
            return this;
        }

        public Builder hasExam(String exam) {
            this.exam = exam;
            return this;
        }

        public Builder hasGroups(HashSet<Group> groups) {
            this.groups = groups;
            return this;
        }

        public Builder withGroup(Group group) {
            this.groups.add(group);
            return this;
        }

        public CourseComponent build() {
            return new CourseComponent(this);
        }
    }
}