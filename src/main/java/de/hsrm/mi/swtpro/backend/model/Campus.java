package de.hsrm.mi.swtpro.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Campus {
 
    private String name;
    @Id
    private String adress;
    @ManyToOne(targetEntity = University.class)
    private University university;
    @OneToMany(mappedBy = "campus")
    private List<Building> buildings = new ArrayList<Building>();
    

    public Campus(String name, String adress, University university) {
        this.name = name;
        this.adress = adress;
        this.university = university;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
