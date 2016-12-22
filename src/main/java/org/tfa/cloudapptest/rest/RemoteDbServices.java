package org.tfa.cloudapptest.rest;

import java.util.LinkedHashMap;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.tfa.cloudapptest.db.RemoteDbDataSource;
import org.tfa.cloudapptest.om.School;
import org.tfa.cloudapptest.om.SchoolDao;

@RequestMapping("remotedb/")
@RefreshScope
@RestController
public class RemoteDbServices {

	private SchoolDao schoolDao = null;

	@Autowired
	@Qualifier("remoteDataSource")
	public void setRemoteDataSource(BasicDataSource remoteDataSource) {
		schoolDao = new SchoolDao(remoteDataSource);
	}
	
    @RequestMapping("school/{id}")
    public School getSchoolRowDb(@PathVariable(value="id") String schoolId) {
        System.out.println("/schoolrowdb called");
        School school = schoolDao.getSchoolTestRow(schoolId);
        return school;
    }
}
