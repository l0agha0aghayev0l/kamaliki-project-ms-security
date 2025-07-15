package com.dea.ms_security.error;

public class UserIsAlreadyExistsException extends RuntimeException{
    
    public UserIsAlreadyExistsException(String message) {
        super(message);
    }
}
