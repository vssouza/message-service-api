package com.example.message.exception.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@RequiredArgsConstructor
public class ErrorMessage {

    @JsonProperty
    @Getter
    private final Instant timestamp = Instant.now();
    @JsonProperty
    @Getter
    private final Integer status;
    @JsonProperty
    @Getter
    private final String error;
    @JsonProperty
    @Getter
    private final String message;
    @JsonProperty
    @Getter
    private final String path;

}
