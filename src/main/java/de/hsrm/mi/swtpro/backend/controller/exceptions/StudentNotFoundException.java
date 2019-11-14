package de.hsrm.mi.swtpro.backend.controller.exceptions;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
