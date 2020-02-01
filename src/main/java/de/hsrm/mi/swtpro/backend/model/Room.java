package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import javax.validation.constraints.*;

import javax.persistence.*;

import java.util.Set;

/**
 * A room has a number within its building and a number of seats
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @NotNull
    private int number;

    @Getter @Setter
    @Min(value = 0)
    private int seats;

    @Getter @Setter
    @ManyToOne(targetEntity = Building.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "building_id")
    @NotNull
    private Building building;

    @Singular("group")
    @Getter @Setter
    @OneToMany(mappedBy = "room")
    private Set<Group> groups;

}
