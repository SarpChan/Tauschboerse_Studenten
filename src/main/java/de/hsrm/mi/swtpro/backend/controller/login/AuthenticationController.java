package de.hsrm.mi.swtpro.backend.controller.login;

import de.hsrm.mi.swtpro.backend.model.AuthenticationResponse;
import de.hsrm.mi.swtpro.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;

/**
 * The authentication controller contains the POST request to login
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    UserRepository userRepository;

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * The POST gets the username and password in the body and authenticate with the
     * username and password. If username and password are valid, a JWT token is
     * created using the authenticationService and send the token to the client.
     *
     * @param authenticationRequest keeps the passwort and username from the request
     * @return
     */
    @PostMapping(path = "/login", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        Optional<User> optionalUser = userRepository.findByLoginName(authenticationRequest.getUsername());
        long userId = 0L;
        String userRight = "";
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userId = user.getId();
            if (user.getUserRights().toString().equals("ADMIN")) {
                userRight = user.getUserRights().toString();
            }
        }
        String token = authenticationService.generateJWTToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()).getToken();
        return AuthenticationResponse.builder().userId(userId).userRights(userRight).token(token).build();
    }

    /**
     * exception handling
     *
     * @param ex exception
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}