package com.example.message.exception.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RequiredArgsConstructor
public class ErrorMessage {

    @JsonProperty
    @Getter
    private final Instant timestamp = Instant.now();
    @JsonProperty
    @Getter
    private final Integer status;
    @JsonProperty
    @Getter
    private final String error;
    @JsonProperty
    @Getter
    private final String message;
    @JsonProperty
    @Getter
    private final String path;

    public static ResponseEntity<ErrorMessage> getResonseEntity(final WebRequest request,
                                                                final HttpStatus status, final String message) {
        HttpHeaders headers = new HttpHeaders();
        ErrorMessage errorMessage = new ErrorMessage(
                status.value(),
                status.getReasonPhrase(),
                message,
                ((ServletWebRequest) request).getRequest().getServletPath()
        );
        return new ResponseEntity<>(errorMessage, headers, status);
    }

}
