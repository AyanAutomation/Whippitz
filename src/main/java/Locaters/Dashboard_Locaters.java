package Locaters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Repeative_codes.Generic_codes;

public class Dashboard_Locaters extends Generic_codes{
	
	
	@FindBy(xpath="//a[@href='https://whippets.yourcloudnetwork.net/admin/dashboard']")
	private WebElement  Dashboard_menu_Options;
	@FindBy(xpath="//*[@class='card-body']")
	private WebElement  Dashboard_land_confirmation;
	@FindBy(xpath="//h3[@class='card-text']")
	private List <WebElement>  Dashbaord_status_cards; 
	@FindBy(xpath="//*[@class='col-lg-6 col-md-6 mb-3']//tbody")
	private WebElement  inventory_list;/*
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
	
	
    public Dashboard_Locaters(WebDriver d){
		
		super(d);
		PageFactory.initElements(d, this);}
    
    
    
    
    public WebElement Dashboard_menu_Options(){
    WebElementWait(Dashboard_menu_Options);
    return Dashboard_menu_Options;} 
    public WebElement Dashboard_land_confirmation(){
    WebElementWait(Dashboard_land_confirmation);
    return Dashboard_land_confirmation;}
    public List <WebElement> Dashbaord_status_cards(){
    WebElementWait(Dashbaord_status_cards);
    return Dashbaord_status_cards;} 
    public WebElement inventory_list(){
    WebElementWait(inventory_list);
    return inventory_list;} 
    public List <WebElement> packs_labels(){
    inventory_list();
    List <WebElement> packs_labels = inventory_list().findElements(By.xpath(".//td[1]"));
    WebElementWait(packs_labels);
    return packs_labels;}
    public List <WebElement> stock_numbers(){
    inventory_list();
    List <WebElement> stock_numbers = inventory_list().findElements(By.xpath(".//td[2]"));
    WebElementWait(stock_numbers);
    return stock_numbers;
    } /*
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}  */
    
    
	
	
	

}
