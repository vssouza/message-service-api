package com.example.message.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageServiceAPIInfo {

    public static final String BASE_PATH = "/message";
    public static final String MONGO_INFO_PATH = "/mongo/info";
    public static final String RABBIT_INFO_PATH ="/rabbit/info";
    public static final String SERVICE_INFO_PATH = "/service/info";

    public MessageServiceAPIInfo() {
        paths = new ArrayList<>();
        paths.add(BASE_PATH);
        paths.add(MONGO_INFO_PATH);
        paths.add(RABBIT_INFO_PATH);
        paths.add(SERVICE_INFO_PATH);
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
