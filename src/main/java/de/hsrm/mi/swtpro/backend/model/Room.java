package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * A room has a number within its building and a number of seats
 */
@Entity
@Builder
public class Room {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private int number;

    @Getter @Setter
    private int seats;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(targetEntity = Building.class)
    private Building building;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private Room(Builder builder) {
        this.number = builder.number;
        this.seats = builder.seats;
        this.building = builder.building;
    }

    /**
     * Builder class 
     * defines the parameters of the room object to be built
     */
    @Deprecated
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
}