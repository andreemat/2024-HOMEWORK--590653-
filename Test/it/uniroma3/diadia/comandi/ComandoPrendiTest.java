package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPrendiTest {
	Partita partita;
	ComandoPrendi prendi;
	IO io;
	Attrezzo coltello;
	private Labirinto labirinto;
	@Before
	public void setUp() throws Exception {
		io=new IOconsole();
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("LabCampusOne") 
				.addAttrezzo("Scopa",2)
				.addStanza("Stanza2") 
				.addAdiacenza("LabCampusOne","Stanza2","ovest") 
				.addAdiacenza("Stanza2","LabCampusOne","est") 
				.addAttrezzo("Spada",2)
				.addStanzaVincente("Stanza2")
				.getLabirinto();
		partita=new Partita(io,labirinto);
		prendi= new ComandoPrendi();

		
		
	}
	/*Test che verifica il corretto funzionamendo del ComandoPrendi 
	 * prendendo l'attrezzo che si trova nell'atrio (Stanza iniziale)*/
	@Test
	public void testComandoPrendi() {
		prendi.setParametro("Scopa");
		prendi.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("Scopa"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("Scopa"));
	}

}
