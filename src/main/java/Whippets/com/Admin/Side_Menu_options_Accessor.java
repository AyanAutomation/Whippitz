package Whippets.com.Admin;
import java.io.IOException;
import org.testng.annotations.Test;
import Locaters.Side_Menu_Access_Locators;

public class Side_Menu_options_Accessor extends Login{
	
	@Test
	public void side_menu() throws IOException{
		
		
		Side_Menu_Access_Locators p = new Side_Menu_Access_Locators(d);
		
		login();
		p.sidebar();
		
		
		
	}
	

}
