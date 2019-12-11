package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested SwapOffer not found :(")
public class SwapOfferNotFoundException extends RuntimeException{
    public SwapOfferNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
