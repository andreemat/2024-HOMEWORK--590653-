package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	StanzaBloccata Stanza1;
	Stanza Stanza2;
	Stanza Stanza3;
	Attrezzo pass;
	
	@Before
	public void setUp() {
		pass = new Attrezzo("pass",1);
		Stanza1 = new StanzaBloccata("Stanza1","sud","pass");
		Stanza3= new Stanza("Stanza3");
		Stanza2 =new Stanza("Stanza2");
		Stanza1.impostaStanzaAdiacente("sud", Stanza2);
		Stanza1.impostaStanzaAdiacente("est", Stanza3);
	}

	@Test
	public void testStanzaBloccataDirezioneBloccata() {
		assertEquals(Stanza1,Stanza1.getStanzaAdiacente("sud"));
	}
	@Test
	public void testStanzaSBloccataConAttrezzoDirezioneSbloccata(){
		Stanza1.addAttrezzo(pass);
		assertEquals(Stanza2,Stanza1.getStanzaAdiacente("sud"));
	}
	@Test
	public void testStanzaBloccataConAttrezzoDirezioneNonBloccata(){
		assertEquals(Stanza3,Stanza1.getStanzaAdiacente("est"));
	}
}
