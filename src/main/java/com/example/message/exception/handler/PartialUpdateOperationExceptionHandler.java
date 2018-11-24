package com.example.message.exception.handler;

import com.example.message.exception.PartialUpdateOperationException;
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
public class PartialUpdateOperationExceptionHandler extends BaseExceptionHandler {

    private static final String PARTIAL_UPDATE_OPERATION_EXCEPTION_MESSAGE = "exception.message.partial_update";

    @ExceptionHandler(PartialUpdateOperationException.class)
    public final ResponseEntity<ErrorMessage> handleException(final Exception ex, final WebRequest request) {
        return ErrorMessage.getResonseEntity(request, HttpStatus.BAD_REQUEST,
                this.getLocalizedMessage(ex, PARTIAL_UPDATE_OPERATION_EXCEPTION_MESSAGE, request.getLocale()));
    }
}
