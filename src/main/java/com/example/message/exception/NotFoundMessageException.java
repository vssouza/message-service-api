package com.example.message.exception;

public class NotFoundMessageException extends RuntimeException {
    public NotFoundMessageException(final String message) {
        super(message);
    }
}
