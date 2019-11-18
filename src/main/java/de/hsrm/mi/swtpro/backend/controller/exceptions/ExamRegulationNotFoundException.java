package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested ExamRegulation not found :(")
public class ExamRegulationNotFoundException extends RuntimeException{
    public ExamRegulationNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
