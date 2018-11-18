package com.example.message.exception;

public class BusinessInvalidUserException extends RuntimeException {

    public BusinessInvalidUserException(String message) {
        super(message);
    }
}
