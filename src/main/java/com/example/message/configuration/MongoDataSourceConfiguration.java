package com.example.message.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="datasource")
public class MongoDataSourceConfiguration {

    @Setter
    @Getter
    private String address;
    @Setter
    @Getter
    private int port;
    @Setter
    @Getter
    private String collection;

    @Override
    public String toString() {
        return String.format("http://%s:%d/%s", address, port, collection);
    }
}
