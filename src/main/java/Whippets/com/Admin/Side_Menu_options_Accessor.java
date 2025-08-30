package Whippets.com.Admin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import Locaters.Side_Menu_Access_Locators;

public class Side_Menu_options_Accessor extends Login{
	
  
	
	 List<WebElement> menu_itemsList = new ArrayList<WebElement>();
	
	@Test
	public void side_menu() throws IOException{
		
		
		Side_Menu_Access_Locators p = new Side_Menu_Access_Locators(d);
		
		login();
		p.sidebar();
		menu_itemsList = p.Sidemenus();}
	
	
	
	
	public void Product_menu_Accessor(String desiredoption) throws IOException, InterruptedException{
		
		Side_Menu_Access_Locators p = new Side_Menu_Access_Locators(d);
		
		side_menu();
		for(WebElement menu_item:menu_itemsList ){
			
			if(menu_item.getText().contains("Products")){
				
				menu_item.click();
			    p.Product_Submenu_list();
			    List<WebElement> product_submenuoptionsElments = p.options();
			    for(WebElement op:product_submenuoptionsElments){
			    	if(op.getText().contains(desiredoption)){
			    		
			    		op.click();
			    		Thread.sleep(600);
			    		p.page_title();
			    		System.out.println("Page tile =  "+p.page_title().getText());
			    		System.out.println();
			    		break;}}break;}}
		
		
		
		
		
		
	}
	

}
