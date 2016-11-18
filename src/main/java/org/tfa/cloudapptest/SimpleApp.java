package org.tfa.cloudapptest;

//import javax.security.auth.message.config.AuthConfigFactory;
//import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class SimpleApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	
    	return application.sources(SimpleApp.class);
    }

    public static void main(String[] args) throws Exception {
    	
        SpringApplication.run(SimpleApp.class, args);
    }
 
}