package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

/* Studiengang */
@Entity
class StudyProgram {
    private int id;
    private String title;
    private String degree;

    public StudyProgram(int id, String title) {
        this.id = id;
        this.title = title;
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
}