package Whippets.com.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners_Reports.Listen;
import Locaters.Dashboard_Locaters;
import Locaters.Driver_Module_locaters;
import Locaters.Login_locaters;
import Locaters.Product_Module_locaters;
import Repeative_codes.Generic_codes;

@Listeners(Listeners_Reports.Listen.class)
public class Product_module extends Side_Menu_options_Accessor{
	
	List<String> products = new ArrayList<String>(); 
	TreeSet<String> Uniqueproductset = new TreeSet<String>();
	List<String> duplicates = new ArrayList<String>();
	List<String> variations = new ArrayList<String>();
	TreeMap<String,Integer> variation_stock = new TreeMap<String,Integer>();
	
	
	
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
			    product1.put("Product Name", "Whippitz N₂O Cylinder – Coconut Cream Breeze");
			    product1.put("Product Type", "Group");
			    product1.put("Stock", "72");
			    product1.put("Product Price (AUD)", "158.00");
			    product1.put("Pack of", "Pack of 6");
			    product1.put("Discount Type", "Fixed Discount");
			    product1.put("Discount %", "");
			    product1.put("Discount Price (AUD)", "11.00");
			    product1.put("Short Description", "Silky coconut cream profile tailored for summer sundaes, pies, and tropical shakes.");
			    product1.put("Image Path", Dynamic_file_path + "Coconut Cream Breeze.png");

			    TreeMap<String, String> product2 = new TreeMap<>();
			    product2.put("Product Name", "Whippitz N₂O Cylinder – Lemon Meringue Zest");
			    product2.put("Product Type", "Group");
			    product2.put("Stock", "64");
			    product2.put("Product Price (AUD)", "162.00");
			    product2.put("Pack of", "Pack of 8");
			    product2.put("Discount Type", "Percentage Discount");
			    product2.put("Discount %", "12");
			    product2.put("Discount Price (AUD)", "19.44"); // 12% of 162.00
			    product2.put("Short Description", "Bright lemon-vanilla balance ideal for tarts, pavlovas, and iced desserts.");
			    product2.put("Image Path", Dynamic_file_path + "Lemon Meringue Zest.png");

			    TreeMap<String, String> product3 = new TreeMap<>();
			    product3.put("Product Name", "Whippitz N₂O Cylinder – Cherry Cheesecake Dream");
			    product3.put("Product Type", "Group");
			    product3.put("Stock", "88");
			    product3.put("Product Price (AUD)", "170.00");
			    product3.put("Pack of", "Pack of 10");
			    product3.put("Discount Type", "Fixed Discount");
			    product3.put("Discount %", "");
			    product3.put("Discount Price (AUD)", "9.00");
			    product3.put("Short Description", "Lush cherry notes layered over creamy cheesecake—made for patisseries and cafés.");
			    product3.put("Image Path", Dynamic_file_path + "Cherry Cheesecake Dream.png");

			    TreeMap<String, String> product4 = new TreeMap<>();
			    product4.put("Product Name", "Whippitz N₂O Cylinder – Salted Caramel Crunch");
			    product4.put("Product Type", "Group");
			    product4.put("Stock", "54");
			    product4.put("Product Price (AUD)", "190.00");
			    product4.put("Pack of", "Pack of 12");
			    product4.put("Discount Type", "Percentage Discount");
			    product4.put("Discount %", "15");
			    product4.put("Discount Price (AUD)", "28.50"); // 15% of 190.00
			    product4.put("Short Description", "Decadent caramel with a subtle sea-salt finish—perfect for waffles and bakes.");
			    product4.put("Image Path", Dynamic_file_path + "Salted Caramel Crunch.png");

			    TreeMap<String, String> product5 = new TreeMap<>();
			    product5.put("Product Name", "Whippitz N₂O Cylinder – Matcha Green Velvet");
			    product5.put("Product Type", "Group");
			    product5.put("Stock", "76");
			    product5.put("Product Price (AUD)", "168.00");
			    product5.put("Pack of", "Pack of 6");
			    product5.put("Discount Type", "Fixed Discount");
			    product5.put("Discount %", "");
			    product5.put("Discount Price (AUD)", "10.00");
			    product5.put("Short Description", "Elegant matcha cream body for modern pastries, crepes, and beverage bars.");
			    product5.put("Image Path", Dynamic_file_path + "Matcha Green Velvet.png");

