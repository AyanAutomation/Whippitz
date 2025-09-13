package Whippets.com.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;
import Listeners_Reports.Listen;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Listeners_Reports.Reports;
import Locaters.Customer_module_locaters;
import Locaters.Login_locaters;
import Locaters.Product_Module_locaters;
import Repeative_codes.Generic_codes;


@Listeners(Listeners_Reports.Listen.class)
public class Customer_Module extends Product_module{
	
	
	TreeMap<String, String> customerNames_phnumbers = new TreeMap<String, String>();
	TreeMap<String, String> customerNames_Emails = new TreeMap<String, String>();
	TreeMap<String, String> customerNames_Statuses = new TreeMap<String, String>();
	List <String> customers_names = new ArrayList<String>();
	List <String> customers_phones = new ArrayList<String>();
	List <String> customers_Emails= new ArrayList<String>();
	List <String> customers_Statuses= new ArrayList<String>();
	String Customer_MailID;
	String Customer_Phone_Number; 
	
	@Test
	public void customer_list_Accessor() throws IOException, InterruptedException{
		
		Customer_module_locaters p = new Customer_module_locaters(d);
		Product_Module_locaters po = new Product_Module_locaters(d);
		
		menu_Accessor("Customers","All Customers");
		p.Landed_on_customer_list();
		po.select_dropdown();
		Select s = new Select(po.select_dropdown());
		s.selectByVisibleText("100");
		Thread.sleep(800);}
	
	
	
	@Test
	public void customer_List_Data_Collector() throws IOException, InterruptedException{
		
		Customer_module_locaters p = new Customer_module_locaters(d);
	
		
		customer_list_Accessor();
		p.customer_list();
		allList_clear();
		List <WebElement> customers_name_element = p.names();
		List <WebElement> customers_phones_element = p.Customer_phnums();
		List <WebElement> customers_Emails_element = p.Customer_email();
		List <WebElement> customers_Statuses_element = p.Customer_Status();
		IntStream.range(0, customers_name_element.size()).forEach(i->{
			customers_names.add(customers_name_element.get(i).getText());
			customers_phones.add(customers_phones_element.get(i).getText());
			customers_Emails.add(customers_Emails_element.get(i).getText());
			customers_Statuses.add(customers_Statuses_element.get(i).getText());
			});
		if(customers_names.size()==customers_phones.size()&&customers_Emails.size()==customers_Statuses.size()&&customers_Emails.size()==customers_names.size()){
			IntStream.range(0,customers_names.size()).forEach(k->{
				customerNames_phnumbers.put(customers_names.get(k), customers_phones.get(k));
				customerNames_Emails.put(customers_names.get(k), customers_Emails.get(k));
				customerNames_Statuses.put(customers_names.get(k), customers_Statuses.get(k));});
		      }}
	
	
	
	public void allList_clear(){
		
		customers_names.clear();
		customers_phones.clear();
		customers_Emails.clear();
		customers_Statuses.clear();
		customerNames_phnumbers.clear();
		customerNames_Emails.clear();
		customerNames_Statuses.clear();
		}
    @Test(dataProvider="customerData")
	public void customer_add(TreeMap<String, String> customerData) throws IOException, InterruptedException{
		
		Customer_module_locaters p = new Customer_module_locaters(d);
		JavascriptExecutor js = (JavascriptExecutor)d;
		
		
	    Customer_MailID = customerData.get("Email");
		Customer_Phone_Number = customerData.get("Phone"); 
		menu_Accessor("Customers","Add Customer");
		for(Map.Entry<String, String> pair:customerNames_phnumbers.entrySet()){
			Listen.Print_in_Report().log(Status.INFO,pair.getKey()+"  "+pair.getValue());
			}
		Customer_mandatory_field_Checker();
		p.first_name().sendKeys(customerData.get("First Name"));
		p.last_name().sendKeys(customerData.get("Last Name"));
		p.email().sendKeys(Customer_MailID);
		p.phone().sendKeys(Customer_Phone_Number);
		Select s = new Select(p.Status_select_dropdown());
		s.selectByVisibleText(customerData.get("Status"));
		p.Address_Autocomplete_feild().click();
		Thread.sleep(800);
		p.Address_Autocomplete_feild().sendKeys(customerData.get("Address"));
		Thread.sleep(800);
		Generic_codes.commitAutocomplete( 
		d,p.Address_Autocomplete_feild(), p.latitudeInput(), p.longitudeInput(), customerData.get("Address"),15);
		Thread.sleep(800);
		p.Address_Autocomplete_feild().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(800);
		js.executeScript("arguments[0].scrollIntoView(true);",p.Image_upload_field());
		p.Image_upload_field().sendKeys(customerData.get("Profile Image"));
		Thread.sleep(800);
        js.executeScript("arguments[0].scrollIntoView(true);",p.submit_button());
        p.submit_button().click();
        try {
        p.Success_toast();
        String toast = p.Success_toast().getText();
        Listen.Print_in_Report().log(Status.INFO,toast);
        if(toast.contains("The email has already been taken.")){   
        	customer_add_edit_form_email_validation();
        	String Toast_two= p.Success_toast().getText();
        	Listen.Print_in_Report().log(Status.INFO,Toast_two);
            try{if(Toast_two.contains("The phone has already been taken.")){
        		customer_add_edit_form_Phone_validation();
        	}}catch(Exception mkl){
        	Listen.Print_in_Report().log(Status.INFO,"Success Toast is not locateble");
            }}}catch(Exception mko){
            Listen.Print_in_Report().log(Status.INFO,"Success Toast is not locateble");
            }
       
    }
    
	
   
