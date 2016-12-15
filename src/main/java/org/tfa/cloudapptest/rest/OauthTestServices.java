package org.tfa.cloudapptest.rest;

import static java.lang.String.format;
import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthTestServices {

	@Autowired
	OAuth2RestTemplate restTemplate = null;
	
	@RequestMapping("/oauthrequest")
	public String home() {

		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri("https://oauth-dev.tfanet.org/oauth/oauth20/token");
		resourceDetails.setClientId("enterpriseapi-7IID8zPakTx7F7orZPHjf42Z");
		resourceDetails.setClientSecret("3de7d7029cc69004095f199c266cb3c6da0ef69a");
		resourceDetails.setGrantType("client_credentials");
		resourceDetails.setScope(asList("READ_APPLICATION"));

		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
		restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));

		String value = restTemplate.getForObject("http://gateway-dev.tfanet.org/datatest/data/school/1", String.class);

		return value;
	}

	@RequestMapping("/oauthmdh")
	public String oauthMdh() {

		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri("https://oauth-qa.tfanet.org/oauth/oauth20/token");
		resourceDetails.setClientId("enterpriseapi-5r33UMTmAKP5pYv5g5Vyy65y");
		resourceDetails.setClientSecret("845e83a6e7444627694e8446f7edc5a0ed857b3f");
		resourceDetails.setGrantType("client_credentials");
		resourceDetails.setScope(asList("READ_APPLICATION"));

		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
		restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));

		String value = restTemplate.getForObject("https://gateway-qa.tfanet.org/services/reporting/report/html?cuId=AWqXBCRUsTRPgyZ86vF_7FY", String.class);

		return value;
	}
}
