package Locaters;

import java.util.List;

import org.openqa.selenium.By;
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
	private WebElement  password_field; 
	@FindBy(xpath="//tbody")
	private WebElement list; /*
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
    return password_field;} 
    public WebElement list(){
    WebElementWait(list);
    return list;}  
    public List<WebElement> second_column(){
    list();
    List<WebElement> second_column = list().findElements(By.xpath(".//td[2]"));
    WebElementWait(second_column);
    return second_column;} 
    public List<WebElement> Third_column(){
    list();
    List<WebElement> Third_column = list().findElements(By.xpath(".//td[3]"));
    WebElementWait(Third_column);
    return Third_column;} 
    public List<WebElement> Fourth_column(){
    list();
    List<WebElement> Fourth_column = list().findElements(By.xpath(".//td[4]"));
    WebElementWait(Fourth_column);
    return Fourth_column;} 
    public List<WebElement> Fifth_column(){
    list();
    List<WebElement> Fifth_column = list().findElements(By.xpath(".//td[5]"));
    WebElementWait(Fifth_column);
    return Fifth_column;}
    public List<WebElement> Seventh_column(){
    list();
    List<WebElement> Seventh_column = list().findElements(By.xpath(".//td[7]"));
    WebElementWait(Seventh_column);
    return Seventh_column;} /*
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
