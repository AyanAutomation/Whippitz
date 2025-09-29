package Whippets.com.Admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.apache.commons.lang3.tuple.Pair;
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
		
		pack_and_count.clear();
		TreeMap<String,Integer> product_variation_and_count = product_Variations_count_collector();
		DashBoard_Redirection();
		p.inventory_list();
		List<WebElement> packelements = p.packs_labels();
		List<WebElement> stockelements = p.stock_numbers();
		
	   if(packelements.size()==stockelements.size()){
		   
		   for(int n=0;n<packelements.size();n++) {
			  
			   if(packelements.get(n).getText().trim().contains(":")){
				   
				  String Clean_key = packelements.get(n).getText().trim().split(":")[0].trim();
				   pack_and_count.put(Clean_key, Integer.parseInt(stockelements.get(n).getText().trim()));
				   Listen.Print_in_Report().log(Status.INFO, "DashBoad :-   "+Clean_key+" :------  "+Integer.parseInt(stockelements.get(n).getText().trim()));
				   System.out.println("DashBoad :-  "+Clean_key+" :------ "+Integer.parseInt(stockelements.get(n).getText().trim()));}}}
	   
	   
		for(Map.Entry<String, Integer> dashboard_pair:pack_and_count.entrySet()){
			
			
			if(Objects.equals(product_variation_and_count.get(dashboard_pair.getKey().trim()),  dashboard_pair.getValue())) {
				System.out.println();
				Listen.Print_in_Report().log(Status.INFO, "Pair matches testcase passed "+dashboard_pair.getKey()+" in dashboard is "+dashboard_pair.getValue()+" in product list count "+product_variation_and_count.get(dashboard_pair.getKey()));
				System.out.println("Pair matches testcase passed "+dashboard_pair.getKey()+" in dashboard is "+dashboard_pair.getValue()+" in product list count "+product_variation_and_count.get(dashboard_pair.getKey()));
				System.out.println();
			}else{
				System.out.println(dashboard_pair.getValue()+"  Pair don't match testcase Failed");
				Listen.Print_in_Report().log(Status.INFO,dashboard_pair.getValue()+"    Pair don't match testcase Failed");
			}}}
	
	
	
	
	
}
