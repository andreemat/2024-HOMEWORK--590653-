package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirintoTest {
	private String monolocale;
	private String bilocale;
	private CaricatoreLabirinto cl; 
	@Before
	public void setUp() throws Exception {
		
		 monolocale = 
				"Stanze: atrio\n"+
				"Stanze magiche: \n"+
				"Stanze buie: \n"+
				"Stanze bloccate: \n"+
				"Inizio: atrio\n"+
				"Vincente: atrio\n"+
				"Attrezzi: \n"+
				"Uscite: \n"+
				"Maghi: \n"+
				"Cani: \n"+
				"Streghe: \n";
		
				
		 bilocale = 
				"Stanze: N10, N11\n"+
				"Stanze magiche: \n"+
				"Stanze buie: \n"+
				"Stanze bloccate: \n"+
				"Inizio: N10\n"+
				"Vincente: N11\n"+
				"Attrezzi: martello 3 N10\n"+
				"Uscite: \n"+
				"Maghi: \n"+
				"Cani: \n"+
				"Streghe: \n";
				

	

	}
		@Test
		public void testMonolocale() throws FormatoFileNonValidoException, FileNotFoundException {
			cl = new CaricatoreLabirinto(new StringReader(monolocale));
			cl.carica();
			assertEquals("atrio", this.cl.getStanzaIniziale().getNome());
			assertEquals("atrio", this.cl.getStanzaVincente().getNome());
			}
		
		@Test
		public void testBilocale() throws FormatoFileNonValidoException, FileNotFoundException {
			cl = new CaricatoreLabirinto(new StringReader(bilocale));
			cl.carica();
			assertEquals("N10", this.cl.getStanzaIniziale().getNome());
			assertEquals("N11", this.cl.getStanzaVincente().getNome());
			}
		
		
		@Test
		public void testBilocaleAttrezzo() throws FormatoFileNonValidoException, FileNotFoundException {
			cl = new CaricatoreLabirinto(new StringReader(bilocale));
			cl.carica();
			Attrezzo expected = new Attrezzo("martello", 3);
			assertEquals(expected, this.cl.getStanzaIniziale().getAttrezzo("martello"));
			}


}
