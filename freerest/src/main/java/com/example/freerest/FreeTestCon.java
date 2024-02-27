package com.example.freerest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.service.GService;
import com.bit.service.GoogleAuth;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.services.drive.model.File;

import jakarta.servlet.http.HttpServletRequest;

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
		 gService.listFiles("tools");
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
		}
		
	}
	

}
