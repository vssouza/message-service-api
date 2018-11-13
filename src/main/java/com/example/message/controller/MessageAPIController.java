package com.example.message.controller;

import com.example.message.configuration.AMQPDatasource;
import com.example.message.configuration.MongoDataSourceConfiguration;
import com.example.message.configuration.MessageAPIInfo;
import com.example.message.entity.Message;
import com.example.message.service.MessageAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = MessageAPIInfo.BASE_PATH)
public class MessageAPIController {

    private MongoDataSourceConfiguration dataSourceConfig;
    private MessageAPIInfo serviceInfo;
    private AMQPDatasource amqpDatasource;
    private MessageAPIService messageAPIService;

    @Autowired
    public MessageAPIController(final MongoDataSourceConfiguration dataSourceConfig,
                                final MessageAPIInfo serviceInfo,
                                final MessageAPIService messageAPIService) {
        this.dataSourceConfig = dataSourceConfig;
        this.serviceInfo = serviceInfo;
        this.messageAPIService = messageAPIService;
    }

    @Autowired
    @Qualifier("amqp-datasource")
    public void setAmqpDatasource(final AMQPDatasource amqpDatasource) {
        this.amqpDatasource = amqpDatasource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Message sendMessage(@RequestParam(value = "messageid") int messageId,
                               @RequestParam(value = "message", defaultValue = "This is a default message") String message) {
        return messageAPIService.generateMessage(messageId, message);
    }

    @RequestMapping(path = MessageAPIInfo.MONGO_INFO_PATH, method = RequestMethod.GET)
    public MongoDataSourceConfiguration getMongoConfigInfo() {
        return dataSourceConfig;
    }

    @RequestMapping(path = MessageAPIInfo.RABBIT_INFO_PATH, method = RequestMethod.GET)
    public AMQPDatasource getRabbitConfigInfo() {
        return amqpDatasource;
    }

    @RequestMapping(path = MessageAPIInfo.SERVICE_INFO_PATH, method = RequestMethod.GET)
    public MessageAPIInfo getServiceInfo() {
        return serviceInfo;
    }
}
