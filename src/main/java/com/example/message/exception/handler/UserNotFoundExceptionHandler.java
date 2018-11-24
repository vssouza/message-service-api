package com.example.message.exception.handler;

import com.example.message.exception.UserNotFoundException;
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
public class UserNotFoundExceptionHandler extends BaseExceptionHandler {

    private static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "exception.message.user_not_found";

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleException(final Exception ex, final WebRequest request) {
        return ErrorMessage.getResonseEntity(request, HttpStatus.NOT_FOUND,
                this.getLocalizedMessage(ex, USER_NOT_FOUND_EXCEPTION_MESSAGE, request.getLocale()));
    }
}
