package org.tfa.cloudapptest.rest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.tfa.cloudapptest.om.School;
import org.tfa.cloudapptest.om.SchoolDao;

@RequestMapping("localdb/")
@RefreshScope
@RestController
public class LocalDbServices {

	static final Logger LOG = LoggerFactory.getLogger(LocalDbServices.class);
	
	private SchoolDao schoolDao;
	
	@Autowired
	@Qualifier("localDataSource")
	public void setLocalDataSource(BasicDataSource localDataSource) {
		schoolDao = new SchoolDao(localDataSource);
	}
	
    @RequestMapping("school/{id}")
    public School getSchoolRowDb(@PathVariable(value="id") String schoolId) {
        LOG.info("/schoolrowdb called");
        School school = schoolDao.getSchoolTestRow(schoolId);
        return school;
    }

    @RequestMapping("school/timingtest/{num}")
    public List timingTest(@PathVariable(value="num") int num) {
        LOG.info("/schoolrowdb called");
    	
        List schools = new ArrayList();
    	for(int i=1; i<=num; i++) {
            School school = getSchoolRowDb((new Integer(i)).toString());    		
    	}
    	
        return schools;
    }    
}
