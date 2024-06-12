package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {
    FabbricaDiComandi l;
    ComandoVai vai;

    @Before
    public void setUp() {
        l = new FabbricaDiComandiFisarmonica();
    }

    /* verifica la corretta costruzione del metodo ComandoVai */
    @Test
    public void testCostruisciComandoVai() {
        assertEquals("vai", l.costruisciComando("vai nord").getNome());
        assertEquals("nord", l.costruisciComando("vai nord").getParametro());
    }

    /* verifica la corretta costruzione del metodo ComandFine */
    @Test
    public void testCostruisciComandoFine() {
        assertEquals("fine", l.costruisciComando("fine").getNome());
    }

    /* verifica la corretta costruzione del metodo ComandoPrendi */
    @Test
    public void testCostruisciComandoPrendi() {
        assertEquals("prendi", l.costruisciComando("prendi osso").getNome());
        assertEquals("osso", l.costruisciComando("prendi osso").getParametro());
    }

    /* verifica la corretta costruzione del metodo ComandoPosa */
    @Test
    public void testCostruisciComandoPosa() {
        assertEquals("posa", l.costruisciComando("posa osso").getNome());
        assertEquals("osso", l.costruisciComando("posa osso").getParametro());
    }

    /* verifica la corretta costruzione del metodo ComandoGuarda */
    @Test
    public void testCostruisciComandoGuarda() {
        assertEquals("guarda", l.costruisciComando("guarda").getNome());
    }

    /* verifica la corretta costruzione del metodo ComandoNonValido */
    @Test
    public void testCostruisciComandoNonValido() {
        assertEquals("non valido", l.costruisciComando("non valido").getNome());
    }

    @Test
    public void testCostruisciComandoNonAiuto() {
        assertEquals("aiuto", l.costruisciComando("aiuto").getNome());
    }
}
