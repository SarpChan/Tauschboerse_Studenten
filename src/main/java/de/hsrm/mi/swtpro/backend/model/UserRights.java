package de.hsrm.mi.swtpro.backend.model;

/**
 * Defines different rights a user can have
 */
public enum UserRights {

    USER("USER"), ADMIN("ADMIN");

    private final String userRights;
    UserRights(String userRights) {
        this.userRights = userRights;
    }

    public String getUserRights(){
        return userRights;
    }
    
}
