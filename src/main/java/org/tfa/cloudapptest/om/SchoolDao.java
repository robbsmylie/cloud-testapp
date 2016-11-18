package org.tfa.cloudapptest.om;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		System.out.println("setting datasource for template");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public School getSchoolTestRow(String schoolId) {
				
		String query = "select * from school where schoolid="+schoolId;
		List<School> schools = this.jdbcTemplate.query(query, new SchoolMapper());

		return schools.get(0);
	}

	private static final class SchoolMapper implements RowMapper<School> {

	    public School mapRow(ResultSet rs, int rowNum) throws SQLException {
	        School school = new School();
	        school.put("schoolid",rs.getInt("schoolid"));
	        school.put("name",rs.getString("name"));
	        school.put("district",rs.getString("district"));
	        return school;
	    }
	}
}
