package Locaters;

import java.util.List;

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
	private WebElement searchBox; /*
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
    return searchBox;}/*
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
