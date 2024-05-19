package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private StanzaMagica StanzaMagica;
	private Attrezzo Attrezzo2;

	@Before
	public void setUp() throws Exception {
		 StanzaMagica = new StanzaMagica("StanzaMagica", 1);
		 Attrezzo2 = new Attrezzo("attrezzo2",1);
		
	}

	@Test
	public void testModificaAttrezzoPosatoSuperaSoglia() {
		StanzaMagica.addAttrezzo(Attrezzo2);
		StanzaMagica.removeAttrezzo(Attrezzo2);
		StanzaMagica.addAttrezzo(Attrezzo2);
		assertEquals("2ozzertta",StanzaMagica.getAttrezzo("2ozzertta").getNome());
		assertEquals(2,StanzaMagica.getAttrezzo("2ozzertta").getPeso());
	}
	
	@Test
	public void testModificaAttrezzoSogliaNonSuperata() {
		StanzaMagica.addAttrezzo(Attrezzo2);
	
		assertEquals("attrezzo2",StanzaMagica.getAttrezzo("attrezzo2").getNome());
		assertEquals(1,StanzaMagica.getAttrezzo("attrezzo2").getPeso());
	}

}