			    TreeMap<String, String> product6 = new TreeMap<>();
			    product6.put("Product Name", "Whippitz N₂O Cylinder – Blue Raspberry Glacier");
			    product6.put("Product Type", "Group");
			    product6.put("Stock", "92");
			    product6.put("Product Price (AUD)", "172.00");
			    product6.put("Pack of", "Pack of 8");
			    product6.put("Discount Type", "Percentage Discount");
			    product6.put("Discount %", "10");
			    product6.put("Discount Price (AUD)", "17.20"); // 10% of 172.00
			    product6.put("Short Description", "Crisp blue-raspberry chill for frostings, frappes, and summer pop-ups.");
			    product6.put("Image Path", Dynamic_file_path + "Blue Raspberry Glacier.png");

			    return new Object[][] {
			    	{ product1 },
			        { product2 },
			        { product3 },
			        { product4 },
			        { product5 },
			        { product6 }
			    };
		    }
		
		  
		  @DataProvider
		    public Object[][] getProductEditData() {
		        
			    
			  
			  
			  String Dynamic_file_path = System.getProperty("user.dir") + "//ProductImages//";

			  TreeMap<String, String> product1 = new TreeMap<>();
			    product1.put("Product Name", "Whippitz N₂O Cylinder – Coconut Cream Breeze");
			    product1.put("Product Type", "Group");
			    product1.put("Stock", "72");
			    product1.put("Product Price (AUD)", "158.00");
			    product1.put("Pack of", "Pack of 6");
			    product1.put("Discount Type", "Fixed Discount");
			    product1.put("Discount %", "");
			    product1.put("Discount Price (AUD)", "11.00");
			    product1.put("Short Description", "Silky coconut cream profile tailored for summer sundaes, pies, and tropical shakes.");
			    product1.put("Image Path", Dynamic_file_path + "Coconut Cream Breeze.png");

			    TreeMap<String, String> product2 = new TreeMap<>();
			    product2.put("Product Name", "Whippitz N₂O Cylinder – Lemon Meringue Zest");
			    product2.put("Product Type", "Group");
			    product2.put("Stock", "64");
			    product2.put("Product Price (AUD)", "162.00");
			    product2.put("Pack of", "Pack of 8");
			    product2.put("Discount Type", "Percentage Discount");
			    product2.put("Discount %", "12");
			    product2.put("Discount Price (AUD)", "19.44"); // 12% of 162.00
			    product2.put("Short Description", "Bright lemon-vanilla balance ideal for tarts, pavlovas, and iced desserts.");
			    product2.put("Image Path", Dynamic_file_path + "Lemon Meringue Zest.png");

			    TreeMap<String, String> product3 = new TreeMap<>();
			    product3.put("Product Name", "Whippitz N₂O Cylinder – Cherry Cheesecake Dream");
			    product3.put("Product Type", "Group");
			    product3.put("Stock", "88");
			    product3.put("Product Price (AUD)", "170.00");
			    product3.put("Pack of", "Pack of 10");
			    product3.put("Discount Type", "Fixed Discount");
			    product3.put("Discount %", "");
			    product3.put("Discount Price (AUD)", "9.00");
			    product3.put("Short Description", "Lush cherry notes layered over creamy cheesecake—made for patisseries and cafés.");
			    product3.put("Image Path", Dynamic_file_path + "Cherry Cheesecake Dream.png");

			    TreeMap<String, String> product4 = new TreeMap<>();
			    product4.put("Product Name", "Whippitz N₂O Cylinder – Salted Caramel Crunch");
			    product4.put("Product Type", "Group");
			    product4.put("Stock", "54");
			    product4.put("Product Price (AUD)", "190.00");
			    product4.put("Pack of", "Pack of 12");
			    product4.put("Discount Type", "Percentage Discount");
			    product4.put("Discount %", "15");
			    product4.put("Discount Price (AUD)", "28.50"); // 15% of 190.00
			    product4.put("Short Description", "Decadent caramel with a subtle sea-salt finish—perfect for waffles and bakes.");
			    product4.put("Image Path", Dynamic_file_path + "Salted Caramel Crunch.png");

			    TreeMap<String, String> product5 = new TreeMap<>();
			    product5.put("Product Name", "Whippitz N₂O Cylinder – Matcha Green Velvet");
			    product5.put("Product Type", "Group");
			    product5.put("Stock", "76");
			    product5.put("Product Price (AUD)", "168.00");
			    product5.put("Pack of", "Pack of 6");
			    product5.put("Discount Type", "Fixed Discount");
			    product5.put("Discount %", "");
			    product5.put("Discount Price (AUD)", "10.00");
			    product5.put("Short Description", "Elegant matcha cream body for modern pastries, crepes, and beverage bars.");
			    product5.put("Image Path", Dynamic_file_path + "Matcha Green Velvet.png");

			    TreeMap<String, String> product6 = new TreeMap<>();
			    product6.put("Product Name", "Whippitz N₂O Cylinder – Blue Raspberry Glacier");
			    product6.put("Product Type", "Group");
			    product6.put("Stock", "92");
			    product6.put("Product Price (AUD)", "172.00");
			    product6.put("Pack of", "Pack of 8");
			    product6.put("Discount Type", "Percentage Discount");
			    product6.put("Discount %", "10");
			    product6.put("Discount Price (AUD)", "17.20"); // 10% of 172.00
			    product6.put("Short Description", "Crisp blue-raspberry chill for frostings, frappes, and summer pop-ups.");
			    product6.put("Image Path", Dynamic_file_path + "Blue Raspberry Glacier.png");

			    return new Object[][] {
			        { product1 },
			        { product2 },
			        { product3 },
			        { product4 },
			        { product5 },
			        { product6 }
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
			   p.Edit_buttons().get(3).click();
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
		
		   
		   
		   @Test
		   public TreeMap<String,Integer> product_Variations_count_collector() throws IOException, InterruptedException{
			   
		   Product_Module_locaters p = new Product_Module_locaters(d);	   
		   Driver_Module_locaters dr = new Driver_Module_locaters(d);
			   
		   variations.clear();
		   variation_stock.clear();
		   menu_Accessor("Products","Variation List");
		   p.variation_list_title();
		   List <WebElement> variation_name_weblement = dr.second_column();
		   for(WebElement variation_name:variation_name_weblement ){
			  variations.add(variation_name.getText().trim());}
		   
		   Product_list_Accessor(); 
		   
		   for(int m=0; m<variations.size();m++) {
		   p.search_box().clear();	   
		   p.search_box().sendKeys(variations.get(m));
		   Thread.sleep(800);
		   List<WebElement> resultrows= p.rows();
		   int total=0;
		   for(WebElement resultrow:resultrows ){
			   
			   if(resultrow.getText().trim().contains(variations.get(m))) {
			   
				   resultrow.findElement(By.xpath(".//a[2]")).click();
				   p.Add_Edit_form();
				   String stock_number = p.Stock_field_in_view_form().getAttribute("value");
				   total=total+Integer.parseInt(stock_number);
				   d.navigate().back();
				   p.search_box();
			   }
			   }
		   variation_stock.put(variations.get(m), total);
		   }
		   
		   for(Map.Entry<String,Integer> pair:variation_stock.entrySet()){
			   
			   Listen.Print_in_Report().log(Status.INFO, pair.getKey()+" :--- "+pair.getValue());
			   System.out.println(pair.getKey()+" :--- "+pair.getValue());
			   System.out.println();
			   
		   }
		   
		   
		    return variation_stock;}
	
		   
		   }
		   
		   
		   
		   
		   
		   
		   
		
	
	
	


