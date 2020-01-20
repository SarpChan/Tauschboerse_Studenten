package de.hsrm.mi.swtpro.backend.controller.login;

import javax.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;

/**
 * The class validates the login 
 * and returns a token 
 */ 
 @Service
public class AuthenticationService {

    private JwtTokenService jwtTokenService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository; 

    public AuthenticationService(UserRepository userRepository, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }
    /**
     * The methode generate a new token if 
     * username and password is valid.
     * @param username
     * @param password
     * @return token
     */
    public JWTTokenResponse generateJWTToken(String username, String password) {
        return userRepository.findByLoginName(username)
                .filter(user ->  passwordEncoder.matches(password, user.getPassword()))
                .map(user -> new JWTTokenResponse(jwtTokenService.generateToken(username)))
                .orElseThrow(() ->  new EntityNotFoundException("User not found"));
    }
}