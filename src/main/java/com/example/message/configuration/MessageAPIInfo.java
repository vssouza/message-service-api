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

    public static final String MONGO_INFO_PATH = "/mongo/info";
    public static final String RABBIT_INFO_PATH ="/rabbit/info";
    public static final String SERVICE_INFO_PATH = "/service/info";
    public static final String BUSINESS_EXCEPTION_PATH = "/business/exception";
    public static final String GENERAL_EXCEPTION_PATH = "/general/exception";




    public MessageAPIInfo() {
        paths = new ArrayList<>();
        paths.add(BASE_PATH);
        paths.add(BASE_PATH + MONGO_INFO_PATH);
        paths.add(BASE_PATH + RABBIT_INFO_PATH);
        paths.add(BASE_PATH + SERVICE_INFO_PATH);
        paths.add(BASE_PATH + BUSINESS_EXCEPTION_PATH);
        paths.add(BASE_PATH + GENERAL_EXCEPTION_PATH);
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
