package com.example.message.entity;

import lombok.Getter;
import lombok.Setter;

public class Message {

    @Setter
    @Getter
    private int messageId;
    @Setter
    @Getter
    private String message;
}
