package com.example.message.exception.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorMessage extends ErrorMessage {

    @JsonProperty("validation-errors")
    @Getter
    private List<ValidationErrorInfo> validationErrors;

    public ValidationErrorMessage(final Integer status, final String error, final String message, final String path) {
        super(status, error, message, path);
        validationErrors = new ArrayList<>();
    }
}
