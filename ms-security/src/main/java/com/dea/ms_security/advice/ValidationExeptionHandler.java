package com.dea.ms_security.advice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dea.ms_security.error.PasswordNotMatchException;
import com.dea.ms_security.error.UserIsAlreadyExistsException;
import com.dea.ms_security.response.ValidationResponse;

@RestControllerAdvice
public class ValidationExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationResponse>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ValidationResponse> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new ValidationResponse(err.getField(), err.getDefaultMessage())).toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ValidationResponse> handlePasswordNotMatchException (PasswordNotMatchException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ValidationResponse("password", ex.getMessage()));
    }

    @ExceptionHandler(UserIsAlreadyExistsException.class)
    public ResponseEntity<ValidationResponse> handleUserIsAlreadyExistsException (UserIsAlreadyExistsException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ValidationResponse("username", ex.getMessage()));
    }
}
