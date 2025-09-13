package Whippets.com.Admin;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners_Reports.Listen;
import Locaters.Login_locaters;

public class Login extends Base{
	
	
	
	public void login() throws IOException{
		
		Data_Reader file = new Data_Reader();
		Login_locaters p = new Login_locaters(d);
		
		d.navigate().to(URL);
		d.manage().window().maximize();	
		p.Banner_Text();
		p.all_input_feilds().get(0).sendKeys(id);
		p.all_input_feilds().get(1).sendKeys(pass);
		p.submit_button().click();
		try{
			Listen.Print_in_Report().log(Status.INFO,p.toast().getText());}
		catch(Exception k) {
			Listen.Print_in_Report().log(Status.INFO,"login Toast not implemented");
		p.Login_confirmation();}}}
