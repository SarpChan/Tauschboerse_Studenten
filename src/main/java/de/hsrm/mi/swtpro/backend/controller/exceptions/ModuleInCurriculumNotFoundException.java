package de.hsrm.mi.swtpro.backend.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a module in curriculum wasn't found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested ModuleInCurriculum not found :(")
public class ModuleInCurriculumNotFoundException extends RuntimeException {
    public ModuleInCurriculumNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
