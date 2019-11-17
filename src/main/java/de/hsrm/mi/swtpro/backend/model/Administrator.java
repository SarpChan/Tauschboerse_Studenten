package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

@Entity
class Administrator extends User {
    private String adminName;
    private String adminPass;

    public Administrator(String firstName, String lastName, String adminName, String adminPass) {
        super(firstName, lastName);
        this.adminName = adminName;
        this.adminPass = adminPass;
    }

    public String getUsername() {
        return adminName;
    }

    public void setUsername(String username) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return adminPass;
    }

    public void setPassword(String adminPass) {
        this.adminPass = adminPass;
    }
}
