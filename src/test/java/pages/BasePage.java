package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    protected void clickWithJS(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].dispatchEvent(new MouseEvent('mousedown', {bubbles: true}));", element);
        js.executeScript("arguments[0].dispatchEvent(new MouseEvent('mouseup', {bubbles: true}));", element);
        js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true}));", element);
    }
    protected boolean tieneClase(By locator, String clase) {
        WebElement element = driver.findElement(locator);
        return element.getDomAttribute("class").contains(clase);
    }
}
