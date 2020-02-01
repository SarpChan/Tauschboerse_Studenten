package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Lecturer is a Role with special privileges
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@SuperBuilder
public class Lecturer extends Role {

    @Getter @Setter
    private int priviledge;

    @Singular("group")
    @Getter @Setter
    @OneToMany(mappedBy = "lecturer",cascade = CascadeType.ALL)
    private Set<Group> groups ;

}