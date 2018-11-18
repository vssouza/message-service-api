package com.example.message.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MESSAGEAPI_MESSAGES")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {

    @Id
    @GeneratedValue
    @SuppressWarnings("unused")
    @Getter
    private long id;
    @Setter @Getter
    @NotEmpty
    private String message;
    @Setter @Getter
    @NotNull
    @ManyToOne
    private User sender;
    @Setter @Getter
    @NotNull
    @ManyToOne
    private User receiver;

    public Message(final String message, final User sender, final User receiver) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

}
