package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private long id;

    @Getter @Setter
    @NotEmpty
    private String name;

    @Getter @Setter
    @NotEmpty
    private String address;

    @Singular("campus")
    @Getter @Setter
    @OneToMany(mappedBy = "university")
    private Set<Campus> campuses;

    @Singular("fieldOfStudy")
    @Getter @Setter
    @OneToMany(mappedBy = "university")
    private Set<FieldOfStudy> fieldsOfStudy;

}