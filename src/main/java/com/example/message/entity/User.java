package com.example.message.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MESSAGEAPI_USERS")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue @SuppressWarnings("unused")
    @Getter @Setter
    private long id;
    @Setter @Getter
    @NotEmpty @NotNull
    private String username;
    @Setter @Getter
    @NotEmpty @Email
    private String email;

    /*
     * @ManyToMany(mappedBy = "receivers")
     * private List<Message> receivedMessages;
     */

    public User(final String username, final String email) {
        this.username = username;
        this.email = email;
    }
}
