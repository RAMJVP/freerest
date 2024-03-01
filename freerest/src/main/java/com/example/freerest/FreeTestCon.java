package com.example.freerest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.bit.service.GService;
import com.bit.service.GoogleAuth;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.services.drive.model.File;



import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Mono;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
public class FreeTestCon {
	
	
	@Autowired
	private GService gService;
	
	int i=0;
	@GetMapping("freerest")
	public List<Photo> listRest() {
		System.out.println("req###");
		
		System.out.println("req"+i++);
		
		List<Photo> pList= new ArrayList<Photo>();
		
		Photo p= new Photo();
		p.setId(0);
		p.setUrl("https://drive.google.com/uc?export=view&id=1y6UxV-quYC_gIpqjPwGvKR5gShcT1ZbK");
		Photo p1= new Photo();
		p1.setId(1);
		p1.setUrl("https://drive.google.com/uc?export=view&id=1f1sHjZMdrBlAXR3XDAoEStJ-qNRj25sM");
		
		Photo p2= new Photo();
		p2.setId(2);
		p2.setUrl("https://drive.google.com/uc?export=view&id=14GGY17j6Q2_NOo8zIqO0FdloIER3ULir");
			
			
		
		pList.add(p);pList.add(p1);pList.add(p2);
		return pList;
		
	}

	
	
	@GetMapping("listImg")
	public List<Photo> listImg() {
		
		System.out.println("req"+i++);
		
		List<Photo> pList= new ArrayList<Photo>();
		
		Photo p= new Photo();
		p.setId(0);
		p.setUrl("https://drive.google.com/uc?export=view&id=1y6UxV-quYC_gIpqjPwGvKR5gShcT1ZbK");
		Photo p1= new Photo();
		p1.setId(1);
		p1.setUrl("https://drive.google.com/uc?export=view&id=1f1sHjZMdrBlAXR3XDAoEStJ-qNRj25sM");
		
		Photo p2= new Photo();
		p2.setId(2);
		p2.setUrl("https://drive.google.com/uc?export=view&id=14GGY17j6Q2_NOo8zIqO0FdloIER3ULir");
			
			
		
		pList.add(p);pList.add(p1);pList.add(p2);
		return pList;
		
	}
	
	
	@Value("${gooa.baaa}")
	public String ab;
	
	
	@GetMapping("folderimg")
	public List<File> folderimg() {
		System.out.println("req### folderimg");
		System.out.println("req"+i++);
		System.out.println("req"+ab);
		try {
			//return List.of();
		return gService.listFiles("1RnDZCuNVBexG-eOSSgzzSDRygNpJ_Wjb");
		}
			catch (IOException e) {
			e.printStackTrace();
			System.err.println("err "+e.getCause());
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			System.err.println("err "+e.getCause());
		}
		return List.of();
	}
	
	
	
	
	@Autowired
	private WebClient webClient;
	
	@GetMapping("folderimg1")
	public String folderimg1() {
		 
		return webClient.get().uri("https://api.restful-api.dev/objects").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class).block();
	
	}
	
	@GetMapping("folderimg2")
	public String folderimg2(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer ya29.a0AfB_byD4ckUdpmh5U9GMKbYdp6RE9h2g5lvVjDYKRMIjPaUxrIyPTJLQJqO4VNzZ1u1JD5gvcKMIw3zW6vT640cJyTsVjcuxiumLIxUFxaScF46yEJZf6bT8voInpLTKxxAdJpNffpZnWbkFRYJxOQWPJgBTcvxyJBEBjDJyIOWaaCgYKAd8SARESFQHGX2Mig1mzFuzj3HIVqwyROAxEsQ0179");
		
		   return webClient.get()
	                .uri("https://www.googleapis.com/drive/v3/files")
	                .attributes(attrs -> attrs.put(OAuth2AuthorizedClient.class.getName(), authorizedClient))
	                .headers(httpHeaders -> httpHeaders.addAll(headers))
	                .retrieve()
	                .bodyToMono(String.class)
	                .block();
		
		   
		   
		   //ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient)
		   
		 //  .attributes(oauth2AuthorizedClient(authorizedClient))
			/*
			 * return webClient.get() .uri("http://backend-resources:8082/messages")
			 * .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.
			 * oauth2AuthorizedClient(authorizedClient)) .retrieve()
			 * .bodyToMono(String.class) .block();
			 */
	
	}
	
	 
//	@Autowired
	//private OAuth2AuthorizedClientService clientService;
	
	
	
	
	@GetMapping("login/oauth2/code/google")
	public void oauth2callback(HttpServletRequest req) {
		System.out.println("req### oauth2callback");
		System.out.println("req oauth"+i++);
		
		
		String code= req.getParameter("code");
		
		if(code!=null) {
			saveToken(code);
		}
		
	}
	

	@Value("${google.oauth.callback.uri}")
	private  String callbackUri;



	private void saveToken(String code) {
		try {
			
			GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow= GoogleAuth.authorizationCodeFlow();
			GoogleTokenResponse resp = googleAuthorizationCodeFlow.newTokenRequest(code).setRedirectUri(callbackUri).execute();
		googleAuthorizationCodeFlow.createAndStoreCredential(resp, "user");//"471894109466-lamo92va1qcb0mcpq3n67njpf7u3t579.apps.googleusercontent.com");//user
		}
		catch (IOException e) {
			e.printStackTrace();
			System.err.println("saveToken err "+e.getCause());
			//http://localhost:8080/folderimg
			//freerest.com
			//http://freerest.com/folderimg
		}
		
	}
	

}
