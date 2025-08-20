package Locaters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Repeative_codes.Generic_codes;

public class Login_locaters extends Generic_codes{
	
	@FindBy(xpath="//div[@class='card-header text-center']")
	private WebElement Banner_Text;
	@FindBy(xpath="//*[@class='card-body']//input")
	private List <WebElement> all_input_feilds  ;
	@FindBy(xpath="//*[@type='submit']")
	private WebElement  submit_button;
	@FindBy(xpath="//div[@class='toast-message']")
	private WebElement toast; 
	@FindBy(xpath="//*[@class='brand-link']")
	private WebElement Login_confirmation; /*
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ;
	@FindBy(xpath="")
	private WebElement  ; */
	

	  public Login_locaters(WebDriver d){
	
		super(d);
		PageFactory.initElements(d, this);}
	
	    public WebElement Banner_Text(){
		WebElementWait(Banner_Text);
		return Banner_Text;}
	    public List <WebElement> all_input_feilds(){
		WebElementWaits(all_input_feilds);
		return all_input_feilds;}
		public WebElement submit_button(){
		WebElementWait(submit_button);
		return submit_button;}
		public WebElement toast(){
		WebElementWait(toast);
		return toast;} 
        public WebElement Login_confirmation(){
        WebElementWait(Login_confirmation);
		return Login_confirmation;} /*
        public WebElement (){
        WebElementWait();
		return ;} */
	
	
	
	
	

}
