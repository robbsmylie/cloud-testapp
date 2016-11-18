package org.tfa.cloudapptest.db;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbDataSource {
	
	@Value("${DATABASE_URL}")
	private String databaseUrlProperty;

	@Value("${DATABASE_USER}")
	private String databaseUserProperty;

	@Value("${DATABASE_PASSWORD}")
	private String databasePasswordProperty;
	
	public void setDatabaseUrlProperty(String url) {
		databaseUrlProperty = url;
	}
	
	@Bean
	public BasicDataSource dataSource() throws URISyntaxException {
		
		System.out.println("database url: "+databaseUrlProperty);
		/*
		URI dbUri = new URI(databaseUrlProperty);

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		*/
		
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
