package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a student passed exam wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested StudentPassedExam not found :(")
public class StudentPassedExamNotFoundException extends RuntimeException {
    public StudentPassedExamNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
