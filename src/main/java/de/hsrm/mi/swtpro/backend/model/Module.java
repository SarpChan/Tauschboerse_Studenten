package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import javax.validation.constraints.*;

/**
 * Represents an element of the Curriculum
 * A student must pass all Modules in their study program to graduate
 * Moduls describe a field of study and reward the student with a given amount of credit points
 * There may be multiple courses applicable to one module
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class Module {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @NotEmpty(message = "Modulname fehlt")
    private String title;

    @Getter @Setter
    @Min(value = 0)
    private int creditPoints;

    @Getter @Setter
    @NotNull
    private int period;

    @Singular("moduleInCurriculum")
    @Getter @Setter
    @OneToMany(mappedBy = "module",cascade = CascadeType.ALL)
    private Set<ModuleInCurriculum> modulesInCurriculum ;

    @Singular("course")
    @Getter @Setter
    @ManyToMany(mappedBy = "modules",cascade= CascadeType.ALL)
    private Set<Course> courses ;

    /**
     * Adds course to the collection of courses fitting this module
     * @param course
     *
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
