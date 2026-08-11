package com.example.tutorial.exception;

public class ValidationException extends RuntimeException {

    public ValidationException() {
        super("Unprocessable");
    }

    public ValidationException(String message) {
        super(message);
    }
}