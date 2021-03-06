package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a module wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested Module not found :(")
public class ModuleNotFoundException extends RuntimeException{
    public ModuleNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
