package de.hsrm.mi.swtpro.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * A campus has a name and an adress
 * Every campus belongs to one university and has one or more buildings
 */
@Entity
public class Campus {
    @Id
    @GeneratedValue
    private long id;
    @NotEmpty(message = "Name fehlt")
    private String name;
    //@Id
    private String adress;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(targetEntity = University.class)
    private University university;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "campus")
    private List<Building> buildings;
    
    /**
     * Constructor with Builder pattern
     * @param builder
     */
    private Campus(Builder builder) {
        this.name = builder.name;
        this.adress = builder.adress;
        this.university = builder.university;
        this.buildings = builder.buildings;
    }

    /**
     * Builder class 
     * defines the parameters of the campus object to be built
     */
    public static class Builder {
        private String name;
        private String adress;
        private University university;
        private List<Building> buildings = new ArrayList<Building>();

        public Builder(String name, String adress, University university) {
            this.name = name;
            this.adress = adress;
            this.university = university;
        }

        public Builder hasBuilding(List<Building> buildings) {
            this.buildings = buildings;
            return this;
        }

        public Builder hasBuilding(Building building) {
            this.buildings.add(building);
            return this;
        }

        public Campus build() {
            return new Campus(this);
        }
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}