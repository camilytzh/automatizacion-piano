package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static org.testng.Assert.*;
/**
 * Pruebas relacionadas con el ingreso de una secuencia de notas en el piano.
 */
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class PianoTests extends BaseTest{
    private Map<String, List<String>> escenariosNotas;
    /**
     * Carga los datos dentro del archivo json con las notas.
     */
    @BeforeClass
    public void cargarEscenarios() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File archivo = new File("src/test/resources/notas.json");
        escenariosNotas = mapper.readValue(archivo, Map.class);
    }
    /**
     * Verifica que cada tecla al estar presionada cambie a clase activa
     * mientras se reproduce una secuencia de notas.
     * Suelta la tecla luego de un tiempo y verifica si fue liberada con su clase.
     */
    @Test(description = "Escenario 1: Himno de la Alegría - Primera secuencia")
    public void escenario1(){
        List<String> notas = escenariosNotas.get("escenario1");
        for (String nota : notas) {
            Allure.step("Presionar nota " + nota, () -> {
                        pianoPage.presionarTeclaDown(nota);
                        assertTrue(pianoPage.esPresionadaLaTecla(nota), "La tecla " + nota + " no fue presionada");
                    });
            pianoPage.esperar(150);

            pianoPage.soltarTecla(nota);
            assertTrue(pianoPage.esLiberadaLaTecla(nota), "La tecla " + nota + " no fue liberada");
        }
    }
    @Test(description = "Escenario 2: Himno de la Alegría - Segunda secuencia")
    public void escenario2(){
        List<String> notas = escenariosNotas.get("escenario2");
        for(String nota : notas){
            Allure.step("Presionar nota " + nota, () -> {
                        pianoPage.presionarTeclaDown(nota);
                        assertTrue(pianoPage.esPresionadaLaTecla(nota), "La tecla " + nota + " no fue presionada");
                    });
            pianoPage.esperar(150);

            pianoPage.soltarTecla(nota);
            assertTrue(pianoPage.esLiberadaLaTecla(nota), "La tecla " + nota + " no fue liberada");
        }
    }
    @Test(description = "Escenario 3: Himno de la Alegría - Tercera secuencia")
    public void escenario3(){
        List<String> notas = escenariosNotas.get("escenario3");
        for(String nota : notas){
            Allure.step("Presionar nota " + nota, () -> {
                        pianoPage.presionarTeclaDown(nota);
                        assertTrue(pianoPage.esPresionadaLaTecla(nota), "La tecla " + nota + " no fue presionada");
                    });
            pianoPage.esperar(150);

            pianoPage.soltarTecla(nota);
            assertTrue(pianoPage.esLiberadaLaTecla(nota), "La tecla " + nota + " no fue liberada");
        }
    }
}
