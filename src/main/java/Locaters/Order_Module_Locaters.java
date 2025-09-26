package Locaters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Repeative_codes.Generic_codes;

public class Order_Module_Locaters extends Generic_codes{
	
	
	@FindBy(xpath="//table[@id='orderTable']")
	private WebElement  Order_list; 
	@FindBy(xpath="//*[text()='Admin | Order']")
	private WebElement  landed_in_order; 
	@FindBy(xpath="//table[@class='table table-head-fixed text-nowrap dataTable no-footer'][1]//th[@tabindex='0']")
	private List<WebElement>  Column_titles; /*
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
	
	
	
    public Order_Module_Locaters(WebDriver d){
		
		super(d);
		PageFactory.initElements(d, this);}
    
    
    
    public WebElement Order_list(){
    WebElementWait(Order_list);
    return Order_list;} 
    public List<WebElement> first_column(){
    Order_list();	
    List<WebElement> first_column = Order_list().findElements(By.xpath(".//td[1]"));	
    WebElementWait(first_column);
    return first_column;}
    public List<WebElement> second_column(){
    Order_list();	
    List<WebElement> second_column = Order_list().findElements(By.xpath(".//td[2]"));	
    WebElementWait(second_column);
    return second_column;} 
    public List<WebElement> third_column(){
    Order_list();	
    List<WebElement> third_column = Order_list().findElements(By.xpath(".//td[3]"));	
    WebElementWait(third_column);
    return third_column;}
    public List<WebElement> fourth_column(){
    Order_list();	
    List<WebElement> fourth_column = Order_list().findElements(By.xpath(".//td[4]"));	
    WebElementWait(fourth_column);
    return fourth_column;}
    public List<WebElement> fifth_column(){
    Order_list();	
    List<WebElement> fifth_column = Order_list().findElements(By.xpath(".//td[5]"));	
    WebElementWait(fifth_column);
    return fifth_column;}
    public List<WebElement> sixth_column(){
    Order_list();	
    List<WebElement> sixth_column = Order_list().findElements(By.xpath(".//td[6]"));	
    WebElementWait(sixth_column);
    return sixth_column;} 
    public List<WebElement> seventh_column(){
    Order_list();	
    List<WebElement> seventh_column = Order_list().findElements(By.xpath(".//td[7]"));	
    WebElementWait(seventh_column);
    return seventh_column;}
    public List<WebElement> eigth_column(){
    Order_list();	
    List<WebElement> eigth_column = Order_list().findElements(By.xpath(".//td[8]"));	
    WebElementWait(eigth_column);
    return eigth_column;} 
    public WebElement landed_in_order(){
    WebElementWait(landed_in_order);
    return landed_in_order;} 
    public List<WebElement> Column_titles(){
    WebElementWait(Column_titles);
    return Column_titles;} /*
    public List<WebElement> (){
    Order_list();	
    List<WebElement> first_column = Order_list().findElements(By.xpath(".//td[1]"));	
    WebElementWait(first_column);
    return first_column;} */
    
    
    

}
