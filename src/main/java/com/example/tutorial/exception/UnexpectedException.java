package com.example.tutorial.exception;

public class UnexpectedException extends RuntimeException {

    public UnexpectedException() {
        super("Internal Server Error");
    }

    public UnexpectedException(String message) {
        super(message);
    }
}