package com.example.message.service;

import com.example.message.entity.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageAPIService {

    public Message generateMessage(final int messageId, final String message) {
        final Message messageEntity = new Message();
        messageEntity.setMessageId(messageId);
        messageEntity.setMessage(String.format("The message received is: %s",message));
        return messageEntity;
    }

}
