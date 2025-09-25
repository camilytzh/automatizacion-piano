package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/**
 * Clase base para todos los page object.
 * Contiene el WebDriver y métodos comúnes de interacción.
 * Todas las páginas deben heredar de esta clase base.
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    protected boolean tieneClaseActive(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getDomAttribute("class").contains("active");
    }
}
