package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;




public class FabbricaDiComandiFisarmonicaTest {
	FabbricaDiComandi l;
	ComandoVai vai;
	@Before
	public void setUp() {
		l= new FabbricaDiComandiFisarmonica();
		
	}

	@Test
	public void testCostruisciComandoVai() {
		assertEquals("vai",l.costruisciComando("vai nord").getNome());
		assertEquals("nord",l.costruisciComando("vai nord").getParametro());
		
	}
	@Test
	public void testCostruisciComandoFine() {
		assertEquals("fine",l.costruisciComando("fine").getNome());
		
	}
	
	
	@Test
	public void testCostruisciComandoPrendi() {
		assertEquals("prendi",l.costruisciComando("prendi osso").getNome());
		assertEquals("osso",l.costruisciComando("prendi osso").getParametro());
	}
	@Test
	public void testCostruisciComandoPosa() {
		assertEquals("posa",l.costruisciComando("posa osso").getNome());
		assertEquals("osso",l.costruisciComando("posa osso").getParametro());
	}
	@Test
	public void testCostruisciComandoGuarda() {
		assertEquals("guarda",l.costruisciComando("guarda").getNome());
	
	}
	@Test
	public void testCostruisciComandoNonValido() {
		assertEquals("non valido",l.costruisciComando("non valido").getNome());
	
	}
	@Test
	public void testCostruisciComandoNonAiuto() {
		assertEquals("aiuto",l.costruisciComando("aiuto").getNome());
	
	}
	

}
