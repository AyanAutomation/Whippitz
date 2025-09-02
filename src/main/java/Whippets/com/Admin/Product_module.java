package Whippets.com.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Locaters.Product_Module_locaters;

@Listeners(Listeners_Reports.Listen.class)
public class Product_module extends Side_Menu_options_Accessor{
	
	List<String> products = new ArrayList<String>(); 
	
	@Test
	public void Product_list_Accessor() throws IOException, InterruptedException{
		
		
		Product_Module_locaters p = new Product_Module_locaters(d);
		
		products.clear();
		Product_menu_Accessor("Product List");
		p.select_dropdown();
		Select s = new Select(p.select_dropdown());
		s.selectByVisibleText("100");
		Thread.sleep(800);
		List<WebElement> Product_in_list = p.Second_column();
		for(WebElement Product:Product_in_list){
			
			products.add(Product.getText());
			System.out.println("Products are  "+Product.getText());
			System.out.println();}}
		
		
	@Test	
	public void Duplicate_value_checker()throws IOException, InterruptedException{
		
		Product_Module_locaters p = new Product_Module_locaters(d);
		
		
		Product_list_Accessor();
		TreeSet<String> Uniqueproductset = new TreeSet<String>();
		
		for(String Product:products){
			
			
			System.out.println(Uniqueproductset.contains(Product) ? " Duplicate product found "+ Product+" Testcase Failed" :"Duplicate product not found testcase Passed");
			System.out.println();	
			Uniqueproductset.add(Product);}}
		
		@Test
	     public void Product_search() throws IOException, InterruptedException{
	    	 
	    	 Product_Module_locaters p = new Product_Module_locaters(d); 
	    	
	    	 Product_list_Accessor();
	    	 String Productname = products.get(2);
	    	 
	    	 p.search_box().sendKeys(Productname);
	    	 Thread.sleep(800);
	    	 System.out.println(Productname.equalsIgnoreCase(p.Second_column().get(0).getText().trim())? "Testcase Passsed Search Working":"Testcase Failed Search not working");
	    	 System.out.println();
	     }
	
	
		@Test
		public void product_delete() throws IOException, InterruptedException{
			
			Product_Module_locaters p = new Product_Module_locaters(d);
			
			try {
				
				p.Delete_buttons().get(0).click();
				p.Delete_button().click();
			}
			catch(Exception ko) {
				Product_list_Accessor();
				p.Delete_buttons().get(0).click();
				p.Delete_button().click();}}
		
		
		public void product_list_countcheck(){
			
			
			
		}
		
	
	}
	


