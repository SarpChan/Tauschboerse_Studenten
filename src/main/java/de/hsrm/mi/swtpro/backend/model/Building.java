package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.Set;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;


/**
 * Each campus has several buildings
 * A campus has multiple rooms and a name
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
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
    @ManyToOne(targetEntity = Campus.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @Singular("room")
    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "building")
    private Set<Room> rooms;

}