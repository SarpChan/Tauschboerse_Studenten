package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

@Entity
class Course {
    private int id;
    private int name;

    public Course(int id, int name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}