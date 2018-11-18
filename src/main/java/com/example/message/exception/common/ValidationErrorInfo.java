package com.example.message.exception.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ValidationErrorInfo {
    @Getter @Setter
    @JsonProperty("field-name")
    private String fieldName;
    @Getter @Setter
    @JsonProperty("error-message")
    private String errorMessage;
}
