package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@SuperBuilder
public class Administrator extends Role {

    @Getter @Setter
    private int rights;

    @Getter @Setter
    private String adminLogin;

    @Getter @Setter
    private String adminPassword;
}