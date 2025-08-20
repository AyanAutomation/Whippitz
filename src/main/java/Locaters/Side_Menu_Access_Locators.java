package Locaters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Repeative_codes.Generic_codes;

public class Side_Menu_Access_Locators extends Generic_codes{
	
	@FindBy(xpath="//*[@class='sidebar']")
	private WebElement  sidebar; /*
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ; */
	
	public Side_Menu_Access_Locators(WebDriver d){
		
		super(d);
		PageFactory.initElements(d, this);}
	
	 
    public WebElement sidebar(){
    WebElementWait(sidebar);
	return sidebar;} /*
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
	
    */

}
