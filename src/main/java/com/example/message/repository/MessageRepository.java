package com.example.message.repository;

import com.example.message.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAllByReceiverId(long userId);
}
