package Whippets.com.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Locaters.Product_Module_locaters;
import Locaters.Variation_Module_Locaters;

public class Variation_Module extends Product_module{
	
	
	TreeSet<String> unique_variation_texts = new TreeSet<String>();
	
	

	public void Variation_list_accessor() throws IOException, InterruptedException{
		
		Product_Module_locaters p = new Product_Module_locaters(d);
		
		
		menu_Accessor("Products","Variation List");
		try{p.select_dropdown();
		Select s = new Select(p.select_dropdown());
		s.selectByVisibleText("100");
		Thread.sleep(800);}
		catch(Exception km){System.out.println("List don't have pagenation dropdown");
		System.out.println();}}
	
	
	@Test
	public void variation_list_data_collector() throws IOException, InterruptedException{
		
		Variation_Module_Locaters v = new Variation_Module_Locaters(d);
		
		unique_variation_texts.clear();
		Variation_list_accessor();
		v.variation_list_landing_confirmation();
		List <WebElement> row_elements = v.rows();
		for(WebElement row_element:row_elements){
			 
			String row_texts = row_element.getText().trim();
			
			if(unique_variation_texts.contains(row_texts)){
				
				System.out.println("Variation List contains duplicate values");
				System.out.println();}
			System.out.println(row_texts);
			System.out.println();
			unique_variation_texts.add(row_texts);
			
		}
		
		
		
	}
	
	
	
	

}
