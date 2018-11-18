package com.example.message.exception.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public abstract class BaseExceptionHandler {

    ResourceBundleMessageSource resourceBundleMessageSource;

    @Autowired
    public void setResourceBundleMessageSource(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    protected String getLocalizedMessage(final Exception ex, final String label, final Locale locale) {
        String message =  resourceBundleMessageSource.getMessage(label, null, locale);
        return message == null ? ex.getMessage() : message;
    }
}
