package org.tfa.cloudapptest.rest;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tfa.cloudapptest.om.School;
import org.tfa.cloudapptest.om.SchoolDao;

@RestController
public class TestServices {

	@Autowired
	private SchoolDao schoolDao;
	
	@RequestMapping("/")
    public String home() {
        System.out.println("/ called, returning Hello World");
        return "This is a simple app for testing bluemix features in cloud foundry and containers";
    }

    @RequestMapping("testdb/school/{id}")
    public School getSchoolRowDb(@PathVariable(value="id") String schoolId) {
        System.out.println("/schoolrowdb called");
        School school = schoolDao.getSchoolTestRow(schoolId);
        return school;
    }

    @RequestMapping("testsvc/school/{id}")
    public School getSchoolRowsvc(@PathVariable(value="id") String schoolId) {
        System.out.println("/schoolrowsvc called");
        
        School school = new School();
        school.put("name", "this is a dummy school, not real data");
        return school;
    }

}