    @DataProvider
    public Object[][] customerData() {

    	TreeMap<String, String> c1 = new TreeMap<>();
    	c1.put("First Name", "Ivan");     c1.put("Last Name", "Petrov");
    	c1.put("Email", "ivan.petrov321@yopmail.com");
    	c1.put("Phone", "+61-412345678"); c1.put("Status", "Active");
    	c1.put("Address", "21 Pushkin Street, Moscow");
    	c1.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\ivan.png");

    	TreeMap<String, String> c2 = new TreeMap<>();
    	c2.put("First Name", "Olga");     c2.put("Last Name", "Sokolova");
    	c2.put("Email", "olga.sokolova982@yopmail.com");
    	c2.put("Phone", "+61-498765432"); c2.put("Status", "Inactive");
    	c2.put("Address", "55 Nevsky Prospect, St. Petersburg");
    	c2.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\olga.png");

    	TreeMap<String, String> c3 = new TreeMap<>();
    	c3.put("First Name", "Dmitry");   c3.put("Last Name", "Volkov");
    	c3.put("Email", "dmitry.volkov753@yopmail.com");
    	c3.put("Phone", "+61-487654321"); c3.put("Status", "Active");
    	c3.put("Address", "88 Arbat Street, Moscow");
    	c3.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\dmitry.png");

    	TreeMap<String, String> c4 = new TreeMap<>();
    	c4.put("First Name", "Natalia");  c4.put("Last Name", "Smirnova");
    	c4.put("Email", "natalia.smirnova604@yopmail.com");
    	c4.put("Phone", "+61-423456789"); c4.put("Status", "Inactive");
    	c4.put("Address", "14 Tverskaya Street, Moscow");
    	c4.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\natalia.png");

    	TreeMap<String, String> c5 = new TreeMap<>();
    	c5.put("First Name", "Sergey");   c5.put("Last Name", "Ivanov");
    	c5.put("Email", "sergey.ivanov871@yopmail.com");
    	c5.put("Phone", "+61-434567890"); c5.put("Status", "Active");
    	c5.put("Address", "32 Kirov Avenue, Kazan");
    	c5.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\sergey.png");

    	TreeMap<String, String> c6 = new TreeMap<>();
    	c6.put("First Name", "Elena");    c6.put("Last Name", "Kuznetsova");
    	c6.put("Email", "elena.kuznetsova229@yopmail.com");
    	c6.put("Phone", "+61-445678901"); c6.put("Status", "Inactive");
    	c6.put("Address", "19 Fontanka Embankment, St. Petersburg");
    	c6.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\elena.png");

    	TreeMap<String, String> c7 = new TreeMap<>();
    	c7.put("First Name", "Andrei");   c7.put("Last Name", "Morozov");
    	c7.put("Email", "andrei.morozov557@yopmail.com");
    	c7.put("Phone", "+61-456789012"); c7.put("Status", "Active");
    	c7.put("Address", "77 Bauman Street, Kazan");
    	c7.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\andrei.png");

    	TreeMap<String, String> c8 = new TreeMap<>();
    	c8.put("First Name", "Tatiana");  c8.put("Last Name", "Orlova");
    	c8.put("Email", "tatiana.orlova118@yopmail.com");
    	c8.put("Phone", "+61-467890123"); c8.put("Status", "Inactive");
    	c8.put("Address", "5 Lenin Square, Novosibirsk");
    	c8.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\tatiana.png");

    	TreeMap<String, String> c9 = new TreeMap<>();
    	c9.put("First Name", "Mikhail");  c9.put("Last Name", "Egorov");
    	c9.put("Email", "mikhail.egorov442@yopmail.com");
    	c9.put("Phone", "+61-478901234"); c9.put("Status", "Active");
    	c9.put("Address", "42 Gorky Street, Yekaterinburg");
    	c9.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\mikhail.png");

    	TreeMap<String, String> c10 = new TreeMap<>();
    	c10.put("First Name", "Anna");    c10.put("Last Name", "Pavlova");
    	c10.put("Email", "anna.pavlova905@yopmail.com");
    	c10.put("Phone", "+61-489012345"); c10.put("Status", "Inactive");
    	c10.put("Address", "8 Sovetskaya Street, Rostov-on-Don");
    	c10.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\anna.png");


        return new Object[][] {
            { c1 }, { c2 }, { c3 }, { c4 }, { c5 },
            { c6 }, { c7 }, { c8 }, { c9 }, { c10 } 
        };}
    
    
     @Test(dataProvider="customerData")
      public void added_customer_delete(TreeMap<String, String> customerData) throws IOException, InterruptedException{
    	  
    	 Customer_module_locaters p = new Customer_module_locaters(d);
    	 Product_Module_locaters pm = new Product_Module_locaters(d);
    	 JavascriptExecutor js = (JavascriptExecutor)d;
    	 
    	 
    	 menu_Accessor("Customers","All Customers");
    	 String Customer_fullname = customerData.get("First Name")+" "+customerData.get("Last Name");
    	 p.customer_list_filter_inputs().get(0).sendKeys(Customer_fullname);
    	 Thread.sleep(800);try {
    	 if(p.names().get(0).getText().contains(Customer_fullname)){
    		 Listen.Print_in_Report().log(Status.INFO, "Added user found Proceeding to delete");
    		 js.executeScript("arguments[0].click();",  pm.Delete_buttons().get(0));
    		 pm.Delete_popup();
    		 Thread.sleep(400);
			 pm.Delete_button().click();
			 p.Success_toast();
			 Listen.Print_in_Report().log(Status.INFO,p.Success_toast().getText());
		     }}
    	 catch(Exception del){
    	 Listen.Print_in_Report().log(Status.INFO, "User Already Deleted Thereby Moving to Next user");
         }}
     
     
     public void Customer_mandatory_field_Checker() throws InterruptedException{
    	 
    	 Product_Module_locaters p = new Product_Module_locaters(d);	
		 Generic_codes g = new Generic_codes(d);
    	 
		  p.form_submit_button().click();
		  int Error_Mesage_count = p.error_messages().size();
		  Thread.sleep(800);
		  g.Scroll_into_view(p.Top_oftheForm());
		  Thread.sleep(800);
		  p.Asteriks();
		  int Asterisk_count = p.Asteriks().size();
		  Listen.Print_in_Report().log(Status.INFO,Error_Mesage_count==Asterisk_count ? " Testcase Passed ErrorMessage Count "+Error_Mesage_count+" is Equals to Astrix Count "+Asterisk_count: " Testcase Fail Error Message Count "+Error_Mesage_count+" NOT Equals to Astrix Count "+Asterisk_count);
		  }
    
     
     
