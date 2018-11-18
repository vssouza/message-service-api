package com.example.message.common;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public class NullAwareBeanUtilsBean extends BeanUtilsBean {

    @Override
    public void copyProperty(Object dest, String name,  Object value) {
        try {
            this.getProperty(dest, name);
            super.copyProperty(dest, name, value);
        } catch(InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
            throw new UnsupportedOperationException(String.format("The property %s does not exists and cannot be updated.", name));
        }
    }
}