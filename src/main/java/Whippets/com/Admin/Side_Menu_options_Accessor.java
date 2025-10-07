package Whippets.com.Admin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners_Reports.Listen;
import Locaters.Side_Menu_Access_Locators;
import Repeative_codes.Generic_codes;

public class Side_Menu_options_Accessor extends Login{
	
  
	
	 List<WebElement> menu_itemsList = new ArrayList<WebElement>();
	
	@Test
	public void side_menu() throws IOException{
		
		
		Side_Menu_Access_Locators p = new Side_Menu_Access_Locators(d);
		
		try{p.sidebar();
		}
		catch(Exception ko) {
		login();
		p.sidebar();
		}}
	
	
	
	
	public void menu_Accessor(String MenuOption, String Submenu_option ) throws IOException, InterruptedException{
		
		Side_Menu_Access_Locators p = new Side_Menu_Access_Locators(d);
		Generic_codes g = new Generic_codes(d);
		JavascriptExecutor js = (JavascriptExecutor)d;
		
		
		    side_menu();
		    menu_itemsList = p.Sidemenus(); 
		    String Menu_text;
		    if(MenuOption.equalsIgnoreCase("order")||MenuOption.equalsIgnoreCase("delivery-issues")||MenuOption.equalsIgnoreCase("feedbacks")||MenuOption.equalsIgnoreCase("CMS")||MenuOption.equalsIgnoreCase("roles"))
			{
				Second_section_menu_items(MenuOption);	
			}else {
			OuterLoop:	
			for(WebElement menu_item:menu_itemsList){
				
				Menu_text = menu_item.getText();
				
			if(Menu_text.equalsIgnoreCase(MenuOption)){
				
				try{menu_item.click();
				p.Product_Submenu_list();}
				catch(Exception e){
					js.executeScript("arguments[0].click();", menu_item);
					p.Product_Submenu_list();}
				List<WebElement> product_submenuoptionsElments = p.options();
				for(WebElement op:product_submenuoptionsElments){
			    if(Submenu_option!=null && op.getText().equalsIgnoreCase(Submenu_option)){
				op.click();
				Thread.sleep(600);
				break OuterLoop;}
			    if(Submenu_option==null){ 
					break OuterLoop;}}}
			
			}
			
			}}
			
			
	 public void Second_section_menu_items(String Menu_name){
		 
		 Side_Menu_Access_Locators p = new Side_Menu_Access_Locators(d);
		 
		 p.other_side_menus(Menu_name).click();}
	

}
