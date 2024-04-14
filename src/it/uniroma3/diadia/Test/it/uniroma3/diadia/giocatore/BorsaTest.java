package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private Borsa borsa;
	private Attrezzo Osso;
	private Attrezzo Lanterna;
	private Attrezzo Spada;
	private Borsa borsaConPiuAttrezzi;
	
	@Before
	public void setUp(){
		borsa=new Borsa();
		Osso= new Attrezzo("Osso",4);
		Lanterna= new Attrezzo("Lanterna",1);
		Spada=new Attrezzo("Spada",3);
		
		borsaConPiuAttrezzi=new Borsa();
		borsaConPiuAttrezzi.addAttrezzo(Osso);
		borsaConPiuAttrezzi.addAttrezzo(Lanterna);
		borsaConPiuAttrezzi.addAttrezzo(Spada);
	}
	
	

	/*Verifica che addAttrezzo ritorni false se si prova ad inserire
	 *  un'attrezzo con peso maggiore di 10*/
	@Test
	public void testAddAttrezzoPesoAttrezzoMaggiore10() {
		assertFalse(borsa.addAttrezzo(new Attrezzo("Martello",13)));
	}
	
	/*Verifica che addAttrezzo ritorni true 
	 * se inserito un'attrezzo con peso minore di 10*/
	@Test
	public void testAddAttrezzoPesoMinore10() {
		assertFalse(borsa.hasAttrezzo("Osso")); //verifica che non era presente
		assertTrue(borsa.addAttrezzo(new Attrezzo("Osso",4)));	
		assertTrue(borsa.hasAttrezzo("Osso")); //verifica che sia stato realmente inserito
		
	}
	/*Verifica che addAttrezzo ritorni False se viene inserito un attrezzo con peso
	 * minore di 10 ma la somma dei pesi è maggiore di 10*/
	@Test
	public void testAddAttrezzoPesoAttrezziMaggiore10() {
		assertFalse(borsaConPiuAttrezzi.addAttrezzo(new Attrezzo("Forchetta",6)));
		
	}
		
	
	@Test
	/*Funzione che verifica che il metodo getPeso
	 *  ritorni 0 se la borsa è vuota*/
	public void testGetPesoBorsaVuota() {
		assertEquals(0,borsa.getPeso());
	}
	
	/*Funzione che verifica che il metodo getPeso 
	 * ritorni correttamente il peso della borsa*/
	@Test 
	public void testGetPesoBorsaConAttrezzi() {
		assertEquals(8,borsaConPiuAttrezzi.getPeso());
	}
	
	
	/*Verifica che getAttrezzo ritorni null
	 * se non è presente*/
	@Test
	public void TestgetAttrezzoNull() {
		assertNull(borsaConPiuAttrezzi.getAttrezzo("Forchetta"));
	}
	
	/*Verifica che getAttrezzo ritorni correttamente l'attrezzo 
	 * che si trova in prima posizione*/
	@Test
	public void TestgetAttrezzoPrimaPosizione() {
		
		assertEquals(this.Osso,borsaConPiuAttrezzi.getAttrezzo("Osso"));
	}
	
	/*Verifica che getAttrezzo ritorni correttammente l'attrezzo
	 * nel caso questo si trova in mezzo all'arrray */
	@Test
	public void TestgetAttrezzoMetaPosizione() {
		assertEquals(this.Lanterna,borsaConPiuAttrezzi.getAttrezzo("Lanterna"));
	}
	
	/*Verifica che getAttrezzo ritorni correttammente l'attrezzo
	 *  nel caso questo si trova in ultima posizione */
	@Test
	public void TestgetAttrezzoInUltimaPosizione() {
		assertEquals(this.Spada,borsaConPiuAttrezzi.getAttrezzo("Spada"));
	}
	
	
	/*Verifica che la borsa sia vuota (non ci sono attrezzi)*/
	@Test
	public void testIsEmpty() {
		assertTrue(borsa.isEmpty());
	}
	
	
	/*Verifica che la borsa non sia vuota (ci sono attrezzi)*/
	@Test
	public void testIsNotEmpty() {
		assertFalse(borsaConPiuAttrezzi.isEmpty());
	}
	
	
	
	/*Verifica che la borsa abbia quell'attrezzo*/
	@Test
	public void testHasAttrezzo() {
		assertTrue(borsaConPiuAttrezzi.hasAttrezzo("Osso"));
	}
	/*Verifica che la borsa non abbia quell'attrezzo*/
	@Test
	public void testNotHasAttrezzo() {
		assertFalse(borsaConPiuAttrezzi.hasAttrezzo("Forchetta"));
	}
	
	
	
	
	/*Test che verifica che removeAttrezzo ritorni Null 
	 * se l'attrezzo non è presente*/
	@Test
	public void testRemoveAttrezzoBorsaNonPresente() {
		assertNull(borsa.removeAttrezzo("Spada"));
	}
	
	
	
	@Test
	/*Verifica che in una borsa con 3 elementi venga rimosso 
	 * correttamente il primo attrezzo*/
	public void testRemovePrimoAttrezzo() {
		assertEquals(this.Osso,borsaConPiuAttrezzi.removeAttrezzo("Osso"));
		assertFalse(borsaConPiuAttrezzi.hasAttrezzo("Osso")); // verifica che sia stato correttamente rimosso
	}
	@Test
	/*Verifica che in una borsa con 3 elementi venga rimosso
	 *  correttamente l'ultimo elemento*/
	public void testRemoveUltimoAttrezzo() {
		assertEquals(this.Spada,borsaConPiuAttrezzi.removeAttrezzo("Spada"));
		assertFalse(borsaConPiuAttrezzi.hasAttrezzo("Spada")); // verifica che sia stato correttamente rimosso
	}
	@Test
	/*Verifica che in una borsa con 3 elementi venga rimosso
	 *  correttamente l'elemento in mezzo*/
	public void testRemoveAttrezzoAmeta() {
		assertEquals(this.Lanterna,borsaConPiuAttrezzi.removeAttrezzo("Lanterna"));
		assertFalse(borsaConPiuAttrezzi.hasAttrezzo("Lanterna")); // verifica che sia stato correttamente rimosso
	}

}
