package Whippets.com.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Locaters.Dashboard_Locaters;
import Locaters.Login_locaters;
import Locaters.Product_Module_locaters;
import Repeative_codes.Generic_codes;

@Listeners(Listeners_Reports.Listen.class)
public class Product_module extends Side_Menu_options_Accessor{
	
	List<String> products = new ArrayList<String>(); 
	TreeSet<String> Uniqueproductset = new TreeSet<String>();
	List<String> duplicates = new ArrayList<String>();
	@Test
	public void Product_list_Accessor() throws IOException, InterruptedException{
		
		
		Product_Module_locaters p = new Product_Module_locaters(d);
		
		products.clear();
		Product_menu_Accessor("Product List");
		p.select_dropdown();
		Select s = new Select(p.select_dropdown());
		s.selectByVisibleText("100");
		Thread.sleep(800);
		
		}
		
		
	@Test	
	public void Duplicate_value_checker()throws IOException, InterruptedException{
		
		product_list_data_collector();
		Uniqueproductset.clear();
		duplicates.clear();
		for(String Product:products){
			
			if(Uniqueproductset.contains(Product)){
				
			System.out.println(" Duplicate product found "+ Product+" Testcase Failed");
			System.out.println();
			Duplicate_Product_delete(Product);
			d.navigate().refresh();
			Thread.sleep(500);
			} 
			
			Uniqueproductset.add(Product);}
		
	}
		
		
	     public void Product_search(String Product_name) throws IOException, InterruptedException{
	    	 
	    	 Product_Module_locaters p = new Product_Module_locaters(d); 
	    	
	        try{
	    	String Productname = products.get(2);
   	        p.search_box().sendKeys(Product_name);
   	        Thread.sleep(800);
   	        System.out.println(Productname.equalsIgnoreCase(p.Second_column().get(0).getText().trim())? "Testcase Passsed Search Working":"Testcase Failed Search not working");
   	        System.out.println();}catch(Exception mmo) {
	    	 product_list_data_collector();
	    	 String Productname = products.get(2);
	    	 p.search_box().sendKeys(Product_name);
	    	 Thread.sleep(800);
	    	 System.out.println(Productname.equalsIgnoreCase(p.Second_column().get(0).getText().trim())? "Testcase Passsed Search Working":"Testcase Failed Search not working");
	    	 System.out.println();}
	     }
	
		
		
