package com.example.message.exception.handler;

import com.example.message.exception.common.BaseExceptionHandler;
import com.example.message.exception.common.ErrorMessage;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
@Order(2)
public class GlobalExceptionHandler extends BaseExceptionHandler {

    private static final String GLOBAL_EXCEPTION_MESSAGE = "exception.message.global";

    @ExceptionHandler({Throwable.class, Exception.class})
    public final ResponseEntity<ErrorMessage> handleException(final Exception ex, final WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMessage errorMessage = new ErrorMessage(
                status.value(),
                status.getReasonPhrase(),
                this.getLocalizedMessage(ex, GLOBAL_EXCEPTION_MESSAGE, request.getLocale()),
                ((ServletWebRequest) request).getRequest().getServletPath()
        );
        return new ResponseEntity<>(errorMessage, headers, status);
    }
}
