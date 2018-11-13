package com.example.message.controller;

import com.example.message.configuration.AMQPDatasource;
import com.example.message.configuration.MongoDataSourceConfiguration;
import com.example.message.configuration.MessageServiceAPIInfo;
import com.example.message.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageServiceAPIController {

    private MongoDataSourceConfiguration dataSourceConfig;
    private MessageServiceAPIInfo serviceInfo;
    private AMQPDatasource amqpDatasource;

    @Autowired
    public MessageServiceAPIController(final MongoDataSourceConfiguration dataSourceConfig, final MessageServiceAPIInfo serviceInfo) {
        this.dataSourceConfig = dataSourceConfig;
        this.serviceInfo = serviceInfo;
    }

    @Autowired
    @Qualifier("amqp-datasource")
    public void setAmqpDatasource(final AMQPDatasource amqpDatasource) {
        this.amqpDatasource = amqpDatasource;
    }

    @RequestMapping(path = MessageServiceAPIInfo.BASE_PATH, method = RequestMethod.GET)
    public Message sendMessage(@RequestParam(value = "messageid") int messageId,
                               @RequestParam(value = "message", defaultValue = "This is a default message") String message) {
        final Message messageEntity = new Message();
        messageEntity.setMessageId(messageId);
        messageEntity.setMessage(String.format("The message received is: %s",message));
        return messageEntity;
    }

    @RequestMapping(path = MessageServiceAPIInfo.BASE_PATH + MessageServiceAPIInfo.MONGO_INFO_PATH, method = RequestMethod.GET)
    public MongoDataSourceConfiguration getMongoConfigInfo() {
        return dataSourceConfig;
    }

    @RequestMapping(path = MessageServiceAPIInfo.BASE_PATH + MessageServiceAPIInfo.RABBIT_INFO_PATH, method = RequestMethod.GET)
    public AMQPDatasource getRabbitConfigInfo() {
        return amqpDatasource;
    }

    @RequestMapping(path = MessageServiceAPIInfo.BASE_PATH + MessageServiceAPIInfo.SERVICE_INFO_PATH, method = RequestMethod.GET)
    public MessageServiceAPIInfo getServiceInfo() {
        return serviceInfo;
    }
}
