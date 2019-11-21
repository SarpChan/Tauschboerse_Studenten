package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

@Entity
class Administrator extends User {
    private String username;
    private String password;

    public Administrator(Builder builder) {
        super(builder);
        this.username = builder.username;
        this.password = builder.password;
    }

    /**
     * Builder class
     * defines the parameters of the administrator object to be built
     */
    private static class Builder extends User.Builder {

        private String username;
        private String password;

        public Builder(String firstName, String lastName, String loginName, String password, boolean admin) {
            super(firstName, lastName, loginName, password, admin);

        }

        public Administrator build() {
            return new Administrator(this);
        }
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
