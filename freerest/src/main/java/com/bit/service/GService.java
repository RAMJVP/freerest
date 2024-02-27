package com.bit.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.services.drive.model.File;

public interface GService {
	
	public List<File> listFiles(String folderId) throws IOException, GeneralSecurityException;

}
