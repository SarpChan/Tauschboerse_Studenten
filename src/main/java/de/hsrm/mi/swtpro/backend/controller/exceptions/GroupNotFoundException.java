package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested Group not found :(")
public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
