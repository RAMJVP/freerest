package com.bit.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GServList implements GService{
	
	


	private static final String APPLICATION_NAME = "Web client 2";//"Google Drive API Java Spring Boot";

	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

	public List<File> listFiles(String folderId) throws IOException, GeneralSecurityException {
		Drive service = getDriveService();
		FileList result = service.files().list().setQ("'" + folderId + "' in parents")
				.setFields("files(id, name, webViewLink)").execute();
		return result.getFiles();
	}

	private Drive getDriveService() throws IOException, GeneralSecurityException {
		Credential credential = GoogleAuth.getCredentials();
		return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}



}
