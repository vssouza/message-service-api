package com.example.message.exception;

import com.example.message.exception.BusinessRandomException;
import lombok.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class BusinessRandomExceptionHandler {

    @ExceptionHandler(BusinessRandomException.class)
    public final ResponseEntity<ErrorMessage> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorMessage errorMessage = ErrorMessage.of(
                status.value(),
                "Business Random Error",
                ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getServletPath()
        );
        return new ResponseEntity<>(errorMessage, headers, status);
    }

    @Value(staticConstructor = "of")
    private static class ErrorMessage {
        private Instant timestamp = Instant.now();
        private Integer status;
        private String error;
        private String message;
        private String path;
    }
}
