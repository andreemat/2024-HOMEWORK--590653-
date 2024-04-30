package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {
	Partita partita;
	ComandoPosa posa;
	IOconsole IO;
	Attrezzo coltello;
	@Before
	public void setUp() throws Exception {
		IO=new IOconsole();
		partita=new Partita(IO);
		posa= new ComandoPosa();
		coltello= new Attrezzo("coltello",1);
		partita.getGiocatore().getBorsa().addAttrezzo(coltello);
		
	}

	@Test
	public void testEseguiComandoPosaAttrezzo() {
		
		posa.setParametro("coltello");
		posa.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("coltello"));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("coltello"));
	}

	@Test
	public void testSetParametroNull() {
		posa.setParametro(null);
		assertNull(posa.getParametro());
	}
	
	@Test
	public void testSetParametro() {
		posa.setParametro("coltello");
		assertEquals("coltello",posa.getParametro());
	}
}
