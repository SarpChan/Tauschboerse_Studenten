package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
public class Lecturer extends Role {

    @Getter @Setter
    private int priviledge;
}