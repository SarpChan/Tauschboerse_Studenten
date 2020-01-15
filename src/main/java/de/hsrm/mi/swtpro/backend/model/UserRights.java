package de.hsrm.mi.swtpro.backend.model;

public enum UserRights {

    USER("USER"), ADMIN("ADMIN");

    private final String userRights;
    UserRights(String userRights) {
        this.userRights = userRights; }
    
    }