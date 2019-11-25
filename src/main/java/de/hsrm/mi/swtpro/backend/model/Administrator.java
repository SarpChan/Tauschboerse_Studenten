package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
public class Administrator extends Role {

    @Getter @Setter
    private int rights;

    @Getter @Setter
    private String adminLogin;

    @Getter @Setter
    private String adminPassword;
}