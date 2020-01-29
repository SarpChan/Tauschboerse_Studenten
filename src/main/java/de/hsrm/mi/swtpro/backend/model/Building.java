package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;


/**
 * Each campus has several buildings
 * A campus has multiple rooms and a name
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@Builder
public class Building {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @NotEmpty(message= "Name fehlt")
    private String name;

    @Getter @Setter
    @ManyToOne(targetEntity = Campus.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "campus_id")
    @NotNull
    private Campus campus;

    @Singular("room")
    @Getter @Setter
    @OneToMany(mappedBy = "building",cascade= CascadeType.ALL)
    private Set<Room> rooms;

}