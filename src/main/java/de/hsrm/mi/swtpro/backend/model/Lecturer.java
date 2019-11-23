package de.hsrm.mi.swtpro.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Builder
public class Lecturer extends Role {

    @Getter @Setter
    private int priviledge;
}