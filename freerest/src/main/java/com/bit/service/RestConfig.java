package com.bit.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

/**
 * Here is configured the OAuth2 authentication workflow between the backend-auth and backend-client.
 * The protocol used in the current architecture is the authorization_code grant. For this protocol, we need
 * the authorization_code and refresh_token to be transmitted between the services.
 */

/**
 * The WebClient is used to request the backend-resources. The WebClient must be
 * configured to send to the backend-resources the OAuth2 authentication
 * information.
 */

@Configuration
public class RestConfig {

	
	@Autowired
	 private  Environment env;
	    private static final List<String> clients = Arrays.asList("google");
	    
	    
	private static final Logger logger = LoggerFactory.getLogger(RestConfig.class);
	/*
	 * @Bean public WebClient webClient() { return
	 * WebClient.create("https://api.restful-api.dev"); }
	 */

	@Bean
	public WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
				authorizedClientManager);
		return WebClient.builder().filter(logRequest()).apply(oauth2Client.oauth2Configuration()).build();
		//.baseUrl("https://api.restful-api.dev")
	}

	@Bean
	public OAuth2AuthorizedClientManager authorizedClientManager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository authorizedClientRepository) {
		OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
				.authorizationCode().refreshToken().build();
		DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
				clientRegistrationRepository, authorizedClientRepository);

		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
		return authorizedClientManager;
	}

	
	
	
	
	 @Bean
	    public ClientRegistrationRepository clientRegistrationRepository() {
		 
		 logger.info("text#########clientRegistrationRepository");
	        List<ClientRegistration> registrations = clients.stream()
	                .map(client -> this.getRegistration(client))
	                .filter(Objects::nonNull)
	                .collect(Collectors.toList());

	        return new InMemoryClientRegistrationRepository(registrations);
	    }

	    private ClientRegistration getRegistration(String client) {
	    	
	    	logger.info("text#########clientRegistrationRepository1");
	        String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
	        String clientId = env.getProperty(
	                CLIENT_PROPERTY_KEY + client + ".client-id");

	        if (clientId == null) {
	            return null;
	        }

	        String clientSecret = env.getProperty(
	                CLIENT_PROPERTY_KEY + client + ".client-secret");

	        String[] scopes = env.getProperty(
	                CLIENT_PROPERTY_KEY + client + ".scope").split(",");

	        String redirectUri = env.getProperty(
	                CLIENT_PROPERTY_KEY + client + ".redirect-uri");
	        if (client.equals("google")) {
	            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
	                    .clientId(clientId)
	                    .clientSecret(clientSecret)
	                    .redirectUri(redirectUri)
	                    .scope(scopes)
	                    .build();
	        }
	        return null;
	    }
	
	
	@Autowired
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> {
            	logger.info("text#########"+ name);
            	logger.info("text#########"+"{}={}", name, value);}));
            return Mono.just(clientRequest);
        });
    }

   
    
    
}
// https://api.restful-api.dev/objects
