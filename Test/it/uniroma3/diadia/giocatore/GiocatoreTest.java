package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	
	private Giocatore giocatore1;
	@Before
	public void setUp(){
		giocatore1=new Giocatore();
		
	}

	/*Test che verifica che getCFU ritorni i cfu iniziali*/
	@Test
	public void testgetCfu() {
		
		assertEquals(20,giocatore1.getCfu());
		
	}
	/*Test che verifica che setCfu cambi correttamente i CFU del giocatore*/
	@Test
	public void testSetCfu() {
		giocatore1.setCfu(10);
		assertEquals(10,giocatore1.getCfu());
	}
	
	/*Test che verifica  che La borsa non sia pari a null*/
	@Test 
	public void testGetBorsa() {
		assertNotNull(giocatore1.getBorsa());
	}

}
