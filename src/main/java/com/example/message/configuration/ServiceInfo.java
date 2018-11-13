package com.example.message.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceInfo {
    @Getter
    @Value("${service.name}")
    private String name;
    @Getter
    @Value("${service.version}")
    private String version;
}
