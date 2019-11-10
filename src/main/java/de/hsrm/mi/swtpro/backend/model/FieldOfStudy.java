package de.hsrm.mi.swtpro.backend.model;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
class FieldOfStudy {
    @Id @GeneratedValue
    private int id;
    private String title;
    
    @ManyToMany
    private Collection<StudyProgram> studyPrograms;

    public FieldOfStudy(String title) {
        this.title = title;
        this.studyPrograms = new HashSet<StudyProgram>();
    }

    public int getId() {
        return id;
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