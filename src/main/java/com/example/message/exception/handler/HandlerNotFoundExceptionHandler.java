package com.example.message.exception.handler;

import com.example.message.exception.common.BaseExceptionHandler;
import com.example.message.exception.common.ErrorMessage;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Order(1)
public class HandlerNotFoundExceptionHandler extends BaseExceptionHandler {

    private static final String HANDLER_NOT_FOUND_EXCEPTION = "exception.message.handler_not_found";

    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<ErrorMessage> handleException(final Exception ex, final WebRequest request) {
        return ErrorMessage.getResonseEntity(request, HttpStatus.NOT_FOUND,
                this.getLocalizedMessage(ex, HANDLER_NOT_FOUND_EXCEPTION, request.getLocale()));
    }
}
