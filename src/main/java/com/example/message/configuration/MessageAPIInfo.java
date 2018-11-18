package com.example.message.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageAPIInfo {

    public static final String BASE_PATH = "/message-api";
    public static final String URL_ID_PATH = "/{id}";
    public static final String USER_BASE_PATH = BASE_PATH + "/users";
    public static final String MESSAGE_BASE_PATH = BASE_PATH + "/messages";




    public MessageAPIInfo() {
        paths = new ArrayList<>();
        paths.add(BASE_PATH);
        paths.add(USER_BASE_PATH);
        paths.add(MESSAGE_BASE_PATH);
        paths.add(USER_BASE_PATH + URL_ID_PATH);
        paths.add(MESSAGE_BASE_PATH + URL_ID_PATH);

    }

    @Getter
    @Value("${service.name}")
    private String name;
    @Getter
    @Value("${service.version}")
    private String version;

    @Getter
    private List<String> paths;

}
