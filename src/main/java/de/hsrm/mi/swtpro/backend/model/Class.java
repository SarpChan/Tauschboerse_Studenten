package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Class {
    @Id@GeneratedValue
    private long id;
    private String type;
    private String group;
    private int seats;
}
