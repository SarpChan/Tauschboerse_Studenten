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
 * Each campus has several buildings
 * A campus has multiple rooms and a name
 */
@Entity
@Builder
@AllArgsConstructor
public class Building {

    @Id
    @Getter @Setter 
    @GeneratedValue
    private long id;

    @Getter @Setter 
    private String name;

    @Getter @Setter 
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(targetEntity = Campus.class)
    private Campus campus;

    @Getter @Setter 
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "building")
    private Set<Room> rooms;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private Building(Builder builder) {
        this.name = builder.name;
        this.campus = builder.campus;
        this.rooms = builder.rooms;
    }

    /**
     * Builder class
     * defines the parameters of the building object to be built
     */
    @Deprecated
    public static class Builder {
        private String name;
        private Campus campus;
        private Set<Room> rooms = new HashSet<Room>();

        public Builder(String name, Campus campus) {
            this.name = name;
            this.campus = campus;
        }

        public Builder hasRooms(Set<Room> rooms) {
            this.rooms = rooms;
            return this;
        }

        public Builder hasRoom(Room room) {
            this.rooms.add(room);
            return this;
        }

        public Building build() {
            return new Building(this);
        }
    }
}