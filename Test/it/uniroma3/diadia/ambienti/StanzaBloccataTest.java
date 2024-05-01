package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	StanzaBloccata Stanza1;
	StanzaProtected Stanza2;
	StanzaProtected Stanza3;
	Attrezzo pass;
	
	@Before
	public void setUp() {
		pass = new Attrezzo("pass",1);
		Stanza1 = new StanzaBloccata("Stanza1","sud","pass");
		Stanza3= new StanzaProtected("Stanza3");
		Stanza2 =new StanzaProtected("Stanza2");
		Stanza1.impostaStanzaAdiacente("sud", Stanza2);
		Stanza1.impostaStanzaAdiacente("est", Stanza3);
	}
	
	/*Metodo di test che verifica che se nella stanza non è presente 
	 * l'attrezzo pass,non si può andare a sud,
	 *   rimanendo nella stessa stanza*/

	@Test
	public void testStanzaBloccataDirezioneBloccata() {
		assertEquals(Stanza1,Stanza1.getStanzaAdiacente("sud"));
	}
	
	
	/*Metodo di test che verifica che se nella stanza  è presente 
	 * l'attrezzo pass, si può andare a sud,
	 *   cambiando, quindi, stanza*/

	@Test
	public void testStanzaSBloccataConAttrezzoDirezioneSbloccata(){
		Stanza1.addAttrezzo(pass);
		assertEquals(Stanza2,Stanza1.getStanzaAdiacente("sud"));
	}
	
	
	/*Metodo di test che verifica che se nella stanza non è presente 
	 * l'attrezzo pass, si può andare in altre direzioni,
	 *anche se la sud è bloccata   */
	@Test
	public void testStanzaBloccataConAttrezzoDirezioneNonBloccata(){
		assertEquals(Stanza3,Stanza1.getStanzaAdiacente("est"));
	}
}
