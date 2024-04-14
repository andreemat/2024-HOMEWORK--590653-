/**
 * 
 */
package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;


public class PartitaTest {
	
	private Partita partita;

	private Stanza Stanza1;
	private Stanza Stanza2;
	
	@Before
	public void SetUp() {
		this.partita=new Partita();
		this.Stanza1= new Stanza("N10");
		this.Stanza2= new Stanza("N9");
	}
	
	
	/*Classi di test per il metodo SetStanzaCorrente */
	@Test
	/* Verifica che il metodoSetStanzaCorrente ritorni la Stanza iniziale se appena inizializzata*/
	public void testSetStanzaCorrenteIniziale() {
		assertEquals(partita.getLabirinto().getStanzaIniziale(),partita.getStanzaCorrente());
	}
	
	
	@Test
	/* Verifica che il metodo setStanzaCorrente imposti la stanza correttamente passata come parametro*/
	public  void testSetStanzaCorrenteUnaStanza() {
		this.partita.setStanzaCorrente(this.Stanza1);
		assertEquals(this.Stanza1,partita.getStanzaCorrente());
	}
	
	/*Verificah che il metodo SetStanzaCorrente ritorni null se la stanzacorrente è pari a null*/
	@Test
	public void testSetStanzaNull() {
		this.partita.setStanzaCorrente(null);
		assertNull(this.partita.getStanzaCorrente());
	}
	
	
	@Test
	/* Metodo di Test che verifica il cambiamento di una stanza (passaggio da una all'altra),
	 * tramite il metodo setStanzaCorrente*/
	
	public void TestSetStanzaCorrenteCambiaStanze() {
		partita.setStanzaCorrente(Stanza1);
		assertEquals(this.Stanza1,this.partita.getStanzaCorrente());
		/*ora sono in Stanza1*/
		
		partita.setStanzaCorrente(Stanza2);
		assertEquals(this.Stanza2,this.partita.getStanzaCorrente());
		/*ora sono in Stanza2*/
	}
	
	
	
	
	
	
	
	
	/*Classi di Test per il metodo vinta */

	/*Classe di test che verifica se abbiamo vinto la partita trovandoci nella stanza vincente*/
	@Test 
	public void Testvinta() {
		partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	/*Classe di test che verifica che non abbiamo vinto la partita se non ci troviamo nella stanza vincente*/
	@Test 
	public void TestNonvinta() {
		partita.setStanzaCorrente(this.Stanza1);
		assertFalse(this.partita.vinta());
	}


	
	
	
	/*Classi di test per il metodo isFinita*/
	
	/*Metodo di Test che verifica se IsFinita è true se Finita è uguale a true.*/
	@Test 
	public void TestFinitaDaFinitaUgualeTrue() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	

	/*Metodo di Test che verifica se la partita finisce raggiunti 0 CFU*/
	@Test
	public void TestFinitaDaCFUugualiZero() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	
	/*Metodo di Test che verifica che la partita è finita se hai vinto*/	
	@Test
	public void TestPartitaFinitaVinta() {
		partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	/*Metodo di Test che verifica che la partita non finisce se non rispettate le condizioni*/	
	@Test
	public void TestPartitaNonFinita(){
		assertFalse(this.partita.isFinita());
		
	}
}
