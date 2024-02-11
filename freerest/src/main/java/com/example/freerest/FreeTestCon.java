package com.example.freerest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FreeTestCon {
	
	
	
	
	
	int i=0;
	@GetMapping("freerest")
	public List<Photo> listRest() {
		
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
	
	
	
	
	

}
