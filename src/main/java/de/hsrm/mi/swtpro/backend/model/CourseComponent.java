package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Describes the type of lesson of a course
 * A course may be split into multiple types of lessons
 * For example: lecture, exercise and tutorial
 * The type of lesson must be unique for each course
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class CourseComponent {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @NotNull
    private CourseType type;

    @Getter @Setter
    @Min(value = 0)
    private int creditPoints;

    @Getter
    @Setter
    @NotEmpty
    private String exam;

    @Getter @Setter
    @ManyToOne(cascade= CascadeType.ALL)
    @NotNull
    private Course course;

    @Singular("group")
    @Getter @Setter
    @OneToMany(mappedBy = "courseComponent",cascade = CascadeType.ALL)
    private Set<Group> groups;

    @Singular("studentPassedExam")
    @Getter @Setter
    @OneToMany(mappedBy = "courseComponent",cascade= CascadeType.ALL)
    private Set<StudentPassedExam> studentsPassedExam;

    /**
     * Adds group to the collection of groups, which belong to this lesson
     *
     * @param group true if the set didn't already contain group
     */
    public void addGroup(Group group) {
        this.groups.add(group);
    }

    /**
     * Removes group from the collection of groups, which belong to this lesson
     *
     * @param group true if the set contained group
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
     *
     * @param group
     * @return true if given lesson belongs to this lesson
     */
    public boolean containsGroup(Group group) {
        return this.groups.contains(group);
    }

}
