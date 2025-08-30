package Locaters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Repeative_codes.Generic_codes;
import bsh.commands.dir;

public class Side_Menu_Access_Locators extends Generic_codes{
	
	@FindBy(xpath="//*[@class='sidebar']")
	private WebElement  sidebar; 
	@FindBy(xpath="(//*[@class='nav nav-treeview'])[1]")
	private WebElement Product_Submenu_list;
	@FindBy(xpath="//h3")
	private WebElement page_title; /*
	@FindBy(xpath="")
	private WebElement  ; */
	
	public Side_Menu_Access_Locators(WebDriver d){
		
		super(d);
		PageFactory.initElements(d, this);}
	
	 
    public WebElement sidebar(){
    WebElementWait(sidebar);
	return sidebar;} 
    public List<WebElement> Sidemenus(){
    sidebar();
    List<WebElement> Sidemenus = sidebar().findElements(By.xpath(".//a[@href='#']"));
    WebElementWaits(Sidemenus);
    return Sidemenus;} 
    public WebElement Product_Submenu_list(){
    WebElementWait(Product_Submenu_list);
    return Product_Submenu_list;}
    public List<WebElement> options(){
    Product_Submenu_list();
    List<WebElement> options = Product_Submenu_list().findElements(By.xpath(".//a"));
    WebElementWaits(options);
    return options;}
    public WebElement page_title(){
    WebElementWait(page_title);
    return page_title;}/*
    public WebElement (){
    WebElementWait();
    return ;}
	
    */

}
