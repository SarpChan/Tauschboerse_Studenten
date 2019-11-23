package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import java.util.Set;
import java.util.HashSet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * A user has a first name and last name as well as a username and password
 * A user can be an admin
 */
@Entity
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

    @Getter @Setter 
    @OneToMany
    private Set<Role> roles;

    @Deprecated
    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.loginName = builder.loginName;
        this.password = builder.password;
        this.roles = builder.roles;
    }

    /**
     * Builder class 
     * defines the parameters of the user object to be built
     */
    @Deprecated
    public static class Builder {
        private String firstName;
        private String lastName;
        private String loginName;
        private String password;
        private Set<Role> roles;

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.roles = new HashSet<Role>();
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder withLoginName(String loginName) {
            this.loginName = loginName;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder hasRoles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }    
}