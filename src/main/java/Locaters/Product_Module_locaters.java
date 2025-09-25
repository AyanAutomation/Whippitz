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
	private WebElement  Delete_popup; 
	@FindBy(xpath="//form")
	private WebElement  Add_Edit_form; 
	@FindBy(xpath="//iframe")
	private WebElement  Description_frame; 
	@FindBy(xpath="//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	private WebElement  Description_ck_Editor; 
	@FindBy(xpath="//input[@placeholder='Enter Product Discount Price']")
	private WebElement  discount_field; 
	@FindBy(xpath="//*[@class='errorMessage']")
	private List <WebElement>  error_messages; 
	@FindBy(xpath="//h3")
	private WebElement  Top_oftheForm; 
	@FindBy(xpath="(//tbody//td[6]//a[2])")
	private List <WebElement>  Edit_buttons; 
	@FindBy(xpath="//div[@class='toast-message']")
	private WebElement  product_toast;
	@FindBy(xpath="(//tbody//td[6]//a[1])")
	private List <WebElement> Product_view_buttons; 
	@FindBy(xpath="//*[text()='Admin | Product Variation']")
	private WebElement  variation_list_title; /*
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
    return Delete_button;} 
    public WebElement Add_Edit_form(){
    WebElementWait(Add_Edit_form);
    return Add_Edit_form;} 
    public List <WebElement> input_fields(){
    Add_Edit_form();
    List<WebElement> input_fields = Add_Edit_form().findElements(By.xpath(".//input[@class='form-control required errorClass']"));
    WebElementWait(input_fields);
    return input_fields;}
    public List <WebElement> filled_input_feilds(){
    Add_Edit_form();
    List<WebElement> filled_input_feilds = Add_Edit_form().findElements(By.xpath(".//input[@class='form-control required']"));
    WebElementWait(filled_input_feilds);
    return filled_input_feilds;}
    public WebElement Product_type_dropdowns(){
    Add_Edit_form();
    WebElement Product_type_dropdowns = Add_Edit_form().findElement(By.xpath(".//select[@name='product_type']"));
    WebElementWait(Product_type_dropdowns);
    return Product_type_dropdowns;} 
    public WebElement Discount_type_dropdowns(){
    Add_Edit_form();
    WebElement Discount_type_dropdowns = Add_Edit_form().findElement(By.xpath(".//select[@name='discount_type']"));
    WebElementWait(Discount_type_dropdowns);
    return Discount_type_dropdowns;} 
    public WebElement Variation_type_dropdowns(){
    Add_Edit_form();
    WebElement Variation_type_dropdowns = Add_Edit_form().findElement(By.xpath(".//select[@name='variation']"));
    WebElementWait(Variation_type_dropdowns);
    return Variation_type_dropdowns;}
    public WebElement percentage_field(){
    Add_Edit_form();
    WebElement percentage_field = Add_Edit_form().findElement(By.xpath(".//*[@name='discount_percentage']"));
    WebElementWait(percentage_field);
    return percentage_field;}
    public WebElement Description_frame(){
    WebElementWait(Description_frame);
    return Description_frame;} 
    public WebElement Description_ck_Editor(){
    WebElementWait(Description_ck_Editor);
    return Description_ck_Editor;} 
    public WebElement form_submit_button() throws InterruptedException{
    Add_Edit_form();
    WebElement form_submit_button = Add_Edit_form().findElement(By.xpath(".//button"));
    WebElementWait(form_submit_button);
    Scroll_into_view(form_submit_button);
    return form_submit_button;}  
    public WebElement discount_field(){
    WebElementWait(discount_field);
    return discount_field;} 
    public WebElement image_upload_field() throws InterruptedException{
    Add_Edit_form();
    WebElement image_upload_field = Add_Edit_form().findElement(By.xpath(".//input[@type='file']"));
    WebElementWait(image_upload_field);
    Scroll_into_view(image_upload_field);
    return image_upload_field; } 
    public List <WebElement> error_messages(){
    WebElementWait(error_messages);
    return error_messages;}
    public List <WebElement> Asteriks(){
    Add_Edit_form();
    List<WebElement> Asteriks = Add_Edit_form().findElements(By.xpath(".//*[@class='required-asterisk']"));
    WebElementWait(Asteriks);
    return Asteriks;}  
    public WebElement Top_oftheForm(){
     WebElementWait(Top_oftheForm);
    return Top_oftheForm;} 
    public List<WebElement> Edit_buttons(){
    WebElementWait(Edit_buttons);
    return Edit_buttons;} 
    public WebElement product_toast(){
    WebElementWait(product_toast);
    return product_toast;} 
    public List<WebElement> Product_view_buttons(){
    WebElementWait(Product_view_buttons);
    return Product_view_buttons;}  
    public WebElement variation_list_title(){
    WebElementWait(variation_list_title);
    return variation_list_title;}    
    public WebElement Stock_field_in_view_form(){
    Add_Edit_form();
    WebElement Stock_field_in_view_form = Add_Edit_form().findElement(By.xpath(".//input[@name='stock']"));
    WebElementWait(Stock_field_in_view_form);
    return Stock_field_in_view_form;}  
    
    

}
