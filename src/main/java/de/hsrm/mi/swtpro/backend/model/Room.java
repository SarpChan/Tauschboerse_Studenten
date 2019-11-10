package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

@Entity
class Room {
    private String campus;
    private String building;
    private int number;

    public Room(String campus, String building, int number) {
        this.campus = campus;
        this.building = building;
        this.number = number;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
}