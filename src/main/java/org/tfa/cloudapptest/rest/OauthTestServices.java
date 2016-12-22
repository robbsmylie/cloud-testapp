package org.tfa.cloudapptest.rest;

import static java.lang.String.format;
import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/oauth")
@RestController
public class OauthTestServices {

	@Value("${oauth.token.url:localhost}")
	String oauthTokenUrl = null;

	@Value("${oauth.application.id:test}")
	String oauthAppId = null;

	@Value("${oauth.application.secret:test}")
	String oauthAppSecret = null;
	
	/**
	 * this endpoint is just to test making an OAuth call to get a remote resource
	 */
	@RequestMapping("/schooltest")
	public String schoolTest() {

		ClientCredentialsResourceDetails resourceDetails = getOauthResourceDetails();		
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);

		String value = restTemplate.getForObject("http://gateway-dev.tfanet.org/datatest/data/school/1", String.class);

		return value;
	}

	/**
	 * This is a more useful OAuth call to get school data from a remote school endpoint based on ID
	 */
	@RequestMapping("/school/{id}")
	public String getSchool() {

		ClientCredentialsResourceDetails resourceDetails = getOauthResourceDetails();		
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);

		String value = restTemplate.getForObject("http://gateway-dev.tfanet.org/datatest/data/school/1", String.class);

		return value;
	}

	private ClientCredentialsResourceDetails getOauthResourceDetails() {

		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri(oauthTokenUrl);
		resourceDetails.setClientId(oauthAppId);
		resourceDetails.setClientSecret(oauthAppSecret);
		resourceDetails.setGrantType("client_credentials");
		resourceDetails.setScope(asList("READ_APPLICATION"));

		return resourceDetails;
	}

}
