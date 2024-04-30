package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {
	Comando vai;
	Partita partita;
	IOconsole IO;
	@Before
	public void setUp() throws Exception {
		IO=new IOconsole();
		vai=new ComandoVai();
		
		partita=new Partita(IO);

	}

	@Test
	public void testEseguiVaiSud() {
		vai.setParametro("sud");
		vai.esegui(partita);
		assertEquals("Aula N10",partita.getStanzaCorrente().getNome());
		
	}
	
	@Test
	public void TestEseguiCFUVaisud() {
		vai.setParametro("sud");
		vai.esegui(partita);
		assertEquals(19,partita.getGiocatore().getCfu());
	}
	
	
	
	@Test
	public void testEseguiVaiDirezioneInesistente() {
		vai.setParametro("ciao");
		vai.esegui(partita);
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
		
	}
	@Test 
	public void testEseguiCFUVaiDirezioneInesistente(){
		vai.setParametro("ciao");
		vai.esegui(partita);
		assertEquals(20,partita.getGiocatore().getCfu());
	}

	@Test
	public void testVaiDirezioneNull() {
		vai.esegui(partita);
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
		
	}
	
	@Test
	public void testEseguiCFUVaiDirezioneNull() {
		vai.esegui(partita);
	
		assertEquals(20,partita.getGiocatore().getCfu());
	}
	

}
