package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested PO not found :(")
public class PONotFoundException extends RuntimeException{
    public PONotFoundException(String errorMsg){
        super(errorMsg);
    }
}
