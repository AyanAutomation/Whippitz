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
	private WebElement customer_list; /*
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
    return stats;} /*
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
