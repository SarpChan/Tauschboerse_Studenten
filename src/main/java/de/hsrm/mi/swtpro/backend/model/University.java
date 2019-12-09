package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A university has a name and the adress of its headquarter
 * It has at least one campus and field of study
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class University {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String address;

    @Singular("campus")
    @Getter @Setter
    @OneToMany(mappedBy = "university",cascade= CascadeType.ALL)
    private Set<Campus> campuses ;

    @Singular("fieldOfStudy")
    @Getter @Setter
    @OneToMany(mappedBy = "university",cascade= CascadeType.ALL)
    private Set<FieldOfStudy> fieldsOfStudy ;

}