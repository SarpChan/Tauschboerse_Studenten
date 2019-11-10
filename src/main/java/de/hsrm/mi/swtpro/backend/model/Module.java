package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

/**
 * Module
 */

@Entity
class Module {
    private int id;
    private String name;

    public Module(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}