package com.example.message.controller;

import com.example.message.configuration.MessageAPIInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = MessageAPIInfo.SERVICE_INFO_BASE_PATH)
public class ServiceInfoController {

    private MessageAPIInfo messageAPIInfo;

    @Autowired
    public void setMessageAPIInfo(final MessageAPIInfo messageAPIInfo) {
        this.messageAPIInfo = messageAPIInfo;
    }

    @GetMapping
    public MessageAPIInfo getServiceInfo() {
        return messageAPIInfo;
    }

}
