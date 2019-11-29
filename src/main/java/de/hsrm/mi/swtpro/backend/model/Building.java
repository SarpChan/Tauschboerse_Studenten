package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


/**
 * Each campus has several buildings
 * A campus has multiple rooms and a name
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Building {

    @Id
    @Getter @Setter 
    @GeneratedValue
    private long id;

    @Getter @Setter 
    private String name;

    @Getter @Setter 
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @Singular("room")
    @Getter @Setter 
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "building")
    private Set<Room> rooms;

}