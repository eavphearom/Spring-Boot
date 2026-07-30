package com.example.tutorial.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice /*Handle exceptions globally for all controllers.*/
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)/*When a validation error happens, run this method.*/
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors= new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<Map<String,String>> handleResourceNotFound(
            ResourceNotFoundException exception
    ){
        Map<String,String> response = new HashMap<>();
        response.put("message",exception.getMessage());
        return ResponseEntity.status(400).body(response);
    }
}
