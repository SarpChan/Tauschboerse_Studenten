package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested Term not found :(")
public class TermNotFoundException extends RuntimeException{
    public TermNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
