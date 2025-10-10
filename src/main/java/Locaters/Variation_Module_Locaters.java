package Locaters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Repeative_codes.Generic_codes;

public class Variation_Module_Locaters extends Generic_codes{
	
	
	@FindBy(xpath="//h3[text()='Admin | Product Variation']")
	private WebElement  variation_list_landing_confirmation;
	@FindBy(xpath="//table//tbody")
	private WebElement  Table_List; 
	@FindBy(xpath="//form")
	private WebElement  Add_Edit_form; /*
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ; /*
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
	
	
    public Variation_Module_Locaters(WebDriver d){
		
		super(d);
		PageFactory.initElements(d, this);}
	
   
    public WebElement variation_list_landing_confirmation(){
    WebElementWait(variation_list_landing_confirmation);
    return variation_list_landing_confirmation;}  
    public WebElement Table_List(){
    WebElementWait(Table_List);
    return Table_List;}
    public List<WebElement> rows(){
    Table_List();	
    List<WebElement> rows = Table_List().findElements(By.xpath(".//tr"));
    WebElementWait(rows);
    return rows;}
    public List<WebElement> edit_buttons(){
    Table_List();
    List<WebElement> edit_buttons = Table_List().findElements(By.xpath(".//tr//a[1]"));
    WebElementWait(edit_buttons);
    return edit_buttons;}
    public List<WebElement> delete_buttons(){
    List<WebElement> delete_buttons = Table_List().findElements(By.xpath(".//tr//a[2]"));
    WebElementWait(delete_buttons);
    return delete_buttons;}
    public WebElement Add_Edit_form(){
    WebElementWait(Add_Edit_form);
    return Add_Edit_form;}
    public List<WebElement> inputs(){
    Add_Edit_form();	
    List<WebElement> inputs = Add_Edit_form().findElements(By.xpath(".//input[@class='form-control required']"));
    WebElementWait(inputs);
    return inputs;}
    public WebElement submit(){
    Add_Edit_form();
    WebElement submit =	Add_Edit_form().findElement(By.xpath(".//button"));
    WebElementWait(submit);
    return submit;} /*
    public WebElement (){
    WebElementWait();
    return ;}  /*
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}
    public WebElement (){
    WebElementWait();
    return ;}  */
	

}
