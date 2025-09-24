package tests;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;


public class PianoTests extends BaseTest{
    @Test
    public void escenario1(){
        List<String> notas = Arrays.asList("si", "si", "do", "re", "re", "do", "si", "la", "sol", "sol", "la", "si", "si", "la", "la");
        for(String nota : notas){
            pianoPage.presionarTecla(nota);
            assertTrue(pianoPage.esPresionadaLaTecla(nota), "La tecla " + nota + " no fue presionada");
        }
    }
}
