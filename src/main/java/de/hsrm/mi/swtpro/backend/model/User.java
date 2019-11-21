package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A user has a first name and last name as well as a username and password
 * A user can be an admin
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String loginName;
    private String password;
    private boolean admin;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    protected User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.loginName = builder.loginName;
        this.password = builder.password;
        this.admin = builder.admin;
    }


    /**
     * Builder class 
     * defines the parameters of the user object to be built
     */
    public static class Builder {
        private String firstName;
        private String lastName;
        private String loginName;
        private String password;
        private boolean admin;

        public Builder(String firstName, String lastName, String loginName, String password, boolean admin) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.loginName = loginName;
            this.password = password;
            this.admin = admin;
        }

        public User build() {
            return new User(this);
        }
    } 


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
}