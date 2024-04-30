package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPrendiTest {
	Partita partita;
	ComandoPrendi prendi;
	IOconsole IO;
	Attrezzo coltello;
	@Before
	public void setUp() throws Exception {
		IO=new IOconsole();
		partita=new Partita(IO);
		prendi= new ComandoPrendi();

		
		
	}

	@Test
	public void testComandoPrendi() {
		prendi.setParametro("osso");
		prendi.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}

}
