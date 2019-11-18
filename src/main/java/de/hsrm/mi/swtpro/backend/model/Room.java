package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private long id;
    private int number;
    private int seats;
    @ManyToOne(targetEntity = Building.class)
    private Building building;


    public Room(int number, int seats, Building building) {
        this.number = number;
        this.seats = seats;
        this.building = building;
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
}
