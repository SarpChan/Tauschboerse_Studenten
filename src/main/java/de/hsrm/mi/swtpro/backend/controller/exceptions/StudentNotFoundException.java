package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a student wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested Student not found :(")
public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
