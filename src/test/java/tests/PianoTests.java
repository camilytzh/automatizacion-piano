package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static org.testng.Assert.*;

public class PianoTests extends BaseTest{
    private Map<String, List<String>> escenariosNotas;
    @BeforeClass
    public void cargarEscenarios() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File archivo = new File("src/test/resources/notas.json");
        escenariosNotas = mapper.readValue(archivo, Map.class);
    }
    @Test(description = "Escenario 1: Himno de la Alegría - Primera secuencia")
    public void escenario1(){
        List<String> notas = escenariosNotas.get("escenario1");
        for (String nota : notas) {
            pianoPage.presionarTeclaDown(nota);
            assertTrue(pianoPage.esPresionadaLaTecla(nota), "La tecla " + nota + " no fue presionada");

            pianoPage.esperar(100);

            pianoPage.soltarTecla(nota);
            assertTrue(pianoPage.esLiberadaLaTecla(nota), "La tecla " + nota + " no fue liberada");
        }
    }
}
