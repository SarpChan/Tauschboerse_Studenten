package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested StudentPriorizesGroup not found :(")
public class StudentPriorizesGroupNotFoundException extends RuntimeException {
    public StudentPriorizesGroupNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
