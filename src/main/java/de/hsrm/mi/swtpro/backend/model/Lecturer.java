package de.hsrm.mi.swtpro.backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@SuperBuilder
public class Lecturer extends Role {

    @Getter @Setter
    private int priviledge;

    @Singular("group")
    @Getter @Setter
    @OneToMany(mappedBy = "lecturer")
    private Set<Group> groups;

}