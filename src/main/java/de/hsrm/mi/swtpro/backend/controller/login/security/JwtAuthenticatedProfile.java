package de.hsrm.mi.swtpro.backend.controller.login.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

/**
 * JwtAuthenticatedProfile contains the logged in 
 * user with the authorities
 */
public class JwtAuthenticatedProfile implements Authentication {

    private static final long serialVersionUID = 1L;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtAuthenticatedProfile(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return username;
    }
}