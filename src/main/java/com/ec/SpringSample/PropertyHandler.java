package com.ec.SpringSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * This is the handler class which handles property management based on environment.
 * First it reads environment information from application.properties then based on it,
 * tries to get relevant property value.
 */

@Configuration
public class PropertyHandler {

    @Autowired
    Environment env;

    /**
     * Define some constants used in application.properties.
     */
    private final String PRODUCTION_ENV = "PRODUCTION";
    private final String DEVELOPMENT_ENV = "DEVELOPMENT";

    private  String DEVELOPMENT_ENV_SUFFIX = null;
    private  String PRODUCTION_ENV_SUFFIX =  null;

    /**
     * Since env variable is not ready during {@link PropertyHandler} instantination, we need to init it after construction.
     */
    @PostConstruct
    public void init()
    {
        DEVELOPMENT_ENV_SUFFIX = env.getProperty("development.environment.suffix");
        PRODUCTION_ENV_SUFFIX =  env.getProperty("production.environment.suffix");
    }
    /**
     * Read relevant property value based on environment
     * If the environment is DEVELOPMENT then it tries to read property
     * with ".dev" suffix. For production it checks properties with ".prod"
     * suffix. Still no property has been found then it assumes it is a common
     * property for both so check without any suffix.
     * @param propertyName Property name that we are trying to get
     * @return Property value defined in application.properties , null if not found.
     */
    public String getProperty(String propertyName)
    {
        final String environment = env.getProperty("environment");

        String propertyValue = null;

        if(environment.equals(DEVELOPMENT_ENV))
        {
            String val =  env.getProperty(propertyName + DEVELOPMENT_ENV_SUFFIX);
            if(val != null)
            {
                propertyValue = val;
            }
        }else if(environment.equals(PRODUCTION_ENV))
        {
            String val =  env.getProperty(propertyName + PRODUCTION_ENV_SUFFIX);
            if(val != null)
            {
                propertyValue = val;
            }
        }

        //there is no dev or prod property set. so search with exact name
        if(propertyValue == null)
        {
            propertyValue = env.getProperty(propertyName);
        }

        return propertyValue;
    }
}
