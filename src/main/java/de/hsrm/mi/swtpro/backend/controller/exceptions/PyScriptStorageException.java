package de.hsrm.mi.swtpro.backend.controller.exceptions;

/**
 * Exception to catch errors when a script will be stored
 */
public class PyScriptStorageException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PyScriptStorageException(String message) {
        super(message);
    }

    public PyScriptStorageException
    (String message, Throwable cause) {
        super(message, cause);
    }
}