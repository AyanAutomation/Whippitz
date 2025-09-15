package Locaters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Repeative_codes.Generic_codes;

public class Driver_Module_locaters extends Generic_codes{

	
	@FindBy(xpath="//*[text()='Admin | Drivers']")
	private WebElement  Landed_on_driver_list;
	@FindBy(xpath="//input[@id='avatar-upload' and @type='file']")
	private WebElement  profile_image_upload; 
	@FindBy(name="password")
	private WebElement  password_field; /*
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
	
	
	
	
	
	public Driver_Module_locaters(WebDriver d){
		
		super(d);
		PageFactory.initElements(d, this);}
	
	

	
	
    public WebElement Landed_on_driver_list(){
    WebElementWait(Landed_on_driver_list);
    return Landed_on_driver_list;} 
    public WebElement profile_image_upload(){
    Hidden_Element_unhide(profile_image_upload);
    WebElementWait(profile_image_upload);
    return profile_image_upload;} 
    public WebElement password_field(){
    WebElementWait(password_field);
    return password_field;} /*
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
