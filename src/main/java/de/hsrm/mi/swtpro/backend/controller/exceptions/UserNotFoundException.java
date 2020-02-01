package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a user wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested User not found :(")
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
