package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import java.util.Set;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

/**
 * A user has a first name and last name as well as a username and password
 * A user can be an admin
 */
@Entity
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Getter @Setter 
    @GeneratedValue
    private long id;

    @Getter @Setter 
    private String firstName;

    @Getter @Setter 
    private String lastName;

    @Getter @Setter 
    private String loginName;

    @Getter @Setter 
    private String password;

    @Singular("role")
    @Getter @Setter 
    @OneToMany(mappedBy = "user")
    private Set<Role> roles;
    
}