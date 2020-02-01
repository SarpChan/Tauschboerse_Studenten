package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a field of study wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested FieldOfStudy not found :(")
public class FieldOfStudyNotFoundException extends RuntimeException{
    public FieldOfStudyNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
