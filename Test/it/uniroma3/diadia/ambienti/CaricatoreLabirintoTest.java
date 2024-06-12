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
				"Stanze: biblioteca\n"+
				"Stanze magiche: \n"+
				"Stanze buie: \n"+
				"Stanze bloccate: \n"+
				"Inizio: biblioteca\n"+
				"Vincente: biblioteca\n"+
				"Attrezzi: \n"+
				"Uscite: \n"+
				"Maghi: \n"+
				"Cani: \n"+
				"Streghe: \n";
		
				
		 bilocale = 
				"Stanze: N12, N11\n"+
				"Stanze magiche: \n"+
				"Stanze buie: \n"+
				"Stanze bloccate: \n"+
				"Inizio: N12\n"+
				"Vincente: N11\n"+
				"Attrezzi: martello 3 N12\n"+
				"Uscite: \n"+
				"Maghi: \n"+
				"Cani: \n"+
				"Streghe: \n";
				

	

	}
		@Test
		public void testMonolocale() throws FormatoFileNonValidoException, FileNotFoundException {
			cl = new CaricatoreLabirinto(new StringReader(monolocale));
			cl.carica();
			assertEquals("biblioteca", this.cl.getStanzaIniziale().getNome());
			assertEquals("biblioteca", this.cl.getStanzaVincente().getNome());
			}
		
		@Test
		public void testBilocale() throws FormatoFileNonValidoException, FileNotFoundException {
			cl = new CaricatoreLabirinto(new StringReader(bilocale));
			cl.carica();
			assertEquals("N12", this.cl.getStanzaIniziale().getNome());
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
