package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * A campus has a name and an adress
 * Every campus belongs to one university and has one or more buildings
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class Campus {
    
    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String address;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @Singular("building")
    @Getter @Setter
    @OneToMany(mappedBy = "campus")
    private Set<Building> buildings;

}