package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Map;

public class PianoPage extends BasePage {
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
    }
    public String obtenerTecla(String nota){
        return mapaNotas.get(nota);
    }

    public void presionarTecla(String nota) {
        String tecla = obtenerTecla(nota);
        By teclaClickable = By.cssSelector("span[data-note='" + tecla + "']");

        clickWithJS(teclaClickable);
    }
    public boolean esPresionadaLaTecla(String nota){
        String tecla = obtenerTecla(nota);
        By teclaElem = By.cssSelector("span[data-note='" + tecla + "']");
        return tieneClase(teclaElem, "active");
    }
}
