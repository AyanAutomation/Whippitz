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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners_Reports.Listen;
import Locaters.Login_locaters;
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
		
		all_Collections_clear();
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
		JavascriptExecutor js = (JavascriptExecutor)d;
		
		all_Collections_clear();
		order_list_accessor();
		Thread.sleep(800);try {
		List<WebElement> Column_names = p.Column_titles();}
		catch(Exception ko) {
		//slider_operations("Customer");
		js.executeScript("document.body.style.zoom='80%'");	
		Thread.sleep(800);
		List<WebElement> Columnnames = p.Column_titles();
		for(WebElement Column_name:Columnnames ){
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
				   Column_names_and_Values.put(columnname, Driver_name);}}} /*
	         for(Map.Entry<String,Set<String>> pair:Column_names_and_Values.entrySet()){
	        	 String key = pair.getKey();
	        	 System.out.println(key+"  =================================================   ");
	        	 System.out.println(); 
	        	 if(key.contains("#Order")) {
	        	 for(String orids:pair.getValue()){
	        		 System.out.println(orids);
		        	 System.out.println(); }}
	        	 if(key.contains("Customer")) {
		        	 for(String customers:pair.getValue()){
		        		 System.out.println(customers);
			        	 System.out.println();}}
	        	 if(key.contains("Driver")) {
		        	 for(String drivers:pair.getValue()){
		        		 System.out.println(drivers);
			        	 System.out.println();}}} */
	         }
	
	       @Test
	       public void Driver_assigning_to_order() throws IOException, InterruptedException{
		
	    	   Order_Module_Locaters p = new Order_Module_Locaters(d);
	    	   JavascriptExecutor js = (JavascriptExecutor)d;
	    	   Login_locaters lg = new Login_locaters(d);
	    	   Product_Module_locaters pd = new Product_Module_locaters(d);
	    	   
		       
	    	  String Drivername ="Nikolai Petrov";  
	    	   
	         order_details_by_customer_name("Sindbaad Sarkar");
	    	 js.executeScript("arguments[0].scrollIntoView(true);",p.driver_dropdown());
	    	   Select s = new  Select(p.driver_dropdown());
	    	   List <WebElement> options = s.getOptions();
	    	   
	    	    for(WebElement option:options){
    		    System.out.println("drivers are "+option.getText().trim());
	    		   System.out.println(); 
	    		   if(option.getText().trim().equalsIgnoreCase(Drivername)){
	    			   
	    			   s.selectByVisibleText(Drivername);
	    			   lg.submit_button().click();
	    			  try { pd.searchBox();} 
	    			  catch(Exception eo){
	    				  
	    				  lg.submit_button().click();
	    				  Thread.sleep(800);
	    				 String msg = pd.product_toast().getText().trim();
	    				 System.out.println("Error Message   ------------  "+msg);
	    				 System.out.println();
	    				 if(msg.contains("This Driver is already assigned to this Order")){
	    					 
	    					 d.navigate().back();
	    					 pd.searchBox();
	    				 }}
	    			   break;}}}
	
	      
	      public void order_details_by_customer_name(String Customer_name)throws IOException, InterruptedException{
	    	  
	    	  Order_Module_Locaters p = new Order_Module_Locaters(d); 	  
	    	  
	    	  String customer= Customer_name;
	    	  
	    	  order_list_accessor();
	    	  List<WebElement> allrows = p.rows();
              String row_driver_cell_text;
	    	  String row_customer_cell_text;
	    	  
	    	
	    	
	    		  for(WebElement rowcontent:allrows) {
	    			  
	    			  row_driver_cell_text = rowcontent.findElement(By.xpath(".//td[6]")).getText().trim();
	    	          row_customer_cell_text = rowcontent.findElement(By.xpath(".//td[2]")).getText().trim();
	    			  
	    	      if(row_customer_cell_text.equalsIgnoreCase(customer) && row_driver_cell_text.isEmpty()) {
	    		                System.out.println("No driver assigned for this order — clicking row...");
	   	    		            rowcontent.findElement(By.xpath(".//a[1]")).click();
	   	    		            p.entered_order_details_confirmation();
	   	    		            System.out.println("Clicked row and opened order details.");
	   	    		            System.out.println();
	   	    		            break ;}}} 
	       
	       
	       
	
     public void all_Collections_clear(){
    	 
    	 Column_names_and_Values.clear();
    	 columnnames.clear();
    	 order_ids.clear();
    	 Customer_name.clear();
    	 Driver_name.clear();
    	 row_datas.clear();
    	 Order_details.clear();
     }


     
      public void slider_operations(String headerText) throws InterruptedException{
    	  
    	  JavascriptExecutor js = (JavascriptExecutor)d;
    	  Order_Module_Locaters p = new Order_Module_Locaters(d);
    	  
    	   js.executeScript("arguments[0].scrollIntoView(true);", p.dataTables_scrollBody());
    	   Thread.sleep(300);
    	   js.executeScript("arguments[0].scrollLeft = arguments[1].offsetLeft + 200;", p.dataTables_scrollBody(), p.headerByText(headerText, d));
    	   js.executeScript("arguments[0].scrollIntoView({block:'start'});", p.dataTables_scrollHead());
            Thread.sleep(250);
    	 }


}
