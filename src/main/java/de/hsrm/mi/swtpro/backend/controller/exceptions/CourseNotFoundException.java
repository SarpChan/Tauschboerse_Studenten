package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a course wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested Course not found :(")
public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
