package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {
	Comando vai;
	Partita partita;
	IO io;
	private Labirinto labirinto;
	private Partita partita2;
	@Before
	public void setUp() throws Exception {
		io=new IOconsole();
		vai=new ComandoVai();
		
		
		labirinto = new LabirintoBuilder()
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
	
	/*Verifica che il il metodo Esegui vada correttamente a sud*/

	@Test
	public void testEseguiVaiSud() {
		vai.setParametro("sud");
		vai.esegui(partita);
		assertEquals("Aula N10",partita.getStanzaCorrente().getNome());
		
	}
	

	/*Verifica che dopo aver eseguito 
	 * il metodo Esegui i CFU vengano correttamente scalati*/

	
	@Test
	public void TestEseguiCFUVaisud() {
		vai.setParametro("sud");
		vai.esegui(partita);
		assertEquals(19,partita.getGiocatore().getCfu());
	}
	
	
	/*Verifica che se al metodo Esegui viene passato 
	 *una direzione inesistente, non cambia stanza*/
	@Test
	public void testEseguiVaiDirezioneInesistente() {
		vai.setParametro("ciao");
		vai.esegui(partita);
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
		
	}
	/*Verifica che se e al metodo Esegui viene passato 
	 *una direzione inesistente,i CFU non vengono scalati */
	@Test 
	public void testEseguiCFUVaiDirezioneInesistente(){
		vai.setParametro("ciao");
		vai.esegui(partita);
		assertEquals(20,partita.getGiocatore().getCfu());
	}
	
	/*Verifica che se e al metodo Esegui viene passato 
	 *null,la stanza non venga cambiata */
	@Test
	public void testVaiDirezioneNull() {
		vai.esegui(partita);
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
		
	}
	/*Verifica che se e al metodo Esegui viene passato 
	 *null,i CFU non vengono scalati */
	@Test
	public void testEseguiCFUVaiDirezioneNull() {
		vai.esegui(partita);
	
		assertEquals(20,partita.getGiocatore().getCfu());
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
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
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
