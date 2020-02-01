package de.hsrm.mi.swtpro.backend.controller.exceptions;

public class PyScriptStorageException extends RuntimeException {
    public PyScriptStorageException(String message) {
        super(message);
    }

    public PyScriptStorageException
    (String message, Throwable cause) {
        super(message, cause);
    }
}