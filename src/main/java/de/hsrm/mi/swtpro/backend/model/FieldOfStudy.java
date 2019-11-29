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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldOfStudy {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @Singular("studyProgram")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(targetEntity = StudyProgram.class,mappedBy = "fieldsOfStudy")
    private Set<StudyProgram> studyPrograms;

}