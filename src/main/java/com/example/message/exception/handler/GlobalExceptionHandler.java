package com.example.message.exception.handler;

import com.example.message.exception.common.BaseExceptionHandler;
import com.example.message.exception.common.ErrorMessage;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
@Order(2)
public class GlobalExceptionHandler extends BaseExceptionHandler {

    private static final String GLOBAL_EXCEPTION_MESSAGE = "exception.message.global";

    @ExceptionHandler({Throwable.class, Exception.class})
    public final ResponseEntity<ErrorMessage> handleException(final Exception ex, final WebRequest request) {
        return ErrorMessage.getResonseEntity(request, HttpStatus.INTERNAL_SERVER_ERROR,
                this.getLocalizedMessage(ex, GLOBAL_EXCEPTION_MESSAGE, request.getLocale()));
    }
}
