package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

/**
 * Administrator is a role with administrative permissions
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@SuperBuilder
public class Administrator extends Role {

    @Getter @Setter
    private int priviledge;
}