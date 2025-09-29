package Whippets.com.Admin;
import java.io.IOException;
import java.util.TreeMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners_Reports.Listen;
import Locaters.Customer_module_locaters;
import Locaters.Driver_Module_locaters;
import Locaters.Product_Module_locaters;

@Listeners(Listeners_Reports.Listen.class)
public class Driver_module extends Customer_Module{

	
	@Test
	public void Driver_list_Accessor() throws IOException, InterruptedException{
		
		Driver_Module_locaters p = new Driver_Module_locaters(d);
		Product_Module_locaters po = new Product_Module_locaters(d);
		
		menu_Accessor("Drivers","All Drivers");
		p.Landed_on_driver_list();
		po.select_dropdown();
		Select s = new Select(po.select_dropdown());
		s.selectByVisibleText("100");
		Thread.sleep(800);}
	
	@Test (dataProvider="driverData")
	public void Driver_Add(TreeMap<String,String> driver_data) throws IOException, InterruptedException{
		
		Driver_Module_locaters p = new Driver_Module_locaters(d);
		Customer_module_locaters pc = new Customer_module_locaters(d);
		JavascriptExecutor js = (JavascriptExecutor)d;
		Product_Module_locaters pd = new Product_Module_locaters(d);
		
		menu_Accessor("Drivers","Add Driver");
        p.profile_image_upload().sendKeys(driver_data.get("Profile Image"));
	    pc.first_name().sendKeys(driver_data.get("Driver First Name"));
	    pc.last_name().sendKeys(driver_data.get("Driver Last Name"));
	    pc.email().sendKeys(driver_data.get("Email"));
	    pc.phone().sendKeys(driver_data.get("Phone"));
	    p.password_field().sendKeys(driver_data.get("Password"));
	    js.executeScript("arguments[0].scrollIntoView(true);",pc.submit_button());
	    pc.submit_button().click();try {
	    Listen.Print_in_Report().log(Status.INFO, pd.product_toast().getText().trim());
	    System.out.println(pd.product_toast().getText().trim());
	    System.out.println();}
	    catch(Exception kkl){
	    	Listen.Print_in_Report().log(Status.INFO,"Driver add Success toast not Detected");}
		    System.out.println("Driver add Success toast not Detected");
		    System.out.println();
		}
	
