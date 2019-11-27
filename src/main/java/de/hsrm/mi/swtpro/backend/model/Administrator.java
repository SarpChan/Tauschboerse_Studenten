package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Administrator is a role with administrative permissions
 */
@Entity
@SuperBuilder
public class Administrator extends Role {

    @Getter @Setter
    private int priviledge;
}