package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class PianoPage extends BasePage {
    protected JavascriptExecutor js;
    private final Map<String, String> mapaNotas = Map.of(
            "do" ,"1c",
            "re","1d",
            "mi","1e",
            "fa","1f",
            "sol","1g",
            "la","2a",
            "si","2b"
    );
    public PianoPage(WebDriver driver, String url){
        super(driver);
        this.driver.get(url);
        this.js = (JavascriptExecutor) driver;
    }
    public String obtenerTecla(String nota){
        return mapaNotas.get(nota);
    }
    private WebElement obtenerTeclaElemento(String nota) {
        String tecla = obtenerTecla(nota);
        By locator = By.cssSelector("span[data-note='" + tecla + "']");
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public boolean esPresionadaLaTecla(String nota) {
        String tecla = obtenerTecla(nota);
        By teclaElem = By.cssSelector("span[data-note='" + tecla + "']");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(d -> tieneClase(teclaElem));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void presionarTeclaDown(String nota) {
        WebElement element = obtenerTeclaElemento(nota);

        js.executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('mousedown', {bubbles: true}));",
                element
        );
    }

    public void soltarTecla(String nota) {
        WebElement element = obtenerTeclaElemento(nota);

        js.executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('mouseup', {bubbles: true}));",
                element
        );

        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(d -> !tieneClase(By.cssSelector("span[data-note='" + obtenerTecla(nota) + "']")));
    }
    public void esperar(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public boolean esLiberadaLaTecla(String nota) {
        String tecla = obtenerTecla(nota);
        By teclaElem = By.cssSelector("span[data-note='" + tecla + "']");

        try {
            return new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(driver -> !tieneClase(teclaElem));
        } catch (Exception e) {
            return false;
        }
    }
}
