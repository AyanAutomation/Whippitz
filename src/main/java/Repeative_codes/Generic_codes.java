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
     	Thread.sleep(800);}
     
     
     
     
     public static boolean commitAutocomplete(WebDriver d,
             WebElement addressField,
             WebElement latitudeInput,   // can be null -> fallback to value-change wait
             WebElement longitudeInput,  // can be null -> fallback to value-change wait
             String typedText,
             long timeoutSeconds) {
WebDriverWait w = new WebDriverWait(d, Duration.ofSeconds(timeoutSeconds));
boolean committed = false;

// helper waits
java.util.function.BooleanSupplier waitLatLon = () -> {
if (latitudeInput == null || longitudeInput == null) return false;
w.until(dr -> {
String lat = latitudeInput.getAttribute("value");
String lon = longitudeInput.getAttribute("value");
return lat != null && !lat.trim().isEmpty() && lon != null && !lon.trim().isEmpty();
});
return true;
};
java.util.function.BooleanSupplier waitValueChanged = () -> {
w.until(dr -> {
String v = addressField.getAttribute("value");
return v != null && !v.equals(typedText);
});
return true;
};

// 1) DOWN + ENTER + TAB
try {
addressField.click();
addressField.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN, org.openqa.selenium.Keys.ENTER, org.openqa.selenium.Keys.TAB);
committed = (waitLatLon.getAsBoolean() || waitValueChanged.getAsBoolean());
} catch (org.openqa.selenium.TimeoutException ignore) {}

// 2) ENTER + TAB (exact match)
if (!committed) {
try {
addressField.click();
addressField.sendKeys(org.openqa.selenium.Keys.ENTER, org.openqa.selenium.Keys.TAB);
committed = (waitLatLon.getAsBoolean() || waitValueChanged.getAsBoolean());
} catch (org.openqa.selenium.TimeoutException ignore) {}
}

// 3) DOWN, DOWN, ENTER + TAB (if first row isn’t selectable)
if (!committed) {
try {
addressField.click();
addressField.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN, org.openqa.selenium.Keys.ARROW_DOWN,
      org.openqa.selenium.Keys.ENTER, org.openqa.selenium.Keys.TAB);
committed = (waitLatLon.getAsBoolean() || waitValueChanged.getAsBoolean());
} catch (org.openqa.selenium.TimeoutException ignore) {}
}

// 4) Final nudge: change/blur via JS then wait value changed
if (!committed) {
((org.openqa.selenium.JavascriptExecutor) d)
.executeScript("arguments[0].dispatchEvent(new Event('change',{bubbles:true})); arguments[0].blur();",
   addressField);
try { committed = waitValueChanged.getAsBoolean(); } catch (org.openqa.selenium.TimeoutException ignore) {}
}

return committed;
}
}
