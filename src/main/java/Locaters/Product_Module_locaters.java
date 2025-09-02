package Locaters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Repeative_codes.Generic_codes;

public class Product_Module_locaters extends Generic_codes{
	
	@FindBy(xpath="//tbody//td[2]")
	private List <WebElement> Second_column;
	@FindBy(xpath="//select")
	private WebElement  select_dropdown;
	@FindBy(xpath="//input[@type='search']")
	private WebElement searchBox; 
	@FindBy(xpath="//*[@type='search']")
	private WebElement  search_box; 
	@FindBy(xpath="//i[@class='fas fa-trash delete-icon']")
	private List <WebElement> Delete_buttons; 
	@FindBy(xpath="//*[@class='sweet-alert showSweetAlert visible']")
	private WebElement  Delete_popup; /*
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;*/
	
	
    public Product_Module_locaters(WebDriver d){
		
		super(d);
		PageFactory.initElements(d, this);}
    
    
    public List <WebElement> Second_column(){
    WebElementWait(Second_column);
    return Second_column;}
    public WebElement select_dropdown(){
    WebElementWait(select_dropdown);
    return select_dropdown;}
    public WebElement searchBox(){
    WebElementWait(searchBox);
    return searchBox;}
    public WebElement search_box(){
    WebElementWait(search_box);
    return search_box;}
    public List <WebElement> Delete_buttons(){
    WebElementWait(Delete_buttons);
    return Delete_buttons;} 
    public WebElement Delete_popup(){
    WebElementWait(Delete_popup);
    return Delete_popup;} 
    public WebElement Delete_button(){
    Delete_popup();
    WebElement Delete_button = Delete_popup().findElement(By.xpath(".//button[@class='confirm']"));
    WebElementWait(Delete_button);
    return Delete_button;} /*
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
