package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a university wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested University not found :(")
public class UniversityNotFoundException extends RuntimeException{
    public UniversityNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
