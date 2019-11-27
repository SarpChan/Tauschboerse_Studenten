package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

/**
 * A field of study has a name and multiple studyPrograms
 * Many universities can have the same studyprogram
 */
@Entity
@AllArgsConstructors
@Builder
public class FieldOfStudy {

    @Id
    @Getter @Setter
    @GeneratedValue
    private int id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(mappedBy = "fieldsOfStudy", targetEntity = University.class)
    private University university;

    @Singular("studyProgram")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(mappedBy = "fieldsOfStudy")
    private Set<StudyProgram> studyPrograms;

}