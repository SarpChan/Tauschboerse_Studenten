package de.hsrm.mi.swtpro.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Building {
 
    @Id
    @GeneratedValue
    private long id;
    public String name;
    @ManyToOne(targetEntity = Campus.class)
    private Campus campus;
    @OneToMany(mappedBy = "building")
    private List<Room> rooms = new ArrayList<Room>();


    public Building(String name, Campus campus) {
        this.name = name;
        this.campus = campus;
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
}
