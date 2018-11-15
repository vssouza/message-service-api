package com.example.message.service;

import com.example.message.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageAPIService {

    ResourceBundleMessageSource resourceBundleMessageSource;

    @Autowired
    public void setResourceBundleMessageSource(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    public Message generateMessage(final int messageId, final String message, Locale locale) {
        final Message messageEntity = new Message();
        messageEntity.setMessageId(messageId);
        messageEntity.setMessage(String.format("%s: %s",
                resourceBundleMessageSource.getMessage("message.label", null, locale) ,message));
        return messageEntity;
    }

}
