package de.hsrm.mi.swtpro.backend.controller.login.security;

import io.jsonwebtoken.JwtException;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.model.UserRights;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;

/**
 * The class interpret the token 
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final TokenService jwtService;

    @Autowired
    private UserRepository userRepository;

    public JwtAuthenticationProvider() {
        this(null);
    }

    @Autowired
    public JwtAuthenticationProvider(TokenService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String token = (String) authentication.getCredentials();
            String username = jwtService.getUsernameFromToken(token);
            Optional<User> optionalUser = userRepository.findByLoginName(username);
            if (!optionalUser.isPresent()) {
                throw new UsernameNotFoundException(username);
            }
            User user = optionalUser.get();
            UserRights userRights = user.getUserRights();
            String userRight;
            if(userRights != null){
                userRight = userRights.getUserRights();
            }else{
                userRight = "USER";
            } 
            return jwtService.validateToken(token)
                    .map(aBoolean -> new JwtAuthenticatedProfile(username,Arrays.asList(new SimpleGrantedAuthority(userRight))))
                    .orElseThrow(() -> new JwtAuthenticationException("JWT Token validation failed"));
        } catch (JwtException exception) {
            throw new JwtAuthenticationException("Failed to verify token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(
            authentication);
    }
}
