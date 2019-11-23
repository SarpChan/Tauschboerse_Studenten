package de.hsrm.mi.swtpro.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Builder
public class Administrator extends Role {

    @Getter @Setter
    private int rights;

    @Getter @Setter
    private String adminLogin;

    @Getter @Setter
    private String adminPassword;
}