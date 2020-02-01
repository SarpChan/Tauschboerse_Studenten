package de.hsrm.mi.swtpro.backend.controller.login.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

/**
 * JwtAuthenticatedProfile contains the token from the 
 * user for the authentication
 */
public class JwtAuthentication implements Authentication {
  
    private static final long serialVersionUID = 1L;
    private final String token;

    /**
     * Constructor of JwtAuthentication
     * @param token for jwt authentication
     */
    public JwtAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
