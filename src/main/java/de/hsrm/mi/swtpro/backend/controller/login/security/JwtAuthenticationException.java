package de.hsrm.mi.swtpro.backend.controller.login.security;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    
    private static final long serialVersionUID = -2220723358948660957L;

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
