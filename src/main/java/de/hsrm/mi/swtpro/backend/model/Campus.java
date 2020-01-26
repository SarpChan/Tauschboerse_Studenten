package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotNull
    private long id;

    @Getter @Setter
    @NotEmpty(message = "name is empty")
    private String name;

    @Getter @Setter
    @NotEmpty(message = "address is empty")
    private String address;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "university_id")
    @NotNull
    private University university;

    @Singular("building")
    @Getter @Setter
    @OneToMany(mappedBy = "campus",cascade= CascadeType.ALL)
    private Set<Building> buildings ;

}