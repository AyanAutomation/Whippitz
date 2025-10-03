package Whippets.com.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners_Reports.Listen;
import Locaters.Order_Module_Locaters;
import Locaters.Product_Module_locaters;

public class Orders_Module extends Product_module{
	
	TreeMap<String,Set<String>> Column_names_and_Values = new TreeMap<String,Set<String>>();
	List<String> columnnames = new ArrayList<String>();
	TreeSet<String> order_ids = new TreeSet<String>();
	TreeSet<String> Customer_name = new TreeSet<String>();
	TreeSet<String> Driver_name = new TreeSet<String>();
	TreeSet<String> row_datas = new TreeSet<String>();
	List<String> Order_details = new ArrayList<>();
	
	@Test
	public void order_list_accessor() throws IOException, InterruptedException{
		
		Order_Module_Locaters p = new Order_Module_Locaters(d);
		Product_Module_locaters po = new Product_Module_locaters(d);
		
		menu_Accessor("order",null);
		p.landed_in_order();
		po.select_dropdown();
		Select s = new Select(po.select_dropdown());
		s.selectByVisibleText("100");
		Thread.sleep(800);
		}
	
	
	
	@Test
	public void order_list_data_collector() throws IOException, InterruptedException{
		
		Order_Module_Locaters p = new Order_Module_Locaters(d);
		
		all_Collections_clear();
		order_list_accessor();
		List<WebElement> Column_names = p.Column_titles();
		for(WebElement Column_name:Column_names ){
			columnnames.add(Column_name.getText().trim());}
		List<WebElement> orderids = p.first_column();
		List<WebElement> Customernames = p.second_column();
		List<WebElement> Drivernames = p.sixth_column();
		IntStream.range(0, orderids.size()).forEach(m->{
			order_ids.add(orderids.get(m).getText().trim());
			Customer_name.add(Customernames.get(m).getText().trim());
			Driver_name.add(Drivernames.get(m).getText().trim());
			});
	   for(String columnname:columnnames){
		   if(columnname.contains("#Order")){
			   Column_names_and_Values.put(columnname, order_ids);}
		   if(columnname.contains("Customer")){
			   Column_names_and_Values.put(columnname, Customer_name);
			   }if(columnname.contains("Driver")){
				   Column_names_and_Values.put(columnname, Driver_name);}} 
	         for(Map.Entry<String,Set<String>> pair:Column_names_and_Values.entrySet()){
	        	 String key = pair.getKey();
	        	 System.out.println(key+"  =================================================   ");
	        	 System.out.println();
	        	 if(key.contains("#Order")) {
	        	 for(String orids:pair.getValue()){
	        		 System.out.println(orids);
		        	 System.out.println();}}
	        	 if(key.contains("Customer")) {
		        	 for(String customers:pair.getValue()){
		        		 System.out.println(customers);
			        	 System.out.println();}}
	        	 if(key.contains("Driver")) {
		        	 for(String drivers:pair.getValue()){
		        		 System.out.println(drivers);
			        	 System.out.println();}}}}
	
	       @Test
	       public void Driver_assigning_to_order() throws IOException, InterruptedException{
		
	    	   Order_Module_Locaters p = new Order_Module_Locaters(d);
	    	   JavascriptExecutor js = (JavascriptExecutor)d;
		       
	    	  String Drivername ="Nikolai Petrov";
	    	   
	    	   order_details_by_customer_name("Haatim Tai");
	    	   js.executeScript("arguments[0].scrollIntoView(true);",p.driver_dropdown());
	    	   Select s = new  Select(p.driver_dropdown());
	    	   List <WebElement> options = s.getOptions();
	    	   
	    	   for(WebElement option:options){
	    		   
	    		   System.out.println("drivers are "+option.getText().trim());
	    		   if(option.getText().trim().equalsIgnoreCase(Drivername)){
	    			   
	    			   s.deselectByVisibleText(Drivername);
	    			   break;
	    		   }
		    		  System.out.println();
	    	   }
	    	   
	    	   }
	
	     
	      public void order_details_by_customer_name(String customer)throws IOException, InterruptedException{
	    	  
	    	  Order_Module_Locaters p = new Order_Module_Locaters(d); 	  
	    	  
	    	  all_Collections_clear();
	    	  order_list_accessor();
	    	  List<WebElement> allrows = p.rows();
	    	  String rowcontents;
	    	  for(WebElement row:allrows){
	    		
	    		  rowcontents = row.getText().trim();
	    		  
	    		 if(rowcontents.contains(customer)){
	    			 
	    			 Boolean Driver_present = false;
	    			 
	    			for(String Drivername:Driver_name) {
	    			
	            	   if(rowcontents.contains(Drivername)) { 
	    				
	            		   Driver_present= true;
	    				 break;}}
	               
	          if(Driver_present==false) {
	    			 row.findElement(By.xpath(".//a[1]")).click();
	    			 p.entered_order_details_confirmation();
	    			 System.out.println(d.getCurrentUrl());
	    			 System.out.println();
	    			 Listen.Print_in_Report().log(Status.INFO, d.getCurrentUrl());
	    			 break;
	    			 }}}
	    	  
	    	  List <WebElement> order_texts = p.Order_details_text();
	    	  
	    	  for(WebElement order_text:order_texts){
	    		  
	    		  Order_details.add(order_text.getText().trim());
	    	/*	  System.out.println(order_text.getText().trim());
	    		  System.out.println(); */
	    		  Listen.Print_in_Report().log(Status.INFO,order_text.getText().trim());
	    	  }
	    	  
	      
	      
	      
	      
	      } 
	       
	       
	       
	
     public void all_Collections_clear(){
    	 
    	 Column_names_and_Values.clear();
    	 columnnames.clear();
    	 order_ids.clear();
    	 Customer_name.clear();
    	 Driver_name.clear();
    	 row_datas.clear();
    	 Order_details.clear();
     }




}
