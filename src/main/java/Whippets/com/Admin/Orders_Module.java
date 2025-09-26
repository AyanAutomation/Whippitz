package Whippets.com.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.IntStream;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Locaters.Order_Module_Locaters;

public class Orders_Module extends Product_module{
	
	TreeMap<String,Set> Column_names_and_Values = new TreeMap<String,Set>();
	List<String> columnnames = new ArrayList<String>();
	TreeSet<String> order_ids = new TreeSet<String>();
	
	@Test
	public void order_list_accessor() throws IOException, InterruptedException{
		
		Order_Module_Locaters p = new Order_Module_Locaters(d);
		
		menu_Accessor("Orders",null);
		p.landed_in_order();
		}
	
	
	
	
	public void order_list_data_collector() throws IOException, InterruptedException{
		
		Order_Module_Locaters p = new Order_Module_Locaters(d);
		
		order_list_accessor();
		List<WebElement> Column_names = p.Column_titles();
		for(WebElement Column_name:Column_names ){
			columnnames.add(Column_name.getText().trim());}
		List<WebElement> orderids = p.first_column();
		IntStream.range(0, orderids.size()).forEach(m->{
			
			order_ids.add(orderids.get(m).getText().trim());
			
			
		});
		
		
	}
	
	
	
	

}
