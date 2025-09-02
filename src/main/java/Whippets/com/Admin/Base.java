package Whippets.com.Admin;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public String id;
	public String pass;
	public String URL;
	
	public WebDriver d;
	
	
	
	@BeforeMethod
	public void Setup() throws IOException{
		
		Data_Reader file = new Data_Reader();
		id = file.ReadFile("Id");
		pass = file.ReadFile("Pass");
		URL = System.getProperty("urls")!=null ? System.getProperty("urls") : file.ReadFile("Url");
        String browsername = System.getProperty("Browser")!=null ? System.getProperty("Browser") : file.ReadFile("Browser1");
		
		if(browsername.equalsIgnoreCase("chrome")){
			
			WebDriverManager.chromedriver().setup();
			d = new ChromeDriver();}
		
		if(browsername.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			d = new FirefoxDriver();
			}}
	
	
	@AfterMethod
	public void Process_Kill(){
		
		if(d!=null){
			d.quit();
		}
		
		
		
	}
	
	

}
