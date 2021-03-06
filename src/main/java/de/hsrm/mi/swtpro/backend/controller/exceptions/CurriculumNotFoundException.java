package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a curriculum wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested Curriculum not found :(")
public class CurriculumNotFoundException extends RuntimeException{
    public CurriculumNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
