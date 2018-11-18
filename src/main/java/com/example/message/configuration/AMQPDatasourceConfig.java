package com.example.message.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AMQPDatasourceConfig {

    private static final String AMQP_SERVER = "amqp-server";
    private AMQPDatasource amqpDatasource;

    @Bean(name = "amqp-datasource")
    @Profile("development")
    public AMQPDatasource getDevelopmentAMQPDatasource() {
        String serverName = String.format("%s-%s", "dev", AMQP_SERVER);
        return new AMQPDatasource(serverName, 1213, "dev-partition", true);
    }

    @Bean(name = "amqp-datasource")
    @Profile("production")
    public AMQPDatasource getProductionAMQPDatasource() {
        return new AMQPDatasource(AMQP_SERVER, 1210, "prd-partition", false);
    }

    @Autowired
    @Qualifier("amqp-datasource")
    public void setAmqpDatasource(final AMQPDatasource amqpDatasource) {
        this.amqpDatasource = amqpDatasource;
    }
}
