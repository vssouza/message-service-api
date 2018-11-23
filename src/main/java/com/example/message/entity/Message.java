package com.example.message.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "messageapi_messages")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = 7369952970186355164L;

    @Id
    @GeneratedValue
    @SuppressWarnings("unused")
    @Getter
    private long id;
    @Setter @Getter
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String message;
    @Setter @Getter
    @NotNull
    @ManyToOne
    private User sender;
    @Getter
    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;
    @Setter @Getter
    @NotNull
    @ManyToOne
    private User receiver;
    /*
     * We are customizing our many to many to avoid hibernate to create multiple tables for the bidirectional relationship
     *
     * @ManyToMany
     * @JoinTable(name = "receiver_message", joinColumns = @JoinColumn(name="message_id"),
     *              inverseJoinColumns = @JoinColumn("user_id"))
     * private List<User> receivers;
     */

    public Message(final String message, final User sender, final User receiver) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
