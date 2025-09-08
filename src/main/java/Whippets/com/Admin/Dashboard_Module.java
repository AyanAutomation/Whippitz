package Whippets.com.Admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Locaters.Dashboard_Locaters;
import Repeative_codes.Generic_codes;

public class Dashboard_Module extends Customer_Module{

	
	public void DashBoard_Redirection(WebDriver d){
		
		Dashboard_Locaters p = new Dashboard_Locaters(d);
		Generic_codes g = new Generic_codes(d);
		
		p.Dashboard_menu_Options();
		WebElement dashboard_Option = p.Dashboard_menu_Options();
		g.Move_to_element(dashboard_Option);
		dashboard_Option.click();
		p.Dashboard_land_confirmation();
		
		
		
		
		
	}
	
	
}
