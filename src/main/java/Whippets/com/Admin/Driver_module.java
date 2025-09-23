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
		
		
		menu_Accessor("Drivers","Add Driver");
        p.profile_image_upload().sendKeys(driver_data.get("Profile Image"));
	    pc.first_name().sendKeys(driver_data.get("Driver First Name"));
	    pc.last_name().sendKeys(driver_data.get("Driver Last Name"));
	    pc.email().sendKeys(driver_data.get("Email"));
	    pc.phone().sendKeys(driver_data.get("Phone"));
	    p.password_field().sendKeys(driver_data.get("Password"));
	    js.executeScript("arguments[0].scrollIntoView(true);",pc.submit_button());
	    pc.submit_button().click();try {
	    Listen.Print_in_Report().log(Status.INFO, pc.Success_toast().getText());}
	    catch(Exception kkl){
	    	Listen.Print_in_Report().log(Status.INFO,"Driver add Success toast not Detected");}
		
		
		}
	
	@DataProvider
	public Object[][] driverData() {
	 
		TreeMap<String, String> driver1 = new TreeMap<>();
	    driver1.put("Driver First Name", "Ethan");
	    driver1.put("Driver Last Name", "McKenzie");
	    driver1.put("Email", "ethan.mckenzie94@yopmail.com");
	    driver1.put("Phone", "+61-421567890");
	    driver1.put("Password", "EthanM@123");
	    driver1.put("Account Status", "Active");
	    driver1.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\Ethan.png");

	    TreeMap<String, String> driver2 = new TreeMap<>();
	    driver2.put("Driver First Name", "Sophie");
	    driver2.put("Driver Last Name", "Anderson");
	    driver2.put("Email", "sophie.anderson87@yopmail.com");
	    driver2.put("Phone", "+61-422678901");
	    driver2.put("Password", "SophieA@123");
	    driver2.put("Account Status", "Inactive");
	    driver2.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\Sophie.png");

	    TreeMap<String, String> driver3 = new TreeMap<>();
	    driver3.put("Driver First Name", "Liam");
	    driver3.put("Driver Last Name", "Thompson");
	    driver3.put("Email", "liam.thompson75@yopmail.com");
	    driver3.put("Phone", "+61-423789012");
	    driver3.put("Password", "LiamT@123");
	    driver3.put("Account Status", "Active");
	    driver3.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\nikolai_petrov.png");

	    TreeMap<String, String> driver4 = new TreeMap<>();
	    driver4.put("Driver First Name", "Olivia");
	    driver4.put("Driver Last Name", "Hughes");
	    driver4.put("Email", "olivia.hughes69@yopmail.com");
	    driver4.put("Phone", "+61-424890123");
	    driver4.put("Password", "OliviaH@123");
	    driver4.put("Account Status", "Inactive");
	    driver4.put("Profile Image", System.getProperty("user.dir") + "\\Driver Images\\irina_volkova.png");

	    return new Object[][] {
	        { driver1 },
	        { driver2 },
	        { driver3 },
	        { driver4 }
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
