package com.example.message.controller;

import com.example.message.configuration.AMQPDatasource;
import com.example.message.configuration.MongoDataSourceConfiguration;
import com.example.message.configuration.MessageAPIInfo;
import com.example.message.exception.BusinessRandomException;
import com.example.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = MessageAPIInfo.BASE_PATH)
public class MessageController {

    private MongoDataSourceConfiguration dataSourceConfig;
    private MessageAPIInfo serviceInfo;
    private AMQPDatasource amqpDatasource;
    private MessageService messageAPIService;

    @Autowired
    public MessageController(final MongoDataSourceConfiguration dataSourceConfig,
                             final MessageAPIInfo serviceInfo,
                             final MessageService messageAPIService) {
        this.dataSourceConfig = dataSourceConfig;
        this.serviceInfo = serviceInfo;
        this.messageAPIService = messageAPIService;
    }

    @Autowired
    @Qualifier("amqp-datasource")
    public void setAmqpDatasource(final AMQPDatasource amqpDatasource) {
        this.amqpDatasource = amqpDatasource;
    }

    /*
     * To test the locale add the Accept-Language header to the API call (set it to pt for test)
     */
//    @RequestMapping(method = RequestMethod.GET)
//    public Message sendMessage(@RequestParam(value = "messageid") int messageId,
//                               @RequestParam(value = "message", defaultValue = "This is a default message") String message,
//                               Locale locale) {
//        return messageAPIService.generateMessage(messageId, message, locale);
//    }

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

    @RequestMapping(path = MessageAPIInfo.BUSINESS_EXCEPTION_PATH, method = RequestMethod.GET)
    public MessageAPIInfo getBusinessException() {
        throw new BusinessRandomException("This is a Business Random Exception test.");
    }

    @RequestMapping(path = MessageAPIInfo.GENERAL_EXCEPTION_PATH, method = RequestMethod.GET)
    public MessageAPIInfo getGeneralException() {
        throw new RuntimeException("This is a Non Expected Runtime Exception test.");
    }
}
