package Whippets.com.Admin;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners_Reports.Listen;
import Locaters.Dashboard_Locaters;
import Repeative_codes.Generic_codes;

public class Dashboard_Module extends Product_module{

	
	TreeMap<String, Integer> pack_and_count = new TreeMap<String, Integer>();
	
	
	public void DashBoard_Redirection(){
		
		Dashboard_Locaters p = new Dashboard_Locaters(d);
		Generic_codes g = new Generic_codes(d);
		
		p.Dashboard_menu_Options();
		WebElement dashboard_Option = p.Dashboard_menu_Options();
		g.Move_to_element(dashboard_Option);
		dashboard_Option.click();
		p.Dashboard_land_confirmation();}
	
	@Test
	public void Dashboard_product_count_check() throws IOException, InterruptedException{
		
		Dashboard_Locaters p = new Dashboard_Locaters(d);
		
		TreeMap<String,Integer> product_variation_and_count = product_Variations_count_collector();
		DashBoard_Redirection();
		p.inventory_list();
		List<WebElement> packelements = p.packs_labels();
		List<WebElement> stockelements = p.stock_numbers();
		
	   if(packelements.size()==stockelements.size()){
		   
		   for(int n=0;n<packelements.size();n++) {
		   pack_and_count.put(packelements.get(n).getText().trim(), Integer.parseInt(stockelements.get(n).getText().trim()));
		   Listen.Print_in_Report().log(Status.INFO, "DashBoad :-   "+packelements.get(n).getText().trim()+"  "+Integer.parseInt(stockelements.get(n).getText().trim()));
		   System.out.println("DashBoad :-   "+packelements.get(n).getText().trim()+"  "+Integer.parseInt(stockelements.get(n).getText().trim()));
		   }
		   
		   
		   
		   
	   }
		
	}
	
	
	
	
	
}
