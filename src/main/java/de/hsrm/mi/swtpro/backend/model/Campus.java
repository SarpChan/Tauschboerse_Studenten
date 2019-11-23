package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A campus has a name and an adress
 * Every campus belongs to one university and has one or more buildings
 */
@Entity
@Builder
@AllArgsConstructor
public class Campus {
    
    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String adress;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(targetEntity = University.class)
    private University university;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "campus")
    private Set<Building> buildings;
    
    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
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
    @Deprecated
    public static class Builder {
        private String name;
        private String adress;
        private University university;
        private Set<Building> buildings = new HashSet<Building>();

        public Builder(String name, String adress, University university) {
            this.name = name;
            this.adress = adress;
            this.university = university;
        }

        public Builder hasBuilding(Set<Building> buildings) {
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
}