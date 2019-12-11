package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * A field of study has a name and multiple studyPrograms
 * Many universities can have the same studyprogram
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class FieldOfStudy {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="university_id")
    private University university;

    @Singular("studyProgram")
    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "fieldOfStudy_studyProgram",
            joinColumns = @JoinColumn(name = "fieldOfStudy_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "studyProgram_id", referencedColumnName = "id"))
    private Set<StudyProgram> studyPrograms;

}