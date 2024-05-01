package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {
	Partita partita;
	ComandoPosa posa;
	IO io;
	Attrezzo coltello;
	@Before
	public void setUp() throws Exception {
		io=new IOconsole();
		partita=new Partita(io);
		posa= new ComandoPosa();
		coltello= new Attrezzo("coltello",1);
		partita.getGiocatore().getBorsa().addAttrezzo(coltello);
		
	}
	
	/*Test che verifica il corretto funzionamendo del ComandoPosa 
	 * posando l'attrezzo nell' nell'atrio (Stanza iniziale)*/

	@Test
	public void testEseguiComandoPosaAttrezzo() {
		
		posa.setParametro("coltello");
		posa.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("coltello"));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("coltello"));
	}

	/*Test che verifica che SetParametro imposti a null il paramentro
	 * ritorni null se viene passato null come parametro. 
	 * */
	
	@Test
	public void testSetParametroNull() {
		posa.setParametro(null);
		assertNull(posa.getParametro());
	}
	
	/*Test che verifica il corretto funzionamendo del 
	 * metodo SetParametro 
	 * */
	
	@Test
	public void testSetParametro() {
		posa.setParametro("coltello");
		assertEquals("coltello",posa.getParametro());
	}
}
