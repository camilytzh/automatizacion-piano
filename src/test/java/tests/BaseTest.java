package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.PianoPage;
/**
 * Clase base para todos los tests.
 * Encargada de gestionar el ciclo de vida del WebDriver para antes y después de cada prueba.
 */
public class BaseTest {
    protected WebDriver driver;
    protected PianoPage pianoPage;
    /**
     * Se encarga de inicializar el WebDriver, abrir el navegador.
     * Inicializa la página piano y abre la url.
     */
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pianoPage = new PianoPage(driver, "https://www.musicca.com/es/piano");
    }
    /**
     * Cierra el navegador.
     */
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