       public void customer_add_edit_form_email_validation(){
    	   
    	   JavascriptExecutor js = (JavascriptExecutor)d;
    	   Customer_module_locaters p = new Customer_module_locaters(d);
    	   
    	   
    	   Listen.Print_in_Report().log(Status.INFO,"Email Feild Unique Validation is present");
    	   StringBuffer bf = new StringBuffer(Customer_MailID);
           bf.replace(12, 14, "102");
           js.executeScript("arguments[0].scrollIntoView(true);",p.email());
           p.email().clear();
           p.email().sendKeys(bf);
           js.executeScript("arguments[0].scrollIntoView(true);",p.email());
           js.executeScript("arguments[0].scrollIntoView(true);",p.submit_button());
           p.submit_button().click();
           
           }
       
       public void customer_add_edit_form_Phone_validation(){
    	   
    	   JavascriptExecutor js = (JavascriptExecutor)d;
    	   Customer_module_locaters p = new Customer_module_locaters(d);
    	   
    	   
    	   Listen.Print_in_Report().log(Status.INFO,"Phone number Feild Unique Validation is present");
    	   StringBuffer bf = new StringBuffer(Customer_Phone_Number);
           bf.replace(5, 8, "010");
           js.executeScript("arguments[0].scrollIntoView(true);",p.phone());
           p.phone().clear();
           p.phone().sendKeys(bf);
           js.executeScript("arguments[0].scrollIntoView(true);",p.submit_button());
           p.submit_button().click();
         }
    
    
    
	
}
