package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested Building not found :(")
public class BuildingNotFoundException extends RuntimeException{
    public BuildingNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
