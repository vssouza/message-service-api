package com.example.message.exception;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(final String message) {
        super(message);
    }
}
