package com.codelab.backend.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
