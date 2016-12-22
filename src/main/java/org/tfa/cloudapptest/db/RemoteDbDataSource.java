package org.tfa.cloudapptest.db;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RemoteDbDataSource {
	
	@Value("${remote.database.url:localhost}")
	private String databaseUrlProperty;

	@Value("${remote.database.user:testuser}")
	private String databaseUserProperty;

	@Value("${remote.database.password:testpass}")
	private String databasePasswordProperty;
	
	@Primary
	@Bean(value="remoteDataSource")
	public BasicDataSource remoteDataSource() throws URISyntaxException {
		
		System.out.println("database url: "+databaseUrlProperty);
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(databaseUrlProperty);
		basicDataSource.setUsername(databaseUserProperty);
		basicDataSource.setPassword(databasePasswordProperty);
		
		if(databaseUrlProperty.contains("mysql")) {
			basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		}
		else if(databaseUrlProperty.contains("oracle")) {
			basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		}
			
		return basicDataSource;
	}
}
