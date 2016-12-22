package org.tfa.cloudapptest.db;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalDbDataSource {
	
	@Value("${local.database.url:localhost}")
	private String databaseUrlProperty;

	@Value("${local.database.user:testuser}")
	private String databaseUserProperty;

	@Value("${local.database.password:testpass}")
	private String databasePasswordProperty;
	
	@Bean(value="localDataSource")
	public BasicDataSource localDataSource() throws URISyntaxException {
		
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
		else if(databaseUrlProperty.contains("sqlserver")) {
			basicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
			
		return basicDataSource;
	}
}
