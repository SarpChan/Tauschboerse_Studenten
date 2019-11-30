package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import javax.validation.constraints.Min;

import javax.persistence.*;
import java.util.Set;

/**
 * A room has a number within its building and a number of seats
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private int number;

    @Getter @Setter
    @Min(value = 0)
    private int seats;

    @Getter @Setter

    @ManyToOne(targetEntity = Building.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @Singular("group")
    @Getter @Setter
    @OneToMany(mappedBy = "room")
    private Set<Group> groups;

}