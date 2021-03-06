package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a room wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested Room not found :(")
public class RoomNotFoundException extends RuntimeException{
    public RoomNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
