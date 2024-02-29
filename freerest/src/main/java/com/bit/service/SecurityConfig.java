package com.bit.service;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	
	
	  @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	                .authorizeHttpRequests(auth -> {
	                    auth.requestMatchers("/").permitAll();
	                    auth.requestMatchers("/favicon.ico").permitAll();
	                    auth.anyRequest().authenticated();
	                })
	                .oauth2Login(withDefaults())
	                .formLogin(withDefaults())
	                .build();
	    }
	  
	  
	  
	  
		/*
		 * .oauth2Login(oauth2Login -> oauth2Login.loginPage(
		 * OAuth2AuthorizationRequestRedirectFilter.
		 * DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/messages-client-oidc" ))
		 */
		/*
		 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
		 * throws Exception { http .authorizeRequests(authorizeRequests ->
		 * authorizeRequests.anyRequest().authenticated()) .oauth2Login(oauth2Login ->
		 * oauth2Login.loginPage( OAuth2AuthorizationRequestRedirectFilter.
		 * DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/messages-client-oidc" ))
		 * .oauth2Client(Customizer.withDefaults()); return http.build(); }
		 */
}