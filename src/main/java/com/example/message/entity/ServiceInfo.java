package com.example.message.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "service")
@Component
public class ServiceInfo {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String version;
}
