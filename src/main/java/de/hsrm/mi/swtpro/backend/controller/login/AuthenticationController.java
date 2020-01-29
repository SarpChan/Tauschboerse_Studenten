package de.hsrm.mi.swtpro.backend.controller.login;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The authentication controller contains 
 * the POST request to login
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * The POST gets the username and password in the body and authenticate with
     * the username and password. If username and password are valid, a JWT token is created using 
     * the authenticationService and send the token to the client.
     * @param authenticationRequest keeps the passwort and username from the request
     * @return
     */
    @PostMapping(path = "/login", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)  {
        return authenticationService.generateJWTToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()).getToken();
    }

    /**
     * exception handling
     * @param ex exception 
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }




}