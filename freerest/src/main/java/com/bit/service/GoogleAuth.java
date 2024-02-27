package com.bit.service;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleAuth {

	private static final String CREDENTIALS_FILE_PATH = "client_secret_471894109466-lamo92va1qcb0mcpq3n67njpf7u3t579.apps.googleusercontent.com.json";//"alert-hall-412514-cc8adcbb282c.json";//"alert-hall-412514-cc8adcbb282c.json";

	
	//private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/drive");

	public static Credential getCredentials() throws IOException, GeneralSecurityException {
	
		return new AuthorizationCodeInstalledApp(authorizationCodeFlow(), new LocalServerReceiver()).authorize("Web client 2");//user
	}
	
	
	public static GoogleAuthorizationCodeFlow authorizationCodeFlow(){
		GoogleAuthorizationCodeFlow flow=null;;
		try {
			
			InputStream in = new ClassPathResource(CREDENTIALS_FILE_PATH).getInputStream();
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

			 flow = new GoogleAuthorizationCodeFlow.Builder(
					GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
					.setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens"))).setAccessType("offline")
					.build();

			
			  
			 
			  flow = new GoogleAuthorizationCodeFlow.Builder(
					  GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
		                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
		                .setAccessType("offline")
		                .build();
			  flow.newAuthorizationUrl().setRedirectUri("http://localhost:8080/login/oauth2/code/google").build();
			  //flow.createAndStoreCredential(flow.newTokenRequest("code").setRedirectUri("http://localhost:8080/oauth2callback").execute(), "user");
			  
			
			
					} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error "+e.getCause());
		} 
		return flow;
		
	}

}
