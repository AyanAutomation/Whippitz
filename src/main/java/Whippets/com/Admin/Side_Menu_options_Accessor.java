package Whippets.com.Admin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners_Reports.Listen;
import Locaters.Side_Menu_Access_Locators;

public class Side_Menu_options_Accessor extends Login{
	
  
	
	 List<WebElement> menu_itemsList = new ArrayList<WebElement>();
	
	@Test
	public void side_menu() throws IOException{
		
		
		Side_Menu_Access_Locators p = new Side_Menu_Access_Locators(d);
		
		try {p.sidebar();
		menu_itemsList = p.Sidemenus();}catch(Exception ko) {
		login();
		p.sidebar();
		menu_itemsList = p.Sidemenus();}}
	
	
	
	
	public void menu_Accessor(String MenuOption, String Submenu_option ) throws IOException, InterruptedException{
		
		Side_Menu_Access_Locators p = new Side_Menu_Access_Locators(d);
		
		
		
		
			side_menu();
			for(WebElement menu_item:menu_itemsList ){
			if(menu_item.getText().contains(MenuOption)){
			menu_item.click();
			p.Product_Submenu_list();
			List<WebElement> product_submenuoptionsElments = p.options();
			for(WebElement op:product_submenuoptionsElments){
		    if(op.getText().contains(Submenu_option)){
			op.click();
			Thread.sleep(600);
			break;}}break;}}
	

}}
