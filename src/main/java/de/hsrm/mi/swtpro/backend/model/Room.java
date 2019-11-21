package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.*;

/**
 * A room has a number within its building and a number of seats
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Room {

    @Id
    @GeneratedValue
    private long id;
    private int number;
    private int seats;


    @ManyToOne(targetEntity = Building.class)
    private Building building;


    /**
     * Constructor with Builder pattern
     * @param builder
     */
    private Room(Builder builder) {
        this.number = builder.number;
        this.seats = builder.seats;
        this.building = builder.building;
    }


    /**
     * Builder class 
     * defines the parameters of the room object to be built
     */
    public static class Builder {
        private int number;
        private int seats;
        private Building building;

        public Builder(int number, int seats, Building building) {
            this.number = number;
            this.seats = seats;
            this.building = building;
        }

        public Room build() {
            return new Room(this);
        }
    }    


    public Room(int number) {
 
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    
}