package com.example.message.configuration;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class H2DataSourceConfig {

    @Setter
    @Getter
    @Value("${spring.datasource.name}")
    private String address;
    @Setter
    @Getter
    @Value("${spring.h2.console.enabled}")
    private boolean isConsoleEnabled;
    @Setter
    @Getter
    @Value("${spring.jpa.database-platform")
    private String jpaPlatform;

    @Override
    public String toString() {
        return address;
    }
}
