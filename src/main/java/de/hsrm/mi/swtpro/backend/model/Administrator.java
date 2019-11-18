package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

@Entity
class Administrator extends User {
    private String username;
    private String password;

    public Administrator(String firstName, String lastName, String username, String password) {
        super(firstName, lastName,username,password,true);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
