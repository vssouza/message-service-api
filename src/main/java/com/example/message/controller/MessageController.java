package com.example.message.controller;

import com.example.message.configuration.MessageAPIInfo;
import com.example.message.entity.Message;
import com.example.message.entity.User;
import com.example.message.service.MessageService;
import com.example.message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = MessageAPIInfo.MESSAGE_BASE_PATH)
public class MessageController {

    private MessageService messageService;
    private UserService userService;

    @Autowired
    public void setMessageService(final MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Message sendMessage(@RequestBody @NotNull final Message message) {
        User retrievedSender = userService.retrieveUser(message.getSender().getId());
        User retrievedReceiver = userService.retrieveUser(message.getReceiver().getId());
        return messageService.sendMessage(retrievedSender, retrievedReceiver, message);
    }

    @DeleteMapping(path = MessageAPIInfo.URL_ID_PATH)
    public Message deleteMessageById(@PathVariable(value = "id") final long id) {
        return messageService.deleteMessage(id);
    }

    @GetMapping
    public List<Message> listAllMessages() {
        return messageService.listAllMessages();
    }

    @GetMapping(path = MessageAPIInfo.URL_ID_PATH)
    public Message retrieveMessage(@PathVariable(value = "id") final long id) {
        return messageService.retrieveMessage(id);
    }
}
