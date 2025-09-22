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

import com.aventstack.extentreports.Status;

import Listeners_Reports.Listen;
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
		menu_Accessor("Products","Product List");
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
				
			Listen.Print_in_Report().log(Status.INFO," Duplicate product found "+ Product+" Testcase Failed");
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
   	        Listen.Print_in_Report().log(Status.INFO,Productname.equalsIgnoreCase(p.Second_column().get(0).getText().trim())? "Testcase Passsed Search Working":"Testcase Failed Search not working");
   	         }catch(Exception mmo) {
	    	 product_list_data_collector();
	    	 String Productname = products.get(2);
	    	 p.search_box().sendKeys(Product_name);
	    	 Thread.sleep(800);
	    	 Listen.Print_in_Report().log(Status.INFO,Productname.equalsIgnoreCase(p.Second_column().get(0).getText().trim())? "Testcase Passsed Search Working":"Testcase Failed Search not working");
	    	 }
	     }
	
		
		
		public void product_list_data_collector() throws IOException, InterruptedException{
			
			Product_Module_locaters p = new Product_Module_locaters(d);
			
			products.clear();
			menu_Accessor("Products","Product List");
			p.select_dropdown();
			Select s = new Select(p.select_dropdown());
			s.selectByVisibleText("100");
			Thread.sleep(800);
			
			List<WebElement> Product_in_list = p.Second_column();
			
			for(WebElement Product:Product_in_list){
				
				products.add(Product.getText());
		/*		Listen.Print_in_Report().log(Status.INFO,"Products are  "+Product.getText());
				*/}}
		
		
	
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
			Listen.Print_in_Report().log(Status.INFO,number_of_prod.equals(number_of_products_in_list)?"Testcase Passed "+number_of_prod+" is Matching with "+number_of_products_in_list:"Testcase Failed"+number_of_prod+" is not MAtching with  "+number_of_products_in_list);
			}
		
		
		  @Test(dataProvider="getProductData")
		  public void Product_Add(TreeMap<String, Object> data) throws IOException, InterruptedException{
			  
		  Product_Module_locaters p = new Product_Module_locaters(d);	
		  Login_locaters ll = new Login_locaters(d);
		  
		  String ProductType = String.valueOf(data.get("Product Type"));
		  
		  menu_Accessor("Products","Add Product");
		  p.Add_Edit_form();
		  p.filled_input_feilds().get(0).clear();
		  Select s1 = new Select(p.Product_type_dropdowns());
		  s1.selectByVisibleText(ProductType);
		  Astrix_mandatory_count_checker(ProductType);
		  if(ProductType.equalsIgnoreCase("Group")){
			  Select s3 = new Select(p.Variation_type_dropdowns());
			  s3.selectByVisibleText(String.valueOf(data.get("Pack of"))); }
		  p.input_fields().get(0).sendKeys(String.valueOf(data.get("Product Name"))); 
		  p.input_fields().get(1).sendKeys(String.valueOf(data.get("Stock")));
		  p.input_fields().get(2).sendKeys(String.valueOf(data.get("Product Price (AUD)")));
		  Select s2 = new Select(p.Discount_type_dropdowns());
		  s2.selectByVisibleText(String.valueOf(data.get("Discount Type")));
		  if(String.valueOf(data.get("Discount Type")).equalsIgnoreCase("Percentage Discount")){
			 p.percentage_field().sendKeys(String.valueOf(data.get("Discount %")));
		  }
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
		  Listen.Print_in_Report().log(Status.INFO,ll.toast().getText());
		
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
			    product1.put("Product Name", "Whippitz N₂O Cylinder – Strawberry Swirl");
			    product1.put("Product Type", "Group");
			    product1.put("Stock", "90");
			    product1.put("Product Price (AUD)", "48.00");
			    product1.put("Pack of", "Pack of 3");                         // not needed for Single
			    product1.put("Discount Type", "Fixed Discount");
			    product1.put("Discount %", "");                      // not needed for Fixed
			    product1.put("Discount Price (AUD)", "6.00");
			    product1.put("Short Description", "Refreshing chocolate-mint blend, ideal for gourmet desserts and iced beverages.");
			    product1.put("Image Path", Dynamic_file_path + "strawberry_swirl.png");

			    TreeMap<String, String> product2 = new TreeMap<>();
			    product2.put("Product Name", "Whippitz N₂O Cylinder – Pistachio Royale");
			    product2.put("Product Type", "Group");
			    product2.put("Stock", "60");
			    product2.put("Product Price (AUD)", "135.00");
			    product2.put("Pack of", "Pack of 6");                // shown only for Group
			    product2.put("Discount Type", "Percentage Discount");
			    product2.put("Discount %", "12");                    // % field appears
			    product2.put("Discount Price (AUD)", "100.00");            // AUD field disabled/ignored
			    product2.put("Short Description", "Smooth coffee with caramel cream, perfect for cafés and coffee lovers. Pack of 6.");
			    product2.put("Image Path", Dynamic_file_path + "pistachio_royale.png");

			    TreeMap<String, String> product3 = new TreeMap<>();
			    product3.put("Product Name", "Whippitz N₂O Cylinder – Almond Delight Pack");
			    product3.put("Product Type", "Group");
			    product3.put("Stock", "85");
			    product3.put("Product Price (AUD)", "47.00");
			    product3.put("Pack of", "Pack of 10");                         // not needed for Single
			    product3.put("Discount Type", "Fixed Discount");
			    product3.put("Discount %", "");                      // not needed for Fixed
			    product3.put("Discount Price (AUD)", "4.00");
			    product3.put("Short Description", "Exotic mango cream flavour, ideal for smoothies, cakes, and party desserts.");
			    product3.put("Image Path", Dynamic_file_path + "almond_delight.png");

			    TreeMap<String, String> product4 = new TreeMap<>();
			    product4.put("Product Name", "Whippitz N₂O Cylinder – Hazelnut Cream Mega Pack");
			    product4.put("Product Type", "Group");
			    product4.put("Stock", "35");
			    product4.put("Product Price (AUD)", "210.00");
			    product4.put("Pack of", "Pack of 12");               // shown only for Group
			    product4.put("Discount Type", "Percentage Discount");
			    product4.put("Discount %", "18");                    // % field appears
			    product4.put("Discount Price (AUD)", "200.00");            // AUD field disabled/ignored
			    product4.put("Short Description", "Rich hazelnut cream cylinders in a value mega pack for bakeries and dessert studios.");
			    product4.put("Image Path", Dynamic_file_path + "mocha_fudge.png");

			    return new Object[][] {
			        { product1 },
			        { product2 },
			        { product3 },
			        { product4 } 
			    };
		    }
		
		
		
		public void Astrix_mandatory_count_checker(String productType) throws InterruptedException{
			
			  Product_Module_locaters p = new Product_Module_locaters(d);	
			  Generic_codes g = new Generic_codes(d);
			
			  if(productType.equals("Group")){
				  Select s1 = new Select(p.Product_type_dropdowns());
				  s1.selectByVisibleText(productType);
				  p.form_submit_button().click();
				  int Error_Mesage_count = p.error_messages().size();
				  Thread.sleep(800);
				  g.Scroll_into_view(p.Top_oftheForm());
				  Thread.sleep(800);
				  p.Asteriks();
				  int Asterisk_count = p.Asteriks().size();
				  Listen.Print_in_Report().log(Status.INFO,Error_Mesage_count==Asterisk_count ? "Testcase Passed ErrorMessage Count "+Error_Mesage_count+" is Equals to Astrix Count "+Asterisk_count: "Testcase Fail ErrorMessage Count "+Error_Mesage_count+" NOT Equals to Astrix Count "+Asterisk_count);
				  
			  }
			  else {
			  p.form_submit_button().click();
			  int Error_Mesage_count = p.error_messages().size();
			  Thread.sleep(800);
			  g.Scroll_into_view(p.Top_oftheForm());
			  Thread.sleep(800);
			  p.Asteriks();
			  int Asterisk_count = p.Asteriks().size();
			  Listen.Print_in_Report().log(Status.INFO,Error_Mesage_count==Asterisk_count ? "Testcase Passed ErrorMessage Count "+Error_Mesage_count+" is Equals to Astrix Count "+Asterisk_count: "Testcase Fail ErrorMessage Count "+Error_Mesage_count+" NOT Equals to Astrix Count "+Asterisk_count);
			
			  }
			
			
		}
		
		
	
	}
	


