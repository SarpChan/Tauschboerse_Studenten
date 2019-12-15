package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested Lecturer not found :(")
public class LecturerNotFoundException extends RuntimeException {
    public LecturerNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
