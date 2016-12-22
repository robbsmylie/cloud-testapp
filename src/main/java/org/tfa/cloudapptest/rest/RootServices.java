package org.tfa.cloudapptest.rest;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.tfa.cloudapptest.om.School;
import org.tfa.cloudapptest.om.SchoolDao;

@RefreshScope
@RestController
public class RootServices {
	
	@Value("${message:Hello default message}")
	String configMessage = null;

	@RequestMapping("/")
    public String home() {
        System.out.println("/ called, returning Hello World");
        return "This is a simple app for testing bluemix features in cloud foundry and containers";
    }

	@RequestMapping("config-message")
    public String configMessage() {
        System.out.println("message service called");
        return "this is the config message: "+configMessage;
    }
}
