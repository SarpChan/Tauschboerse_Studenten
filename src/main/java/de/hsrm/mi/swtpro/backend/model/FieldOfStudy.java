package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.*;

/**
 * A field of study has a name and multiple studyPrograms
 * Many universities can have the same studyprogram
 */
@Entity
public class FieldOfStudy {
    @Id
    @GeneratedValue
    private int id;
    private String title;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany
    private Collection<StudyProgram> studyPrograms;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(targetEntity = University.class)
    private University university;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    private FieldOfStudy(Builder builder) {
        this.title = builder.title;
        this.studyPrograms = builder.studyPrograms;
        this.university = builder.university;
    }


    /**
     * Builder class 
     * defines the parameters of the field of study object to be built
     */
    public static class Builder {
        private String title;
        private Collection<StudyProgram> studyPrograms = new HashSet<>();
        private University university;


        public Builder(String title) {
            this.title = title;
            this.studyPrograms = new HashSet<StudyProgram>();
        }

        public Builder hasStudyPrograms(Collection<StudyProgram> studyPrograms) {
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


    public int getId() {
        return id;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<StudyProgram> getStudyProgram() {
        return studyPrograms;
    }

    public void setStudyProgram(Collection<StudyProgram> studyPrograms) {
        this.studyPrograms = studyPrograms;
    }
}