package com.example.message.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "MESSAGEAPI_USERS")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
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
     * We are customizing our many to many to avoid hibernate to create multiple tables for the bidirectional relationship
     *
     * @ManyToMany(mappedBy = "receivers")
     * private List<Message> receivedMessages;
     */

    public User(final String username, final String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
