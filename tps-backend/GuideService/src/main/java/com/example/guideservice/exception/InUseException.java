package com.example.guideservice.exception;

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}
