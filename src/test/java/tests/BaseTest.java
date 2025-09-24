package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.PianoPage;

public class BaseTest {
    protected WebDriver driver;
    protected PianoPage pianoPage;
    @BeforeMethod
    public void beforeMethod(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pianoPage = new PianoPage(driver, "https://www.musicca.com/es/piano");
    }
    @AfterMethod
    public void afterMethod(){
        driver.close();
    }
}
