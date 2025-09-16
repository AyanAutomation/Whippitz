package Repeative_codes;

import java.util.List;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
		
    	WebDriverWait w = new WebDriverWait(d,Duration.ofSeconds(5));
		
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
     
     
     public void Hidden_Element_unhide(WebElement element){
    	 
    	JavascriptExecutor js =  (JavascriptExecutor)d;
    	 
    	js.executeScript(element.isDisplayed()? "void 0;" : "arguments[0].classList.remove('d-none');arguments[0].removeAttribute('hidden');arguments[0].style.cssText='display:block !important;visibility:visible !important;opacity:1 !important;width:1px;height:1px;position:fixed;left:0;top:0;z-index:9999;';", element);
     }
     
     
     
     
     
     public static boolean commitAutocomplete(WebDriver d,
             WebElement addressField,
             WebElement latitudeInput,   // can be null
             WebElement longitudeInput,  // can be null
             String typedText,
             long timeoutSeconds) {
return commitAutocomplete(d, addressField, latitudeInput, longitudeInput, typedText, timeoutSeconds, false);
}

public static boolean commitAutocomplete(WebDriver d,
             WebElement addressField,
             WebElement latitudeInput,
             WebElement longitudeInput,
             String typedText,
             long timeoutSeconds,
             boolean debug) {

long deadline = System.currentTimeMillis() + timeoutSeconds * 1000L;

java.util.function.BooleanSupplier waitLatLon = () -> {
if (latitudeInput == null || longitudeInput == null) return false;
WebDriverWait shortWait = new WebDriverWait(d, Duration.ofSeconds(2));
if (debug) System.out.println("[AC] waitLatLon: waiting up to 2s for lat/lon…");
shortWait.until(dr -> {
String lat = latitudeInput.getAttribute("value");
String lon = longitudeInput.getAttribute("value");
if (debug) System.out.println("[AC]   lat=" + lat + " | lon=" + lon);
return lat != null && !lat.trim().isEmpty() && lon != null && !lon.trim().isEmpty();
});
return true;
};

java.util.function.BooleanSupplier waitValueChanged = () -> {
WebDriverWait shortWait = new WebDriverWait(d, Duration.ofSeconds(2));
if (debug) System.out.println("[AC] waitValueChanged: waiting up to 2s for value!=typed…");
shortWait.until(dr -> {
String v = addressField.getAttribute("value");
if (debug) System.out.println("[AC]   current value=" + v);
return v != null && !v.equals(typedText);
});
return true;
};

java.util.function.Supplier<Boolean> success = () -> {
try {
return (waitLatLon.getAsBoolean() || waitValueChanged.getAsBoolean());
} catch (org.openqa.selenium.TimeoutException e) {
if (debug) System.out.println("[AC]   -> wait timed out");
return false;
}
};

while (System.currentTimeMillis() < deadline) {
// 1) DOWN + ENTER
if (debug) System.out.println("[AC] Try #1: ARROW_DOWN + ENTER + TAB");
addressField.click();
addressField.sendKeys(Keys.ARROW_DOWN, Keys.ENTER, Keys.TAB);
if (success.get()) { if (debug) System.out.println("[AC] ✅ Committed via DOWN+ENTER"); return true; }

// 2) ENTER only (exact match)
if (debug) System.out.println("[AC] Try #2: ENTER + TAB");
addressField.click();
addressField.sendKeys(Keys.ENTER, Keys.TAB);
if (success.get()) { if (debug) System.out.println("[AC] ✅ Committed via ENTER"); return true; }

// 3) DOWN, DOWN + ENTER (skip non-selectable first row)
if (debug) System.out.println("[AC] Try #3: DOWN, DOWN + ENTER + TAB");
addressField.click();
addressField.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER, Keys.TAB);
if (success.get()) { if (debug) System.out.println("[AC] ✅ Committed via DOWN,DOWN+ENTER"); return true; }

// 4) JS change/blur + check
if (debug) System.out.println("[AC] Try #4: JS change+blur");
((JavascriptExecutor) d).executeScript(
"arguments[0].dispatchEvent(new Event('change',{bubbles:true})); arguments[0].blur();",
addressField
);
if (success.get()) { if (debug) System.out.println("[AC] ✅ Committed via JS change/blur"); return true; }

if (debug) System.out.println("[AC] Loop retry before deadline…");
}

if (debug) System.out.println("[AC] ❌ Failed to commit within " + timeoutSeconds + "s");
return false;
}
}
