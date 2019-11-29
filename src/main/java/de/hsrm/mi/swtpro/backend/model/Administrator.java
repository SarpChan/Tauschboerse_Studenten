package de.hsrm.mi.swtpro.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

/**
 * Administrator is a role with administrative permissions
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Administrator extends Role {

    @Getter @Setter
    private int priviledge;
}