package tests;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.testng.Assert.*;


public class PianoTests extends BaseTest{
    private static final List<String> NOTAS_ESCENARIO_1 = Arrays.asList(
            "si", "si", "do", "re", "re", "do", "si", "la",
            "sol", "sol", "la", "si", "si", "la", "la"
    );
    private static final List<String> NOTAS_ESCENARIO_3 = Stream.concat(
            Stream.of(
            "si", "si", "do", "re", "re", "do", "si", "la", "sol",
            "sol", "la", "si", "la", "sol", "sol", "la", "si", "sol",
            "la", "si", "do", "si","sol", "la", "si", "do", "si", "sol",
            "sol", "fa", "re"),
            NOTAS_ESCENARIO_1.stream()
    ).toList();
    @Test
    public void escenario1(){
        for(String nota : NOTAS_ESCENARIO_1){
            pianoPage.presionarTecla(nota);
            assertTrue(pianoPage.esPresionadaLaTecla(nota), "La tecla " + nota + " no fue presionada");
        }
    }
}
