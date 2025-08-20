package validation;

import java.io.IOException;
import java.util.TreeMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Locaters.Login_locaters;
import Whippets.com.Admin.Base;
import Whippets.com.Admin.Data_Reader;

public class Login_validation extends Base{
	
	@Test(dataProvider="invalid_login_data")
	public void Invalid_login(TreeMap<String, String> datas) throws IOException, InterruptedException{
		
		Data_Reader file = new Data_Reader();
		Login_locaters p = new Login_locaters(d);
		
		d.navigate().to(file.ReadFile("Url"));
		d.manage().window().maximize();	
		p.Banner_Text();
		p.all_input_feilds().get(0).sendKeys(id);
		p.all_input_feilds().get(1).sendKeys(pass);
		p.submit_button().click();
		try{
			System.out.println(p.toast().getText());}
		catch(Exception k) {
			System.out.println("login Toast not implemented");
			System.out.println();
		p.Login_confirmation();}}
		
		
		
		@DataProvider
		public Object[][] invalid_login_data() throws IOException{
			
			Data_Reader files = new Data_Reader();
			
			TreeMap<String, String> values1 = new TreeMap<String, String>();
			
			values1.put("id", "admin");
			values1.put("pass", "pass");
			
         TreeMap<String, String> values2 = new TreeMap<String, String>();
			
            values1.put("id", files.ReadFile("Id"));
            values1.put("pass", "pass");
			
	    TreeMap<String, String> values3 = new TreeMap<String, String>();
				
	            values1.put("id", "admin");
	            values1.put("pass", files.ReadFile("Pass"));
			
         return new Object[][]{{values1},{values2},{values3}};
			
		}
		
		
		
		
	}


