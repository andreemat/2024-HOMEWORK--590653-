package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPrendiTest {
	Partita partita;
	ComandoPrendi prendi;
	IO io;
	Attrezzo coltello;
	@Before
	public void setUp() throws Exception {
		io=new IOconsole();
		partita=new Partita(io);
		prendi= new ComandoPrendi();

		
		
	}
	/*Test che verifica il corretto funzionamendo del ComandoPrendi 
	 * prendendo l'attrezzo che si trova nell'atrio (Stanza iniziale)*/
	@Test
	public void testComandoPrendi() {
		prendi.setParametro("osso");
		prendi.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}

}
