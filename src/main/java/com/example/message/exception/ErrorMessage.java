package com.example.message.exception;

import lombok.Value;

import java.time.Instant;

@Value(staticConstructor = "of")
public class ErrorMessage {

    private Instant timestamp = Instant.now();
    private Integer status;
    private String error;
    private String message;
    private String path;

}
