package com.dea.ms_security.error;

public class PasswordNotMatchException extends RuntimeException{
    
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
