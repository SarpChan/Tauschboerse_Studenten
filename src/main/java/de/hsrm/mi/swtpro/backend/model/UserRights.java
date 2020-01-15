package de.hsrm.mi.swtpro.backend.model;

public enum UserRights {

    ROLE_USER("USER"), ROLE_ADMIN("ADMIN");

    private final String userRights;
    UserRights(String userRights) {
        this.userRights = userRights; }
    
    }