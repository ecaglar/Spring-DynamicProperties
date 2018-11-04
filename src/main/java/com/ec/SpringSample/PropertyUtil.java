package com.ec.SpringSample;

public final class PropertyUtil {

    private static PropertyHandler propertyHandler;

    public static void setMyPropertyHandler(PropertyHandler handler)
    {
        PropertyUtil.propertyHandler = handler;
    }

    private PropertyUtil(){}

    public static String getProperty(String propertyName)
    {
        return propertyHandler.getProperty(propertyName);
    }
}
