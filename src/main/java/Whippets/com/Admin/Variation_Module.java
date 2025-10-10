package Whippets.com.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
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
			unique_variation_texts.add(row_texts);}}
		
		
		
		@Test(dataProvider="variation_data")
		public void Add_Variation(TreeMap<String,String> data) throws IOException, InterruptedException{
			
		Variation_Module_Locaters p = new Variation_Module_Locaters(d);
			
		menu_Accessor("Products","Add Variation");	
		p.Add_Edit_form();
		product_edit_form_astrix_checker();
		d.navigate().refresh();
		p.inputs().get(0).sendKeys(data.get("Variation Name"));	
		p.inputs().get(1).sendKeys(data.get("Variation Size"));	
		p.submit().click();
		p.variation_list_landing_confirmation();
			
		}
		
		@DataProvider
		public Object[][] variation_data(){
		
			 TreeMap<String, String> v1 = new TreeMap<>();
			    v1.put("Variation Name", "Pack of 2");
			    v1.put("Variation Size", "2");

			    TreeMap<String, String> v2 = new TreeMap<>();
			    v2.put("Variation Name", "Pack of 4");
			    v2.put("Variation Size", "4");

			    TreeMap<String, String> v3 = new TreeMap<>();
			    v3.put("Variation Name", "Pack of 5");
			    v3.put("Variation Size", "5");

			    TreeMap<String, String> v4 = new TreeMap<>();
			    v4.put("Variation Name", "Pack of 7");
			    v4.put("Variation Size", "7");

			    TreeMap<String, String> v5 = new TreeMap<>();
			    v5.put("Variation Name", "Pack of 9");
			    v5.put("Variation Size", "9");

			    TreeMap<String, String> v6 = new TreeMap<>();
			    v6.put("Variation Name", "Pack of 15");
			    v6.put("Variation Size", "15");

			    TreeMap<String, String> v7 = new TreeMap<>();
			    v7.put("Variation Name", "Pack of 16");
			    v7.put("Variation Size", "16");

			    TreeMap<String, String> v8 = new TreeMap<>();
			    v8.put("Variation Name", "Pack of 18");
			    v8.put("Variation Size", "18");

			    TreeMap<String, String> v9 = new TreeMap<>();
			    v9.put("Variation Name", "Pack of 20");
			    v9.put("Variation Size", "20");

			    TreeMap<String, String> v10 = new TreeMap<>();
			    v10.put("Variation Name", "Pack of 24");
			    v10.put("Variation Size", "24");

			    return new Object[][]{
			        { v1 }, { v2 }, { v3 }, { v4 }, { v5 },
			        { v6 }, { v7 }, { v8 }, { v9 }, { v10 }
			    };}}
