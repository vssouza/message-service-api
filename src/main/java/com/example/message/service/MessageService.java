package com.example.message.service;

import com.example.message.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    ResourceBundleMessageSource resourceBundleMessageSource;

    @Autowired
    public void setResourceBundleMessageSource(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

//    public Message sendMessage(long senderId, long receiverId, final String message, Locale locale) {
//        String messageLabel = resourceBundleMessageSource.getMessage("message.label", null, locale) ,message)
//
//    }

}
