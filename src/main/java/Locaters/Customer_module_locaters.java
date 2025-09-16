package Locaters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Repeative_codes.Generic_codes;

public class Customer_module_locaters extends Generic_codes{

	
	
	@FindBy(xpath="//*[text()='Admin | Customer']")
	private WebElement  Landed_on_customer_list; 
	@FindBy(xpath="//tbody")
	private WebElement customer_list; 
	@FindBy(name="first_name")
	private WebElement  first_name;
	@FindBy(name="last_name")
	private WebElement  last_name;
	@FindBy(name="email")
	private WebElement  email;
	@FindBy(name="phone")
	private WebElement phone;
	@FindBy(xpath="//select[@name='account_status']")
	private WebElement  Status_select_dropdown;
	@FindBy(xpath="//gmp-place-autocomplete")
	private WebElement  Address_Autocomplete_feild;
	@FindBy(xpath="//input[@type='file']")
	private WebElement  Image_upload_field; 
	@FindBy(xpath="//button[@type='submit']")
	private WebElement submit_button; 
	@FindBy(xpath="//input[@placeholder='Enter Latitude']")
	private WebElement  latitudeInput;
	@FindBy(xpath="//input[@placeholder='Enter Longitude']")
	private WebElement  longitudeInput; 
	@FindBy(xpath="//div[@id='toast-container']//div[@class='toast-message']")
	private WebElement Success_toast; 
	@FindBy(xpath="(//table)[1]//tr[2]//input")
	private List <WebElement>  customer_list_filter_inputs;/*
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;*/
	
	
	
	public Customer_module_locaters(WebDriver d){
		
		super(d);
		PageFactory.initElements(d, this);}
	
	
	
	
    public WebElement Landed_on_customer_list(){
    WebElementWait(Landed_on_customer_list);
    return Landed_on_customer_list;}  
    public WebElement customer_list(){
    WebElementWait(customer_list);
    return customer_list;} 
    public List<WebElement> names(){
    customer_list();
    List<WebElement> names = customer_list().findElements(By.xpath(".//td[3]"));
    WebElementWait(names);
    return names;} 
    public List<WebElement> Customer_phnums(){
    customer_list();
    List<WebElement> phnums = customer_list().findElements(By.xpath(".//td[5]"));
    WebElementWait(phnums);
    return phnums;} 
    public List<WebElement> Customer_email(){
    customer_list();
    List<WebElement> email = customer_list().findElements(By.xpath(".//td[4]"));
    WebElementWait(email);
    return email;}
    public List<WebElement> Customer_Status(){
    customer_list();
    List<WebElement> stats = customer_list().findElements(By.xpath(".//td[6]"));
    WebElementWait(stats);
    return stats;} 
    public WebElement first_name(){
    WebElementWait(first_name);
    return first_name;} 
    public WebElement last_name(){
    WebElementWait(last_name);
    return last_name;} 
    public WebElement email(){
    WebElementWait(email);
    return email;} 
    public WebElement phone(){
    WebElementWait(phone);
    return phone;} 
    public WebElement Status_select_dropdown(){
    WebElementWait(Status_select_dropdown);
    return Status_select_dropdown;} 
    public WebElement Address_Autocomplete_feild(){
    WebElementWait(Address_Autocomplete_feild);
    return Address_Autocomplete_feild;} 
    public WebElement Image_upload_field(){
    WebElementWait(Image_upload_field);
    return Image_upload_field;} 
    public WebElement submit_button(){
    WebElementWait(submit_button);
    return submit_button;} 
    public WebElement latitudeInput(){
    WebElementWait(latitudeInput);
    return latitudeInput;} 
    public WebElement longitudeInput(){
    WebElementWait(longitudeInput);
    return longitudeInput;} 
    public WebElement Success_toast(){
    WebElementWait(Success_toast);
    Move_to_element(Success_toast);
    return Success_toast;} 
    public List <WebElement> customer_list_filter_inputs(){
    WebElementWait(customer_list_filter_inputs);
    return customer_list_filter_inputs;} /*
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;} 
    public WebElement (){
    WebElementWait();
    return ;} */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
