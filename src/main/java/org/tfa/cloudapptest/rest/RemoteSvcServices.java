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

@RequestMapping("remotesvc/")
@RefreshScope
@RestController
public class RemoteSvcServices {

	@Value("${service.registry.school.data.get:localhost}")
	String schoolDataGetUrl = null;
	
    @RequestMapping("school/{id}")
    public School getSchoolRowsvc(@PathVariable(value="id") String schoolId) {
        System.out.println("/schoolrowsvc called with id="+schoolId);
        
        RestTemplate restTemplate = new RestTemplate();
        School school = restTemplate.getForObject(schoolDataGetUrl, School.class, schoolId);

        return school;
    }

}
