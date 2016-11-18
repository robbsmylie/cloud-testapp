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
public class PersonDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		System.out.println("setting datasource for template");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Person getTestPersonData(String personid) {
				
		String query = "select * from person where personid="+personid;
		List<Person> persons = this.jdbcTemplate.query(query, new PersonMapper());

		return persons.get(0);
	}

	private static final class PersonMapper implements RowMapper<Person> {

	    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Person person = new Person();
	        person.put("personid",rs.getInt("personid"));
	        person.put("firstname",rs.getString("firstname"));
	        person.put("lastname",rs.getString("lastname"));
	        return person;
	    }
	}
}