	@DataProvider
	public Object[][] driverData() {
	 
		TreeMap<String, String> driver1 = new TreeMap<>();
	    driver1.put("Driver First Name", "Harvey");
	    driver1.put("Driver Last Name", "Wilson");
	    driver1.put("Email", "harvey.wilson95@yopmail.com");
	    driver1.put("Phone", "+61-431678901");
	    driver1.put("Password", "HarveyW@123");
	    driver1.put("Account Status", "Active");
	    driver1.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\Harvey.png");

	    TreeMap<String, String> driver2 = new TreeMap<>(); 
	    driver2.put("Driver First Name", "Matilda");
	    driver2.put("Driver Last Name", "Brown");
	    driver2.put("Email", "matilda.brown84@yopmail.com");
	    driver2.put("Phone", "+61-432789012");
	    driver2.put("Password", "MatildaB@123");
	    driver2.put("Account Status", "Inactive");
	    driver2.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\Matilda.png");

	    TreeMap<String, String> driver3 = new TreeMap<>();
	    driver3.put("Driver First Name", "Cooper");
	    driver3.put("Driver Last Name", "Harris");
	    driver3.put("Email", "cooper.harris79@yopmail.com");
	    driver3.put("Phone", "+61-433890123");
	    driver3.put("Password", "CooperH@123");
	    driver3.put("Account Status", "Active");
	    driver3.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\nikolai_petrov.png");

	    TreeMap<String, String> driver4 = new TreeMap<>();
	    driver4.put("Driver First Name", "Amelia");
	    driver4.put("Driver Last Name", "King");
	    driver4.put("Email", "amelia.king73@yopmail.com");
	    driver4.put("Phone", "+61-434901234");
	    driver4.put("Password", "AmeliaK@123");
	    driver4.put("Account Status", "Inactive");
	    driver4.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\irina_volkova.png");

	    TreeMap<String, String> driver5 = new TreeMap<>();
	    driver5.put("Driver First Name", "Jackson");
	    driver5.put("Driver Last Name", "Reid");
	    driver5.put("Email", "jackson.reid91@yopmail.com");
	    driver5.put("Phone", "+61-435012345");
	    driver5.put("Password", "JacksonR@123");
	    driver5.put("Account Status", "Active");
	    driver5.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\jackson.png");

	    TreeMap<String, String> driver6 = new TreeMap<>();
	    driver6.put("Driver First Name", "Zara");
	    driver6.put("Driver Last Name", "Mitchell");
	    driver6.put("Email", "zara.mitchell86@yopmail.com");
	    driver6.put("Phone", "+61-436123456");
	    driver6.put("Password", "ZaraM@123");
	    driver6.put("Account Status", "Inactive");
	    driver6.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\zara.png");

	    TreeMap<String, String> driver7 = new TreeMap<>();
	    driver7.put("Driver First Name", "Benjamin");
	    driver7.put("Driver Last Name", "Fraser");
	    driver7.put("Email", "benjamin.fraser82@yopmail.com");
	    driver7.put("Phone", "+61-437234567");
	    driver7.put("Password", "BenjaminF@123");
	    driver7.put("Account Status", "Active");
	    driver7.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\benjamin.png");

	    TreeMap<String, String> driver8 = new TreeMap<>();
	    driver8.put("Driver First Name", "Chloe");
	    driver8.put("Driver Last Name", "Parker");
	    driver8.put("Email", "chloe.parker77@yopmail.com");
	    driver8.put("Phone", "+61-438345678");
	    driver8.put("Password", "ChloeP@123");
	    driver8.put("Account Status", "Inactive");
	    driver8.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\chloe.png");

	    TreeMap<String, String> driver9 = new TreeMap<>();
	    driver9.put("Driver First Name", "Nathan");
	    driver9.put("Driver Last Name", "Brooks");
	    driver9.put("Email", "nathan.brooks79@yopmail.com");
	    driver9.put("Phone", "+61-439456789");
	    driver9.put("Password", "NathanB@123");
	    driver9.put("Account Status", "Active");
	    driver9.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\nathan.png");

	    TreeMap<String, String> driver10 = new TreeMap<>();
	    driver10.put("Driver First Name", "Grace");
	    driver10.put("Driver Last Name", "Campbell");
	    driver10.put("Email", "grace.campbell83@yopmail.com");
	    driver10.put("Phone", "+61-440567890");
	    driver10.put("Password", "GraceC@123");
	    driver10.put("Account Status", "Inactive");
	    driver10.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\grace.png");

	    return new Object[][] {
	        { driver1 },/*
	        { driver2 },
	        { driver3 },
	        { driver4 },
	        { driver5 },
	        { driver6 },
	        { driver7 },
	        { driver8 },
	        { driver9 },
	        { driver10} */
	    };
	}
	
      @Test(dataProvider="driverData")
      public void Added_driver_Delete(TreeMap<String,String> Driver_Datas) throws IOException, InterruptedException{
    	  
    	  Product_Module_locaters pd = new Product_Module_locaters(d);
    	  Driver_Module_locaters p = new Driver_Module_locaters(d);
    	  Customer_module_locaters pc = new Customer_module_locaters(d);
    	  
    	  String Search_mail = Driver_Datas.get("Email");
    	  
    	  Driver_list_Accessor();
    	  pd.search_box().sendKeys(Search_mail);try {
    	  Listen.Print_in_Report().log(Status.INFO, p.Fifth_column().get(0).getText().trim().contains(Search_mail)?"Testcase Passed Search Mail "+Search_mail+ " Showing in Result":"Testcase Failed Search Mail"+Search_mail+" not shown in result");
    	  pd.Delete_buttons().get(0).click();
    	  pd.Delete_popup();
    	  pd.Delete_button().click();
    	  Listen.Print_in_Report().log(Status.INFO, pc.Success_toast().getText());
    	  }catch(Exception del) {Listen.Print_in_Report().log(Status.INFO,"USer May Have Already Been Deleted");}} 






}
