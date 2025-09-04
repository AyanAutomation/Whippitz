package Repeative_codes;

import java.util.List;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Generic_codes {
	
	WebDriver d;
	
	public Generic_codes(WebDriver d){
		this.d=d;
	}
	
	
	public void WebElementWait(WebElement element){
		
		WebDriverWait w = new WebDriverWait(d,Duration.ofSeconds(5));
		
		w.until(ExpectedConditions.visibilityOf(element));
		
		
	}
	
    public void WebElementWait(List<WebElement> elements){
		
    	WebDriverWait w = new WebDriverWait(d,Duration.ofSeconds(10));
		
    	w.until(ExpectedConditions.visibilityOfAllElements(elements));
		
	}
    
     public void Move_to_element(WebElement element){
		
    	Actions a = new Actions(d);
		
    	a.moveToElement(element).build().perform();
		
	}
     
     public void Scroll_into_view(WebElement element) throws InterruptedException{
 		
     	JavascriptExecutor js =  (JavascriptExecutor)d;
     	
     	Thread.sleep(800);
     	js.executeScript("arguments[0].scrollIntoView(true);",element);
     	Thread.sleep(800);
 		
 	}

}
