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
 * A user has a first name and last name as well as a username and password
 * A user can be an admin
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @NotEmpty
    private String firstName;

    @Getter @Setter
    @NotEmpty
    private String lastName;

    @Getter @Setter
    @NotEmpty
    private String loginName;

    @Getter @Setter
    @NotEmpty
    private String password;

    @Singular("role")
    @Getter @Setter
    @OneToMany(mappedBy = "user")
    private Set<Role> roles;

}
