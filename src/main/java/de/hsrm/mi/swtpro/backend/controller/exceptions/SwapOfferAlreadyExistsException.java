package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a swap offer already exists
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Swap Offer already exists")
public class SwapOfferAlreadyExistsException extends RuntimeException{
    public SwapOfferAlreadyExistsException(String errorMsg){
        super(errorMsg);
    }
}
