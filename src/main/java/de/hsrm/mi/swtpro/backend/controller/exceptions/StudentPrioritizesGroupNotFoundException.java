package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a student prioritizes group wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested StudentPriorizesGroup not found :(")
public class StudentPrioritizesGroupNotFoundException extends RuntimeException {
    public StudentPrioritizesGroupNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
