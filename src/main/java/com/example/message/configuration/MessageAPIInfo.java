package com.example.message.configuration;

import com.example.message.entity.ServiceInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageAPIInfo {

    public static final String BASE_PATH = "/message-api";
    public static final String URL_ID_PATH = "/{id}";
    public static final String USER_BASE_PATH = BASE_PATH + "/users";
    public static final String MESSAGE_BASE_PATH = BASE_PATH + "/messages";
    public static final String SERVICE_INFO_BASE_PATH= BASE_PATH + "/info";
    public static final String RECEIVER_BASE_PATH = "/receivers/{id}";

    public MessageAPIInfo() {
        paths = new ArrayList<>();
        paths.add(BASE_PATH);
        paths.add(USER_BASE_PATH);
        paths.add(MESSAGE_BASE_PATH);
        paths.add(USER_BASE_PATH + URL_ID_PATH);
        paths.add(SERVICE_INFO_BASE_PATH);
        paths.add(MESSAGE_BASE_PATH + URL_ID_PATH);
        paths.add(MESSAGE_BASE_PATH + RECEIVER_BASE_PATH);

    }

    @Autowired
    @Getter
    @JsonProperty("service-info:")
    private ServiceInfo serviceInfo;

    @Getter
    private List<String> paths;

    @Getter
    @JsonProperty("amqp-datasource")
    private AMQPDatasource amqpDatasource;

    @Getter
    @JsonProperty("h2-datasource")
    private H2DataSourceConfiguration h2DataSourceConfiguration;

    @Autowired
    public void setAmqpDatasource(final AMQPDatasource amqpDatasource) {
        this.amqpDatasource = amqpDatasource;
    }

    @Autowired
    public void setH2DataSourceConfiguration(final H2DataSourceConfiguration h2DataSourceConfiguration){
        this.h2DataSourceConfiguration = h2DataSourceConfiguration;
    }

}
