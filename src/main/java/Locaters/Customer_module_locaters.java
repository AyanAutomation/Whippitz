package Locaters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Repeative_codes.Generic_codes;

public class Customer_module_locaters extends Generic_codes{

	
	
	@FindBy(xpath="//*[text()='Admin | Customer']")
	private WebElement  Landed_on_customer_list; /*
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
    return Landed_on_customer_list;}  /*
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
