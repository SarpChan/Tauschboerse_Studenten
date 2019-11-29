package de.hsrm.mi.swtpro.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class Role {

    @Id
    @Getter @Setter
    @GeneratedValue
    private int id;

    @Getter @Setter
    @ManyToOne
    private User user;
}