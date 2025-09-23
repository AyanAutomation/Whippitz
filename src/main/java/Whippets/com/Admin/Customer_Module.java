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
		Product_Module_locaters pm = new Product_Module_locaters(d);
		
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
        Listen.Print_in_Report().log(Status.INFO, pm.error_messages().get(0).getText().trim().contains("Please enter a valid phone number") ? "Testcase passed Ph number blank feild validation present in customer add form" : "Testcase Failed Ph number blank feild validation not present in customer add form");
        js.executeScript("arguments[0].scrollIntoView(true);",p.phone());
        p.phone().sendKeys(Customer_Phone_Number);
        js.executeScript("arguments[0].scrollIntoView(true);",p.submit_button());
        p.submit_button().click();
        Thread.sleep(800); try {
        WebElement t1 =  p.Success_toast();
        String toast=null;
        if(t1!=null){
        	 
        	 toast = p.Success_toast().getText();
         }
         System.out.println(toast);
         Listen.Print_in_Report().log(Status.INFO,toast);
         if(toast.contains("The email has already been taken.")){   
        	customer_add_edit_form_email_validation();
        	String Toast_two= p.Success_toast().getText();
        	Listen.Print_in_Report().log(Status.INFO,Toast_two);
            if(Toast_two.contains("The phone has already been taken.")){
        		customer_add_edit_form_Phone_validation();
        	}}}catch(Exception mmk) {
        		Listen.Print_in_Report().log(Status.INFO, "Toast presence cannot be located");
        	}}
    
	
   
    @DataProvider
    public Object[][] customerData() {

        TreeMap<String, String> c1 = new TreeMap<>();
        c1.put("First Name", "Liam");     c1.put("Last Name", "Williams");
        c1.put("Email", "liam.williams431@yopmail.com");
        c1.put("Phone", "+61-421345678"); c1.put("Status", "Active");
        c1.put("Address", "12 George Street, Sydney, NSW");
        c1.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\Liam Williams.png");

        TreeMap<String, String> c2 = new TreeMap<>();
        c2.put("First Name", "Olivia");   c2.put("Last Name", "Brown");
        c2.put("Email", "olivia.brown527@yopmail.com");
        c2.put("Phone", "+61-422456789"); c2.put("Status", "Inactive");
        c2.put("Address", "85 Collins Street, Melbourne, VIC");
        c2.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\Olivia Brown.png");

        TreeMap<String, String> c3 = new TreeMap<>();
        c3.put("First Name", "Noah");     c3.put("Last Name", "Harris");
        c3.put("Email", "noah.harris614@yopmail.com");
        c3.put("Phone", "+61-423567890"); c3.put("Status", "Active");
        c3.put("Address", "45 Adelaide Terrace, Perth, WA");
        c3.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\Noah.png");

        TreeMap<String, String> c4 = new TreeMap<>();
        c4.put("First Name", "Isla");     c4.put("Last Name", "Mitchell");
        c4.put("Email", "isla.mitchell732@yopmail.com");
        c4.put("Phone", "+61-424678901"); c4.put("Status", "Inactive");
        c4.put("Address", "39 Elizabeth Street, Brisbane, QLD");
        c4.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\Isla.png");

        TreeMap<String, String> c5 = new TreeMap<>();
        c5.put("First Name", "Jack");     c5.put("Last Name", "Robinson");
        c5.put("Email", "jack.robinson845@yopmail.com");
        c5.put("Phone", "+61-425789012"); c5.put("Status", "Active");
        c5.put("Address", "76 Murray Street, Hobart, TAS");
        c5.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\Jack.png");

        TreeMap<String, String> c6 = new TreeMap<>();
        c6.put("First Name", "Grace");    c6.put("Last Name", "Edwards");
        c6.put("Email", "grace.edwards963@yopmail.com");
        c6.put("Phone", "+61-426890123"); c6.put("Status", "Inactive");
        c6.put("Address", "14 North Terrace, Adelaide, SA");
        c6.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\Henry.png");

        TreeMap<String, String> c7 = new TreeMap<>();
        c7.put("First Name", "Henry");    c7.put("Last Name", "Thompson");
        c7.put("Email", "henry.thompson437@yopmail.com");
        c7.put("Phone", "+61-427901234"); c7.put("Status", "Active");
        c7.put("Address", "22 Mitchell Street, Darwin, NT");
        c7.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\Thompson.png");

        TreeMap<String, String> c8 = new TreeMap<>();
        c8.put("First Name", "Chloe");    c8.put("Last Name", "White");
        c8.put("Email", "chloe.white589@yopmail.com");
        c8.put("Phone", "+61-428012345"); c8.put("Status", "Inactive");
        c8.put("Address", "9 Macquarie Street, Sydney, NSW");
        c8.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\Chloe.png");

        TreeMap<String, String> c9 = new TreeMap<>();
        c9.put("First Name", "Lucas");    c9.put("Last Name", "King");
        c9.put("Email", "lucas.king673@yopmail.com");
        c9.put("Phone", "+61-429123456"); c9.put("Status", "Active");
        c9.put("Address", "33 Swanston Street, Melbourne, VIC");
        c9.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\Lucas.png");

        TreeMap<String, String> c10 = new TreeMap<>();
        c10.put("First Name", "Ella");    c10.put("Last Name", "Parker");
        c10.put("Email", "ella.parker781@yopmail.com");
        c10.put("Phone", "+61-430234567"); c10.put("Status", "Inactive");
        c10.put("Address", "57 St Georges Terrace, Perth, WA");
        c10.put("Profile Image", System.getProperty("user.dir") + "\\Customer Photos\\Ella.png");

        return new Object[][] {
            { c1 }, { c2 }, { c3 }, { c4 }, { c5 },
            { c6 }, { c7 }, { c8 }, { c9 }, { c10 }
        };
    }
    

    
    
     @Test(dataProvider="customerData")
      public void added_customer_delete(TreeMap<String, String> customerData) throws IOException, InterruptedException{
    	  
    	 Customer_module_locaters p = new Customer_module_locaters(d);
    	 Product_Module_locaters pm = new Product_Module_locaters(d);
    	 JavascriptExecutor js = (JavascriptExecutor)d;
    	 
    	 
    	 menu_Accessor("Customers","All Customers");
    	 String Customer_mail = customerData.get("Email");;
    	 p.customer_list_filter_inputs().get(1).sendKeys(Customer_mail);
    	 Thread.sleep(800);try {
    	 if(p.Customer_email().get(0).getText().contains(Customer_mail)){
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
     
         
     @Test(dataProvider="customerData")
     public void customer_edit_check(TreeMap<String, String> customerData) throws IOException, InterruptedException{
    	 
    	 Customer_module_locaters p = new Customer_module_locaters(d);
    	 Product_Module_locaters pm = new Product_Module_locaters(d);
    	 JavascriptExecutor js = (JavascriptExecutor)d;
    	 Generic_codes gc = new Generic_codes(d);
    	 
         customer_List_Data_Collector();
    	 String Customer_mail = customerData.get("Email");
    	 String Customer_address = customerData.get("Address");
    	 customer_search_and_edit_Button_click(Customer_mail);
    	 customer_name_edit_check();
    	 customer_search_and_edit_Button_click(Customer_mail);
    	 customer_Address_edit_check();
    	 customer_search_and_edit_Button_click(Customer_mail);
    	 p.email().clear();
    	 p.email().sendKeys(customerNames_Emails.get(customerNames_Emails.lastKey()));
    	 Thread.sleep(800);
    	 js.executeScript("arguments[0].scrollIntoView(true);",p.submit_button());
         p.submit_button().click();
         gc.Move_to_element(p.Success_toast());
         String Success_toast = p.Success_toast().getText();
         Listen.Print_in_Report().log(Status.INFO,Success_toast);
         System.out.println(Success_toast);
         System.out.println();
         String[] message = Success_toast.split("The ");
         if(message[1].trim().contains("email has already been taken.")){
        	 js.executeScript("arguments[0].scrollIntoView(true);", p.email());
        	 p.email().clear();
        	 p.email().sendKeys(customerData.get("Email"));
        	 String value = p.phone().getAttribute("value");
        	if(!value.contains(customerNames_phnumbers.get(customerNames_phnumbers.firstKey()))){
        	 IntStream.range(0,10).forEach(v->{p.phone().sendKeys(Keys.BACK_SPACE);});
        	 p.phone().sendKeys("+61"+customerNames_phnumbers.get(customerNames_phnumbers.firstKey()));
        	 customer_Address_Setter(Customer_address);
        	 js.executeScript("arguments[0].scrollIntoView(true);",p.submit_button());
             p.submit_button().click();
        	 }Thread.sleep(800);
             gc.Move_to_element(p.Success_toast());
             String toast_two = p.Success_toast().getText();
             Listen.Print_in_Report().log(Status.INFO,toast_two);
             System.out.println(toast_two);
             System.out.println();
             if(toast_two.contains("The phone has already been taken.")){
            	 js.executeScript("arguments[0].click();",   p.phone());
            	 Thread.sleep(800);            	
            	 p.phone().sendKeys(customerData.get("Phone"));
            	 Thread.sleep(800); 
            	 js.executeScript("arguments[0].scrollIntoView(true);",p.submit_button());
                 p.submit_button().click();}}
                 else{
                	 Listen.Print_in_Report().log(Status.INFO, "Else Block Executed phone number validation toast not shown");
                	 System.out.println("Else Block Executed phone number validation toast not shown");
                     System.out.println(); 	 
                 }            
     }
     
     
     
     
     
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
           p.submit_button().click();}
       
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
           p.submit_button().click();}
    
    
       
       public void customer_name_edit_check(){
    	   
    	   Customer_module_locaters p = new Customer_module_locaters(d);
    	   JavascriptExecutor js = (JavascriptExecutor)d;
    	   
    	   p.first_name().sendKeys(Keys.BACK_SPACE);
    	   js.executeScript("arguments[0].scrollIntoView(true);",p.submit_button());
           p.submit_button().click();
           String toast = p.Success_toast().getText();
           
    	   Listen.Print_in_Report().log(Status.INFO, p.Landed_on_customer_list().isDisplayed() && toast.contains("Customer updated successfully.") ? "Testcase Passed Customer name edit working":"Testcase Failed Customer name edit not working");
    	   System.out.println(p.Landed_on_customer_list().isDisplayed() && toast.contains("Customer updated successfully.") ? "Testcase Passed Customer name edit working":"Testcase Failed Customer name edit not working");
           System.out.println();
       }
    
       
     public void customer_Address_edit_check() throws InterruptedException{
    	   
    	   Customer_module_locaters p = new Customer_module_locaters(d);
    	   JavascriptExecutor js = (JavascriptExecutor)d;
    	   
    	   
    	   String address = "Mani Casadona, International Financial Hub 700160, International Finalcial Hub(CBD), Newtown, Kolkata, West Bengal, India";
    	   
    	p.Address_Autocomplete_feild().click();
   		Thread.sleep(800);
   		p.Address_Autocomplete_feild().sendKeys(address);
   		Thread.sleep(800);
   		Generic_codes.commitAutocomplete( 
   		d,p.Address_Autocomplete_feild(), p.latitudeInput(), p.longitudeInput(), address,15);
   		Thread.sleep(800);
   		p.Address_Autocomplete_feild().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
   		Thread.sleep(800);
    	   js.executeScript("arguments[0].scrollIntoView(true);",p.submit_button());
           p.submit_button().click();
           String toast = p.Success_toast().getText();
           
    	   Listen.Print_in_Report().log(Status.INFO, p.Landed_on_customer_list().isDisplayed() && toast.contains("Customer updated successfully.") ? "Testcase Passed Customer Address edit working":"Testcase Failed Customer Address edit not working");
    	   System.out.println(p.Landed_on_customer_list().isDisplayed() && toast.contains("Customer updated successfully.") ? "Testcase Passed Customer Address edit working":"Testcase Failed Customer Address edit not working");
           System.out.println();
       }
       
     public void customer_Address_Setter(String Address_sent) throws InterruptedException{
  	   
  	   Customer_module_locaters p = new Customer_module_locaters(d);
  	   
  	    p.Address_Autocomplete_feild().click();
 		Thread.sleep(800);
 		p.Address_Autocomplete_feild().sendKeys(Address_sent);
 		Thread.sleep(800);
 		Generic_codes.commitAutocomplete( 
 		d,p.Address_Autocomplete_feild(), p.latitudeInput(), p.longitudeInput(), Address_sent,15);
 		Thread.sleep(800);
 		p.Address_Autocomplete_feild().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
 		Thread.sleep(800);
  	    
     }
       
       
       
      public void customer_search_and_edit_Button_click(String Customermail) throws InterruptedException{
    	   
    	   Customer_module_locaters p = new Customer_module_locaters(d);
    	   JavascriptExecutor js = (JavascriptExecutor)d;
    	   Product_Module_locaters pm = new Product_Module_locaters(d);
    	 
    	 
    	 Thread.sleep(800);
    	 p.customer_list_filter_inputs().get(1).sendKeys(Customermail);
      	 Listen.Print_in_Report().log(Status.INFO, p.Customer_email().get(0).getText().contains(Customermail) ? "Testcase Passed Searched Customer found in Result":"Testcase Failed Searched Customer not found in Result");
         System.out.println(p.Customer_email().get(0).getText().contains(Customermail) ? "Testcase Passed Searched Customer found in Result":"Testcase Failed Searched Customer not found in Result");
         System.out.println();
      	 js.executeScript("arguments[0].click();",  pm.Edit_buttons().get(0));
      	 Thread.sleep(800);
       }
	
}
