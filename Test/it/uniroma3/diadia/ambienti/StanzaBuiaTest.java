package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	

	private StanzaBuia Stanza1;
	private Attrezzo Spada;

	@Before
	public void setUp() throws Exception {
		Stanza1= new StanzaBuia("S1","Spada");
		Spada= new Attrezzo("Spada",1);
	}

	@Test
	public void testStanzaBuiaSenzaSpada() {
		assertEquals("Qui c'è buio pesto",Stanza1.getDescrizione());
	}
	@Test
	public void testStanzaBuiaConSpada() {
		Stanza1.addAttrezzo(Spada);
		assertNotEquals("Qui c'è buio pesto",Stanza1.getDescrizione());
	}


}
