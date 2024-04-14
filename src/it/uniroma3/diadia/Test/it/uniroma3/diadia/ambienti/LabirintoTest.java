package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	private Labirinto labirinto;

	@Before
	public void setUp(){
		 labirinto = new Labirinto();
		
	}

	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca",labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testGetStanzaIniziale() {
		assertEquals("Atrio",labirinto.getStanzaIniziale().getNome());
	}
		


}
