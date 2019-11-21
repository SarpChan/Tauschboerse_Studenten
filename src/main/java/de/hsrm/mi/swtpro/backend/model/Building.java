package de.hsrm.mi.swtpro.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * Each campus has several buildings
 * A campus has multiple rooms and a name
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Building {
 
    @Id
    @GeneratedValue
    private long id;
    private String name;


    @ManyToOne(targetEntity = Campus.class)
    private Campus campus;


    @OneToMany(mappedBy = "building")
    private List<Room> rooms;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    private Building(Builder builder) {
        this.name = builder.name;
        this.campus = builder.campus;
        this.rooms = builder.rooms;
    }


    /**
     * Builder class
     * defines the parameters of the building object to be built
     */
    public static class Builder {
        private String name;
        private Campus campus;
        private List<Room> rooms = new ArrayList<Room>();

        public Builder(String name, Campus campus) {
            this.name = name;
            this.campus = campus;
        }

        public Builder hasRooms(List<Room> rooms) {
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    

}