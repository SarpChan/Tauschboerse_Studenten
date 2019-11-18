package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.*;

/* Fachbereich */

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


    public FieldOfStudy(String title) {
        this.title = title;
        this.studyPrograms = new HashSet<StudyProgram>();
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