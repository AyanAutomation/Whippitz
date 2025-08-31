package Repeative_codes;

import java.util.List;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Generic_codes {
	
	WebDriver d;
	
	public Generic_codes(WebDriver d){
		this.d=d;
	}
	
	
	public void WebElementWait(WebElement element){
		
		WebDriverWait w = new WebDriverWait(d,Duration.ofSeconds(10));
		
		w.until(ExpectedConditions.visibilityOf(element));
		
		
	}
	
    public void WebElementWait(List<WebElement> elements){
		
    	WebDriverWait w = new WebDriverWait(d,Duration.ofSeconds(10));
		
    	w.until(ExpectedConditions.visibilityOfAllElements(elements));
		
	}

}
