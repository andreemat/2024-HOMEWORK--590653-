package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPosaTest {
	Partita partita;
	AbstractComando posa;
	IO io;
	Attrezzo coltello;
	private Labirinto.LabirintoBuilder labirinto;
	@Before
	public void setUp() throws Exception {
		io=new IOconsole();
		labirinto = (LabirintoBuilder) new Labirinto.LabirintoBuilder()
				.addStanzaIniziale("LabCampusOne") 
				.addAttrezzo("Scopa",2)
				.addStanza("Stanza2") 
				.addAdiacenza("LabCampusOne","Stanza2","ovest") 
				.addAdiacenza("Stanza2","LabCampusOne","est") 
				.addAttrezzo("Spada",2)
				.addStanzaVincente("Stanza2")
				.getLabirinto();
		partita=new Partita(io,labirinto);
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
