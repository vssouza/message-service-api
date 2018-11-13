package com.example.message.configuration;

import lombok.Getter;
import lombok.Setter;

public class AMQPDatasource {

    public AMQPDatasource(String server, int port, String partition, boolean isThrottlingEnabled) {
        this.server = server;
        this.port = port;
        this.partition = partition;
        if(isThrottlingEnabled) {
            this.throttling = 10;
        } else {
            throttling = -1;
        }
    }

    @Setter
    @Getter
    private String server;
    @Setter
    @Getter
    private int port;
    @Setter
    @Getter
    private String partition;
    @Setter
    @Getter
    private long throttling;
}
