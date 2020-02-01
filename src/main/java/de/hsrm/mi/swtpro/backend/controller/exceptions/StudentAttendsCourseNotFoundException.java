package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a student attends course  wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested StudentAttendsCourse not found :(")
public class StudentAttendsCourseNotFoundException extends RuntimeException {
    public StudentAttendsCourseNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
