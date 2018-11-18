package com.example.message.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGEAPI_USERS")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue
    @SuppressWarnings("unused")
    @Getter
    @Setter
    private long id;
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String email;

    public User(final String username, final String email) {
        this.username = username;
        this.email = email;
    }
}
