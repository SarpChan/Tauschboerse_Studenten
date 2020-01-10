package de.hsrm.mi.swtpro.backend.controller.login.config;

public enum Role {

    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String role;
    Role(String role) {
        this.role = role; }
    
    }