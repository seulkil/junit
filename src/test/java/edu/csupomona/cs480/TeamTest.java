package edu.csupomona.cs480;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.FSUserManager;
import edu.csupomona.cs480.data.provider.UserManager;
import edu.csupomona.cs480.util.ResourceResolver;

public class TeamTest {

	@Test
	public void getfilepath(){
		String userId = "efef";
		System.out.println(userId);
		String realPath = "C:\\Users\\this laptop is big\\cs480\\Win32Project2.vcxproj";
		System.out.println(realPath);
		UserManager urm = new FSUserManager();
		User ur = urm.getUser(userId);
		String fromObject = ur.getFilePath();
		
		
		assertEquals(realPath, fromObject);
	}

	@Test
	public void check() { 
		ResourceResolver rr = new ResourceResolver();
		File file = rr.getUserFile();
		System.out.println("heloo");
		assertEquals(file.getName(), "Multi-map.json");		
	}
}
