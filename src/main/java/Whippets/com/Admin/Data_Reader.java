package Whippets.com.Admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Data_Reader {

	
	public String ReadFile(String Key) throws IOException {
		
		 FileInputStream fds = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Data\\Data.Properties");
		 Properties prop = new Properties();
		 prop.load(fds); 
		 String value= prop.getProperty(Key);
		 
		 return value;
	}
	
}
