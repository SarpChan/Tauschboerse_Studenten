package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

import lombok.*;

/**
 * A university has a name and the adress of its headquarter
 * It has at least one campus and field of study
 */
@Entity
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
    private String adress;

    @Getter @Setter

    @OneToMany(mappedBy = "university")
    @Singular
    private Set<Campus> campuses;

    @Getter @Setter
    @Singular
    @OneToMany(mappedBy = "university")
    private Set<FieldOfStudy> fieldOfStudies;

}