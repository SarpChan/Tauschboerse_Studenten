package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
public abstract class Role {

    @Id
    @Getter @Setter
    @GeneratedValue
    private int id;

    @Getter @Setter
    @ManyToOne
    private User user;
}