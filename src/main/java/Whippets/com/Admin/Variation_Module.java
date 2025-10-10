package Whippets.com.Admin;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Locaters.Product_Module_locaters;

public class Variation_Module extends Product_module{
	
	@Test
	public void Variation_list_accessor() throws IOException, InterruptedException{
		
		Product_Module_locaters p = new Product_Module_locaters(d);
		
		products.clear();
		menu_Accessor("Products","Variation List");
		try{p.select_dropdown();
		Select s = new Select(p.select_dropdown());
		s.selectByVisibleText("100");
		Thread.sleep(800);}
		catch(Exception km){System.out.println("Variation List don't have pagenation dropdown");
		System.out.println();;}}
	
	
	public void variation_list_data_collector(){
		
		
		
		
		
		
		
	}
	
	
	
	

}
