package com.example.message.exception.handler;

import com.example.message.exception.BusinessRandomException;
import com.example.message.exception.common.ErrorMessage;
import com.example.message.exception.common.BaseExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class BusinessRandomExceptionHandler extends BaseExceptionHandler {

    private static final String BUSINESS_RANDOM_EXCEPTION_MESSAGE = "exception.message.random";

    @ExceptionHandler(BusinessRandomException.class)
    public final ResponseEntity<ErrorMessage> handleException(final Exception ex, final WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorMessage errorMessage = ErrorMessage.of(
                status.value(),
                status.getReasonPhrase(),
                this.getLocalizedMessage(ex, BUSINESS_RANDOM_EXCEPTION_MESSAGE, request.getLocale()),
                ((ServletWebRequest) request).getRequest().getServletPath()
        );
        return new ResponseEntity<>(errorMessage, headers, status);
    }
}