		public void product_list_data_collector() throws IOException, InterruptedException{
			
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
		/*		System.out.println("Products are  "+Product.getText());
				System.out.println();*/}}
		
		
	
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
		
		@Test
		public void product_list_count_Check_with_Dashboard() throws IOException, InterruptedException{
			
			Dashboard_Locaters p = new Dashboard_Locaters(d);
			Generic_codes g = new Generic_codes(d);
			
			product_list_data_collector();
			p.Dashboard_menu_Options();
			WebElement dashboard_Option = p.Dashboard_menu_Options();
			g.Move_to_element(dashboard_Option);
			dashboard_Option.click();
			p.Dashboard_land_confirmation();
			String number_of_prod = p.Dashbaord_status_cards().get(1).getText();
			String number_of_products_in_list = Integer.toString(products.size());
			System.out.println(number_of_prod.equals(number_of_products_in_list)?"Testcase Passed "+number_of_prod+" is Matching with "+number_of_products_in_list:"Testcase Failed"+number_of_prod+" is not MAtching with  "+number_of_products_in_list);
			System.out.println();}
		
		
		  @Test(dataProvider="getProductData")
		  public void Product_Add(TreeMap<String, Object> data) throws IOException, InterruptedException{
			  
		  Product_Module_locaters p = new Product_Module_locaters(d);	
		  Login_locaters ll = new Login_locaters(d);
		  
		  
		
		  Product_menu_Accessor("Add Product");
		  p.Add_Edit_form();
		  Astrix_mandatory_count_checker();
		  p.input_fields();
		  p.input_fields().get(0).sendKeys(String.valueOf(data.get("Product Name"))); 
		  Select s1 = new Select(p.Product_type_dropdowns());
		  s1.selectByVisibleText(String.valueOf(data.get("Product Type")));
		  p.input_fields().get(1).sendKeys(String.valueOf(data.get("Stock")));
		  p.input_fields().get(2).sendKeys(String.valueOf(data.get("Product Price (AUD)")));
		  Select s2 = new Select(p.Discount_type_dropdowns());
		  s2.selectByVisibleText(String.valueOf(data.get("Discount Type")));
		  p.discount_field().click();
		  p.discount_field().sendKeys(String.valueOf(data.get("Discount Price (AUD)")));
		  Thread.sleep(800);
		  d.switchTo().frame(p.Description_frame());
		  Thread.sleep(800);
		  p.Description_ck_Editor().click();
		  Thread.sleep(800);
		  p.Description_ck_Editor().sendKeys(String.valueOf(data.get("Short Description")));
		  Thread.sleep(800);
		  d.switchTo().defaultContent();
		  Thread.sleep(800);
		  p.image_upload_field().sendKeys(String.valueOf(data.get("Image Path")));
		  p.form_submit_button().click();
		  ll.toast();
		  System.out.println(ll.toast().getText());
		  System.out.println();
		  }
		
		  @Test(dataProvider="getProductData")
		  public void Added_Product_search_and_delete(TreeMap<String, Object> Prod) throws IOException, InterruptedException{
			  
              Product_search(String.valueOf(Prod.get("Product Name")));
			  product_delete();
			  
			 }
		  
		 public void Duplicate_Product_delete(String Duplicate_products) throws IOException, InterruptedException{
			 
			 Product_search(Duplicate_products);
			 Thread.sleep(900);
			 product_delete();}
		  
		  
		  
		  @DataProvider
		    public Object[][] getProductData() {
		        
			    
			  
			  
			  String Dynamic_file_path = System.getProperty("user.dir") + "//ProductImages//";

		        TreeMap<String, String> product1 = new TreeMap<>();
		        product1.put("Product Name", "Whippitz N₂O Cylinder – Mint Cream");
		        product1.put("Product Type", "Single");
		        product1.put("Stock", "100");
		        product1.put("Product Price (AUD)", "42.00");
		        product1.put("Discount Type", "Fixed Discount");
		        product1.put("Discount Price (AUD)", "5.00");
		        product1.put("Short Description", "Refreshing mint cream flavour, ideal for topping desserts and beverages.");
		        product1.put("Image Path", Dynamic_file_path + "mint_cream.png");

		        TreeMap<String, String> product2 = new TreeMap<>();
		        product2.put("Product Name", "Whippitz N₂O Cylinder – Caramel Cream Pack");
		        product2.put("Product Type", "Group");
		        product2.put("Stock", "50");
		        product2.put("Product Price (AUD)", "120.00");
		        product2.put("Discount Type", "Percentage Discount");
		        product2.put("Discount Price (AUD)", "10");
		        product2.put("Short Description", "Rich caramel cream, perfect for cafés and bakeries. Comes in value group packs.");
		        product2.put("Image Path", Dynamic_file_path + "caramel_cream.png");

		        TreeMap<String, String> product3 = new TreeMap<>();
		        product3.put("Product Name", "Whippitz N₂O Cylinder – Berry Bliss");
		        product3.put("Product Type", "Single");
		        product3.put("Stock", "80");
		        product3.put("Product Price (AUD)", "45.00");
		        product3.put("Discount Type", "Fixed Discount");
		        product3.put("Discount Price (AUD)", "3.00");
		        product3.put("Short Description", "Fruity berry flavour, delicious for milkshakes, cakes, and party desserts.");
		        product3.put("Image Path", Dynamic_file_path + "berry_bliss.png");

		        TreeMap<String, String> product4 = new TreeMap<>();
		        product4.put("Product Name", "Whippitz N₂O Cylinder – Vanilla Cream Bulk Pack");
		        product4.put("Product Type", "Group");
		        product4.put("Stock", "40");
		        product4.put("Product Price (AUD)", "200.00");
		        product4.put("Discount Type", "Percentage Discount");
		        product4.put("Discount Price (AUD)", "15");
		        product4.put("Short Description", "Classic vanilla cream cylinders in bulk pack for professional use.");
		        product4.put("Image Path", Dynamic_file_path + "vanilla_cream.png");

		        return new Object[][] {
		            { product1 },
		            { product2 },
		            { product3 },
		            { product4 }
		        };
		    }
		
		
		
		public void Astrix_mandatory_count_checker() throws InterruptedException{
			
			  Product_Module_locaters p = new Product_Module_locaters(d);	
			  Generic_codes g = new Generic_codes(d);
			
			  p.form_submit_button().click();
			  int Error_Mesage_count = p.error_messages().size();
			  Thread.sleep(800);
			  g.Scroll_into_view(p.Top_oftheForm());
			  Thread.sleep(800);
			  p.Asteriks();
			  int Asterisk_count = p.Asteriks().size();
			  System.out.println(Error_Mesage_count==Asterisk_count ? "Testcase Passed ErrorMessage Count "+Error_Mesage_count+" is Equals to Astrix Count "+Asterisk_count: "Testcase Fail ErrorMessage Count "+Error_Mesage_count+" NOT Equals to Astrix Count "+Asterisk_count);
			  System.out.println();
			
			
			
		}
		
		
	
	}
	


