package Whippets.com.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.openqa.selenium.JavascriptExecutor;
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
	    	String Productname = products.size()>1 ? products.get(2) :products.get(0);
   	        p.search_box().sendKeys(Product_name);
   	        Thread.sleep(800);
   	     if(p.Second_column().get(0)!=null) {
   	        Listen.Print_in_Report().log(Status.INFO,Productname.equalsIgnoreCase(p.Second_column().get(0).getText().trim())? "Testcase Passsed Search Working":"Testcase Failed Search not working");
   	     }else{Listen.Print_in_Report().log(Status.INFO, "Product May have Already Been Deleted");}}catch(Exception mmo) {
	    	 product_list_data_collector();
	    	 String Productname = products.size()>1 ? products.get(2) :products.get(0);
	    	 p.search_box().sendKeys(Product_name);
	    	 Thread.sleep(800);
	    	 if(p.Second_column().get(0)!=null) {
	    	 Listen.Print_in_Report().log(Status.INFO,Productname.equalsIgnoreCase(p.Second_column().get(0).getText().trim()) ? "Testcase Passsed Search Working":"Testcase Failed Search not working");
	    	 }else{Listen.Print_in_Report().log(Status.INFO, "Product May have Already Been Deleted");}}
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
		  p.product_toast();
		  Listen.Print_in_Report().log(Status.INFO,p.product_toast().getText());
		
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
			    product1.put("Product Name", "Whippitz N₂O Cylinder – Blueberry Frost");
			    product1.put("Product Type", "Group");
			    product1.put("Stock", "95");
			    product1.put("Product Price (AUD)", "155.00");
			    product1.put("Pack of", "Pack of 6");
			    product1.put("Discount Type", "Fixed Discount");
			    product1.put("Discount %", "");
			    product1.put("Discount Price (AUD)", "12.00");
			    product1.put("Short Description", "Cool blueberry cream flavour, perfect for smoothies and summer desserts.");
			    product1.put("Image Path", Dynamic_file_path + "Blueberry Frost.png");

			    TreeMap<String, String> product2 = new TreeMap<>();
			    product2.put("Product Name", "Whippitz N₂O Cylinder – Caramel Mocha Royale");
			    product2.put("Product Type", "Group");
			    product2.put("Stock", "60");
			    product2.put("Product Price (AUD)", "178.00");
			    product2.put("Pack of", "Pack of 8");
			    product2.put("Discount Type", "Percentage Discount");
			    product2.put("Discount %", "15");
			    product2.put("Discount Price (AUD)", "26.70");
			    product2.put("Short Description", "Caramel mocha infusion, ideal for coffee bars and bakery frosting.");
			    product2.put("Image Path", Dynamic_file_path + "Caramel Mocha Royale.png");

			    TreeMap<String, String> product3 = new TreeMap<>();
			    product3.put("Product Name", "Whippitz N₂O Cylinder – Tropical Mango Rush");
			    product3.put("Product Type", "Group");
			    product3.put("Stock", "88");
			    product3.put("Product Price (AUD)", "165.00");
			    product3.put("Pack of", "Pack of 10");
			    product3.put("Discount Type", "Fixed Discount");
			    product3.put("Discount %", "");
			    product3.put("Discount Price (AUD)", "15.00");
			    product3.put("Short Description", "Exotic tropical mango cream, perfect for icy drinks and party desserts.");
			    product3.put("Image Path", Dynamic_file_path + "Tropical Mango Rush.png");

			    TreeMap<String, String> product4 = new TreeMap<>();
			    product4.put("Product Name", "Whippitz N₂O Cylinder – Hazelnut Praline Supreme");
			    product4.put("Product Type", "Group");
			    product4.put("Stock", "42");
			    product4.put("Product Price (AUD)", "220.00");
			    product4.put("Pack of", "Pack of 12");
			    product4.put("Discount Type", "Percentage Discount");
			    product4.put("Discount %", "20");
			    product4.put("Discount Price (AUD)", "44.00");
			    product4.put("Short Description", "Premium hazelnut praline flavour, designed for luxury dessert lounges.");
			    product4.put("Image Path", Dynamic_file_path + "Hazelnut Praline Supreme.png");

			    return new Object[][] {
			        { product1 },
			        { product2 },
			        { product3 },
			        { product4 }
			    };
		    }
		
		  
		  @DataProvider
		    public Object[][] getProductEditData() {
		        
			    
			  
			  
			  String Dynamic_file_path = System.getProperty("user.dir") + "//ProductImages//";

			  TreeMap<String, String> product1 = new TreeMap<>();
			    product1.put("Product Name", "Whippitz N₂O Cylinder – Raspberry Velvet");
			    product1.put("Product Type", "Group");
			    product1.put("Stock", "78");
			    product1.put("Product Price (AUD)", "160.00");
			    product1.put("Pack of", "Pack of 6");
			    product1.put("Discount Type", "Fixed Discount");
			    product1.put("Discount %", "");
			    product1.put("Discount Price (AUD)", "10.00");
			    product1.put("Short Description", "Smooth raspberry cream flavour, ideal for chilled desserts and frappes.");
			    product1.put("Image Path", Dynamic_file_path + "Raspberry Velvet.png");

			    TreeMap<String, String> product2 = new TreeMap<>();
			    product2.put("Product Name", "Whippitz N₂O Cylinder – Mocha Almond Crush");
			    product2.put("Product Type", "Group");
			    product2.put("Stock", "65");
			    product2.put("Product Price (AUD)", "175.00");
			    product2.put("Pack of", "Pack of 8");
			    product2.put("Discount Type", "Percentage Discount");
			    product2.put("Discount %", "14");
			    product2.put("Discount Price (AUD)", "24.50");
			    product2.put("Short Description", "Coffee mocha with almond fusion, great for bakeries and gourmet beverages.");
			    product2.put("Image Path", Dynamic_file_path + "Mocha Almond Crush.png");

			    TreeMap<String, String> product3 = new TreeMap<>();
			    product3.put("Product Name", "Whippitz N₂O Cylinder – Kiwi Lime Spark");
			    product3.put("Product Type", "Group");
			    product3.put("Stock", "92");
			    product3.put("Product Price (AUD)", "152.00");
			    product3.put("Pack of", "Pack of 10");
			    product3.put("Discount Type", "Fixed Discount");
			    product3.put("Discount %", "");
			    product3.put("Discount Price (AUD)", "9.00");
			    product3.put("Short Description", "Refreshing kiwi and lime blend, perfect for fruit tarts and tropical drinks.");
			    product3.put("Image Path", Dynamic_file_path + "Kiwi Lime Spark.png");

			    TreeMap<String, String> product4 = new TreeMap<>();
			    product4.put("Product Name", "Whippitz N₂O Cylinder – Peanut Butter Indulgence");
			    product4.put("Product Type", "Group");
			    product4.put("Stock", "48");
			    product4.put("Product Price (AUD)", "225.00");
			    product4.put("Pack of", "Pack of 12");
			    product4.put("Discount Type", "Percentage Discount");
			    product4.put("Discount %", "19");
			    product4.put("Discount Price (AUD)", "42.75");
			    product4.put("Short Description", "Rich peanut butter cream for premium cafés and dessert lounges.");
			    product4.put("Image Path", Dynamic_file_path + "Peanut Butter Indulgence.png");

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
			
			  }}
		
		
		public void product_edit_form_astrix_checker() throws InterruptedException{
			
			Product_Module_locaters p = new Product_Module_locaters(d);	
			Generic_codes g = new Generic_codes(d);
			
			  p.form_submit_button().click();
			  int Error_Mesage_count = p.error_messages().size();
			  Thread.sleep(800);
			  g.Scroll_into_view(p.Top_oftheForm());
			  Thread.sleep(800);
			  p.Asteriks();
			  int Asterisk_count = p.Asteriks().size();
			  Listen.Print_in_Report().log(Status.INFO,Error_Mesage_count==Asterisk_count ? "Testcase Passed ErrorMessage Count "+Error_Mesage_count+" is Equals to Astrix Count "+Asterisk_count: "Testcase Fail ErrorMessage Count "+Error_Mesage_count+" NOT Equals to Astrix Count "+Asterisk_count);
			
			
			
			
		}
		
		
		  
		   @Test(dataProvider="getProductEditData")
		   public void product_Edit(TreeMap<String,String> data) throws IOException, InterruptedException{
			   
			   Product_Module_locaters p = new Product_Module_locaters(d);	
			   String ProductType = String.valueOf(data.get("Product Type")); 
			   JavascriptExecutor js = (JavascriptExecutor)d;
			   
			   
			   
			   
			   Product_list_Accessor();
			   p.Edit_buttons().get(1).click();
			   p.Add_Edit_form();
			   js.executeScript("arguments[0].scrollIntoView(true);",p.image_upload_field());
			   p.image_upload_field().sendKeys(String.valueOf(data.get("Image Path")));
			   js.executeScript("arguments[0].scrollIntoView(true);",p.form_submit_button());
			   Thread.sleep(800);
			   p.form_submit_button().click();
			   Listen.Print_in_Report().log(Status.INFO, p.product_toast().getText());
			   p.search_box();
			   p.Edit_buttons().get(0).click();
			   p.Add_Edit_form();
			   p.filled_input_feilds().get(0).clear();
			   product_edit_form_astrix_checker();
			   Thread.sleep(800);
			   d.navigate().refresh();
			   Thread.sleep(800);
			   p.filled_input_feilds().get(0).clear();
			   js.executeScript("arguments[0].scrollIntoView(true);",p.form_submit_button());
			   Thread.sleep(800);
			   p.form_submit_button().click();
			   js.executeScript("arguments[0].scrollIntoView(true);",p.input_fields().get(0));
			   p.input_fields().get(0).sendKeys(data.get("Product Name"));
			   Thread.sleep(800);
			   js.executeScript("arguments[0].scrollIntoView(true);",p.form_submit_button());
			   Thread.sleep(800);
			   p.form_submit_button().click();
			   Thread.sleep(800);
			   p.product_toast();
			   Listen.Print_in_Report().log(Status.INFO, p.product_toast().getText());
			   p.search_box();
			   p.Edit_buttons().get(0).click();
			   p.Add_Edit_form();
			   p.filled_input_feilds().get(1).clear();
			   p.filled_input_feilds().get(2).clear();
			   product_edit_form_astrix_checker();
			   p.input_fields().get(0).sendKeys(data.get("Stock"));
			   p.input_fields().get(1).sendKeys(data.get("Product Price (AUD)"));
			   js.executeScript("arguments[0].scrollIntoView(true);",p.form_submit_button());
			   Thread.sleep(800);
			   p.form_submit_button().click();
			   Thread.sleep(800);
			   Listen.Print_in_Report().log(Status.INFO, p.product_toast().getText());
			   p.search_box();
		   }
		
		
	
	}
	


