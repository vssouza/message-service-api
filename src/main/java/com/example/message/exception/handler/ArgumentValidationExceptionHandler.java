package com.example.message.exception.handler;

import com.example.message.exception.common.BaseExceptionHandler;
import com.example.message.exception.common.ErrorMessage;
import com.example.message.exception.common.ValidationErrorInfo;
import com.example.message.exception.common.ValidationErrorMessage;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Order(1)
public class ArgumentValidationExceptionHandler extends BaseExceptionHandler {

    private static final String ARGUMENT_VALIDATION_EXCEPTION_MESSAGE = "exception.message.argument_validation";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleException(MethodArgumentNotValidException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationErrorMessage errorMessage = new ValidationErrorMessage(
                status.value(),
                status.getReasonPhrase(),
                this.getLocalizedMessage(ex, ARGUMENT_VALIDATION_EXCEPTION_MESSAGE, request.getLocale()),
                ((ServletWebRequest) request).getRequest().getServletPath()
        );
        populateValidationMessages(errorMessage, ex.getBindingResult());
        return new ResponseEntity<>(errorMessage, headers, status);
    }

    private void populateValidationMessages(ValidationErrorMessage message, BindingResult bindingResult) {
        bindingResult.getFieldErrors().forEach(error -> {
            message.getValidationErrors().add(new ValidationErrorInfo(error.getField(),  error.getDefaultMessage()));

        });

    }
}
