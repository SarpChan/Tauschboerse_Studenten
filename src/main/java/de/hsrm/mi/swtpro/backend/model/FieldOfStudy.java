package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotNull
    private long id;

    @Getter @Setter
    @NotEmpty
    private String title;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="university_id")
    @NotNull
    private University university;

    @Singular("studyProgram")
    @Getter @Setter
    @ManyToMany(cascade= CascadeType.ALL)
    private Set<StudyProgram> studyPrograms ;

}
