package Whippets.com.Admin;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import Locaters.Customer_module_locaters;
import Locaters.Product_Module_locaters;

public class Customer_Module extends Product_module{
	
	
	@Test
	public void customer_list_Accessor() throws IOException, InterruptedException{
		
		Customer_module_locaters p = new Customer_module_locaters(d);
		Product_Module_locaters po = new Product_Module_locaters(d);
		
		menu_Accessor("Customers","All Customers");
		p.Landed_on_customer_list();
		po.select_dropdown();
		Select s = new Select(po.select_dropdown());
		s.selectByVisibleText("100");
		Thread.sleep(800);
		
	}

}
