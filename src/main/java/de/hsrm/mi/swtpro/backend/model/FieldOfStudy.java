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

/**
 * A field of study has a name and multiple studyPrograms
 * Many universities can have the same studyprogram
 */
@Entity
@AllArgsConstructor
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
    @ManyToMany
    private Set<StudyProgram> studyPrograms;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(targetEntity = University.class)
    private University university;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private FieldOfStudy(Builder builder) {
        this.title = builder.title;
        this.studyPrograms = builder.studyPrograms;
        this.university = builder.university;
    }

    /**
     * Builder class 
     * defines the parameters of the field of study object to be built
     */
    @Deprecated
    public static class Builder {
        private String title;
        private Set<StudyProgram> studyPrograms = new HashSet<>();
        private University university;


        public Builder(String title) {
            this.title = title;
            this.studyPrograms = new HashSet<StudyProgram>();
        }

        public Builder hasStudyPrograms(Set<StudyProgram> studyPrograms) {
            this.studyPrograms = studyPrograms;
            return this;
        }

        public Builder hasStudyProgram(StudyProgram studyProgram) {
            this.studyPrograms.add(studyProgram);
            return this;
        }

        public FieldOfStudy build() {
            return new FieldOfStudy(this);
        }
    }
}