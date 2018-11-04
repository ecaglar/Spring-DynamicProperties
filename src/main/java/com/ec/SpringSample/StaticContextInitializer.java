package com.ec.SpringSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * This is where we set our property handler.
 */

@Component
public class StaticContextInitializer {

    @Autowired
    private PropertyHandler propertyHandler;


    @PostConstruct
    public void init()
    {
        PropertyUtil.setMyPropertyHandler(propertyHandler);
    }

}
