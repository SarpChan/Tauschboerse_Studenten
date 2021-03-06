package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a study program wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested StudyProgram not found :(")
public class StudyProgramNotFoundException extends RuntimeException{
    public StudyProgramNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
