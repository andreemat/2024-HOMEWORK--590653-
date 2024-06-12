package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;


public class ComandoVaiTest {
	AbstractComando vai;
	Partita partita;
	IO io;
	private Labirinto labirinto;
	private Partita partita2;
	@Before
	public void setUp() throws Exception {
		io=new IOconsole();
		vai=new ComandoVai();
		
		
		labirinto = new Labirinto.LabirintoBuilder()
				.addStanzaIniziale("LabCampusOne") 
				.addStanza("Stanza2") 
				.addAdiacenza("LabCampusOne","Stanza2","ovest") 
				.addAdiacenza("Stanza2","LabCampusOne","est") 
				.addAttrezzo("Spada",2)
				.addStanzaVincente("Stanza2")
				.getLabirinto();
		partita2=new Partita(io,labirinto);
		partita = new Partita(io);

	}
	
		
	/* TEST LABIRINTO BUILDER */
	
	@Test
	public void testEseguiVaiOvestLabirintoBuilder() {
		vai.setParametro("ovest");
		vai.esegui(partita2);
		assertEquals("Stanza2",partita2.getStanzaCorrente().getNome());
		assertEquals(19,partita2.getGiocatore().getCfu());
		
	}
	
	@Test
	public void testVaiDirezioneNullLabirintoBuilder() {
		vai.setParametro("nord");
		vai.esegui(partita2);
		assertEquals("LabCampusOne",partita2.getStanzaCorrente().getNome());
		assertEquals(20,partita2.getGiocatore().getCfu());
		
	}
	
	@Test
	public void testEseguiVaiDueDirezioniLabirintoBuilder() {
		vai.setParametro("ovest");
		vai.esegui(partita2);
		assertEquals("Stanza2",partita2.getStanzaCorrente().getNome());
		assertEquals(19,partita2.getGiocatore().getCfu());
		vai.setParametro("est");
		vai.esegui(partita2);
		assertEquals("LabCampusOne",partita2.getStanzaCorrente().getNome());
		assertEquals(18,partita2.getGiocatore().getCfu());
	
		
	}
	

	
	
	
}
