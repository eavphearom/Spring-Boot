package com.example.tutorial.exception;

public class DuplicateException extends RuntimeException {

    public DuplicateException() {
        super("Duplicate Data");
    }

    public DuplicateException(String message) {
        super(message);
    }
}