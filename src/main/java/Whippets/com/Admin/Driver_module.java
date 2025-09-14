package Whippets.com.Admin;
import java.io.IOException;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
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
	
	
	
	
	
	
	
	

}
