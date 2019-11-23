package de.hsrm.mi.swtpro.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class Role {

    @Id
    @Getter @Setter
    @GeneratedValue
    private int id;

    @Getter @Setter
    @ManyToOne
    private User user;
}