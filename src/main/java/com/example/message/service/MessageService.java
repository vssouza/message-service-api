package com.example.message.service;

import com.example.message.entity.Message;
import com.example.message.entity.User;
import com.example.message.exception.BusinessInvalidUserException;
import com.example.message.exception.MessageNotFoundException;
import com.example.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(final User sender, final User receiver, final Message message) {
        if(!message.getSender().getUsername().equals(sender.getUsername())
                || !message.getReceiver().getUsername().equals(receiver.getUsername())) {
            throw new BusinessInvalidUserException("The user data provided doesn't match with one of our users.");
        }
        return messageRepository.save(message);
    }

    public List<Message> listAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }

    public Message retrieveMessage(long id) {
        if(messageRepository.existsById(id)) {
            return messageRepository.findById(id).get();
        }
        throw new MessageNotFoundException("The specified message could not be found.");
    }

    public Message deleteMessage(long id) {
        Message message = retrieveMessage(id);
        messageRepository.delete(message);
        return message;
    }

}
