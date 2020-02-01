package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a campus wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested Campus not found :(")
public class CampusNotFoundException extends RuntimeException{
    public CampusNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
