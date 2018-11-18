package com.example.message.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ValidationService {

    @Autowired
    private SmartValidator validator;

    public void invokeManualValidation(final Object obj) throws MethodArgumentNotValidException {
        DataBinder binder = new DataBinder(obj);
        binder.setValidator(validator);
        binder.validate();
        if (binder.getBindingResult().getAllErrors().size() > 0) {
            throw new MethodArgumentNotValidException(null, binder.getBindingResult());
        }
    }
}
