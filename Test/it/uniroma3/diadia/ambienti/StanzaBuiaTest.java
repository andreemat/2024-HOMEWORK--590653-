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
	/*Metodo di test che verifica che se in Stanza Buia non è presente l'attrezzo
	 * Spada, viene stampata la descrizione "Qui c'è buio pesto"*/
	@Test
	public void testStanzaBuiaSenzaSpada() {
		assertEquals("Qui c'è buio pesto",Stanza1.getDescrizione());
	}
	
	/*Metodo di test che verifica che se in Stanza Buia è presente l'attrezzo
	 * Spada, non viene stampata la descrizione "Qui c'è buio pesto"*/
	@Test
	public void testStanzaBuiaConSpada() {
		Stanza1.addAttrezzo(Spada);
		assertNotEquals("Qui c'è buio pesto",Stanza1.getDescrizione());
	}


}
