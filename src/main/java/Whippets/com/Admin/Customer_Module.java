package Whippets.com.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import Locaters.Customer_module_locaters;
import Locaters.Product_Module_locaters;

public class Customer_Module extends Product_module{
	
	
	TreeMap<String, String> customerNames_phnumbers = new TreeMap<String, String>();
	TreeMap<String, String> customerNames_Emails = new TreeMap<String, String>();
	TreeMap<String, String> customerNames_Statuses = new TreeMap<String, String>();
	List <String> customers_names = new ArrayList<String>();
	List <String> customers_phones = new ArrayList<String>();
	List <String> customers_Emails= new ArrayList<String>();
	List <String> customers_Statuses= new ArrayList<String>();
	
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
		Product_Module_locaters po = new Product_Module_locaters(d);
		
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
				customerNames_Statuses.put(customers_names.get(k), customerNames_Statuses.get(k));});}}
	
	
	
	public void allList_clear(){
		
		customers_names.clear();
		customers_phones.clear();
		customers_Emails.clear();
		customers_Statuses.clear();
		customerNames_phnumbers.clear();
		customerNames_Emails.clear();
		customerNames_Statuses.clear();
		
		
		
	}

}
