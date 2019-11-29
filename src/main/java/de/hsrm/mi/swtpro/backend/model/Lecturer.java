package de.hsrm.mi.swtpro.backend.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Lecturer extends Role {

    @Getter @Setter
    private int priviledge;

    @Singular("group")
    @Getter @Setter
    @OneToMany(mappedBy = "lecturer")
    private Set<Group> groups;

}