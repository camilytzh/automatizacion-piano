package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
/**
 * Clase que representa la página del piano virtual.
 * Permite interactuar con las teclas del piano y verificar su estado.
 */
public class PianoPage extends BasePage {
    protected JavascriptExecutor js;
    /**
     * Mapeo de notas con identificadores únicos para cada una.
     */
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
    /**
     * Verifica si la clase del elemento pasa a un estado activo que indica que está siendo presionada.
     * @param nota La nota musical a verificar si se encuentra activa.
     */
    public boolean esPresionadaLaTecla(String nota) {
        String tecla = obtenerTecla(nota);
        By teclaElem = By.cssSelector("span[data-note='" + tecla + "']");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(d -> tieneClaseActive(teclaElem));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Mantiene presionada una tecla en específico.
     * @param nota La nota musical a presionar.
     */
    public void presionarTeclaDown(String nota) {
        WebElement element = obtenerTeclaElemento(nota);

        js.executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('mousedown', {bubbles: true}));",
                element
        );
    }
    /**
     * Libera la tecla que estaba siendo presionada
     * @param nota La nota musical a verificar.
     * */
    public void soltarTecla(String nota) {
        WebElement element = obtenerTeclaElemento(nota);

        js.executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('mouseup', {bubbles: true}));",
                element
        );

        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(d -> !tieneClaseActive(By.cssSelector("span[data-note='" + obtenerTecla(nota) + "']")));
    }
    public void esperar(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Verifica que la tecla no siga estando presionada obteniendo el nombre de su clase.
     * @param nota La nota musical a verificar.
     * */
    public boolean esLiberadaLaTecla(String nota) {
        String tecla = obtenerTecla(nota);
        By teclaElem = By.cssSelector("span[data-note='" + tecla + "']");

        try {
            return new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(driver -> !tieneClaseActive(teclaElem));
        } catch (Exception e) {
            return false;
        }
    }
}
