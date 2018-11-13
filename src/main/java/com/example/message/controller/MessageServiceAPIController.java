package com.example.message.controller;

import com.example.message.configuration.AMQPDatasource;
import com.example.message.configuration.MongoDataSourceConfiguration;
import com.example.message.configuration.ServiceInfo;
import com.example.message.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageServiceAPIController {

    private MongoDataSourceConfiguration dataSourceConfig;
    private ServiceInfo serviceInfo;
    private AMQPDatasource amqpDatasource;
    private static final String AMQP_SERVER = "amqp-server";

    @Autowired
    public MessageServiceAPIController(final MongoDataSourceConfiguration dataSourceConfig, final ServiceInfo serviceInfo) {
        this.dataSourceConfig = dataSourceConfig;
        this.serviceInfo = serviceInfo;
    }

    @Autowired
    @Qualifier("amqp-datasource")
    public void setAmqpDatasource(final AMQPDatasource amqpDatasource) {
        this.amqpDatasource = amqpDatasource;
    }

    @RequestMapping(path = "/message", method = RequestMethod.GET)
    public Message sendMessage(@RequestParam(value = "messageid") int messageId,
                               @RequestParam(value = "message", defaultValue = "This is a default message") String message) {
        final Message messageEntity = new Message();
        messageEntity.setMessageId(messageId);
        messageEntity.setMessage(String.format("The message received is: %s",message));
        return messageEntity;
    }

    @RequestMapping(path = "/message/mongo/info", method = RequestMethod.GET)
    public MongoDataSourceConfiguration getMongoConfigInfo() {
        return dataSourceConfig;
    }

    @RequestMapping(path = "/message/rabbit/info", method = RequestMethod.GET)
    public AMQPDatasource getRabbitConfigInfo() {
        return amqpDatasource;
    }

    @RequestMapping(path = "/message/service/info", method = RequestMethod.GET)
    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

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
}
