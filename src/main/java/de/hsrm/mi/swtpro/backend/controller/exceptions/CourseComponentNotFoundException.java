package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a course component wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested CourseComponent not found :(")
public class CourseComponentNotFoundException extends RuntimeException{
    public CourseComponentNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
