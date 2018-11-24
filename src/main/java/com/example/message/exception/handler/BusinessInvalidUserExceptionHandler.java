package com.example.message.exception.handler;

import com.example.message.exception.BusinessInvalidUserException;
import com.example.message.exception.common.BaseExceptionHandler;
import com.example.message.exception.common.ErrorMessage;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Order(1)
public class BusinessInvalidUserExceptionHandler extends BaseExceptionHandler {

    private static final String BUSINESS_INVALID_USER_EXCEPTION_MESSAGE = "exception.message.invalid_user";

    @ExceptionHandler(BusinessInvalidUserException.class)
    public final ResponseEntity<ErrorMessage> handleException(final Exception ex, final WebRequest request) {
        return ErrorMessage.getResonseEntity(request, HttpStatus.CONFLICT,
                this.getLocalizedMessage(ex, BUSINESS_INVALID_USER_EXCEPTION_MESSAGE, request.getLocale()));
    }
}
