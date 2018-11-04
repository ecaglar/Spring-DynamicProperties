# Spring-Dynamic-Properties 
 ### with Spring Boot 2.1.0 and Spring 5.1.2 

Handling property files in Spring applications might be cumbersome unless properly managed.
In addition, if th eproject requires different properties for development and production environment
it should be handled with minumum code in order to prevent further confusions. The reality is, developers
usually use local property values , such as local paths, endpoints etc. ,while developing and testing a module.

This can be handled using different approcahes. 

1- There might be different properties files for different needs and they can be manageed from a central application.properties file.

application.properties
     env = dev
    
application-dev.properties
application-prod.properties

2- Spring boot also supports **profile-spesific properties**. You can simply set a profile ([default] as default) and let spring boot load property file with that profile. Basicly add property files and set profile, then let application load related profile.


application-[profile].properties
-Dspring.profiles.active=local

then application-local.properties will be used.

## Another approach is to resolve property names dynamicly based on profile

In this sample project, I have covered the basics of dynamic property handling depending on the profile (environment in my case)
The purpose is to have only one configurtion file and different configuration values depending on profile.

```
#DEVELOPMENT |  PRODUCTION 
environment = DEVELOPMENT
```

This property makes it clear that we want to use properties related to development environment.
The way we distinguish the properties is,

For development environment properties we use = > ".dev" suffix
For Production environment properties we use = > ".prod" suffix

So, if we would like to define a path that we would like to save our files, we can define it either spesific to environment such that,

```
file.path.dev = /my.dev.path
file.path.prod = /my.prod.path
```

or common for both,

```
file.path = /my.common.path
```

We dont need to care about registering our application.properties file with  **@PropertySource** annotation as Spring Boot is automaticly take care of it. Whenever we like to read a property, we only need one line of code which is,

```
PropertyUtil.getProperty("file.path");
```

PropertyUtil is our custom class where we read properties in a more abstract manner. It takes care of the details such as deciding to read from file.path.dev or file.path.prod.


