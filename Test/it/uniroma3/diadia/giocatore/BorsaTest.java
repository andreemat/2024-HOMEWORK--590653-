package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;


import it.uniroma3.diadia.attrezzi.Attrezzo;


public class BorsaTest {

	private Borsa borsa;
	private Attrezzo Osso;
	private Attrezzo Lanterna;
	private Attrezzo Spada;
	private Borsa borsaConPiuAttrezzi;
	private Borsa borsaConAttrezziStessoPeso;
	private Borsa borsaConAttrezziDecrescente;
	private Borsa borsaConAttrezziNomiUguali;

	
	@Before
	public void setUp() throws FileNotFoundException, IOException{
		borsa=new Borsa(100);
		Osso= new Attrezzo("Osso",4);
		Lanterna= new Attrezzo("Lanterna",3);
		Spada=new Attrezzo("Spada",3);
		
		borsaConPiuAttrezzi=new Borsa(100);
		borsaConPiuAttrezzi.addAttrezzo(Osso);
		borsaConPiuAttrezzi.addAttrezzo(Lanterna);
		borsaConPiuAttrezzi.addAttrezzo(Spada);
		
		borsaConAttrezziStessoPeso = new Borsa(100);
		borsaConAttrezziStessoPeso.addAttrezzo(new Attrezzo("Martello", 5));
		borsaConAttrezziStessoPeso.addAttrezzo(new Attrezzo("Osso", 5));
		borsaConAttrezziStessoPeso.addAttrezzo(new Attrezzo("Lanterna", 5));
	
		
		borsaConAttrezziDecrescente = new Borsa();
		borsaConAttrezziDecrescente.addAttrezzo(new Attrezzo("Zappa", 5));
		borsaConAttrezziDecrescente.addAttrezzo(new Attrezzo("Osso", 2));
		borsaConAttrezziDecrescente.addAttrezzo(new Attrezzo("Bottiglia", 1));
	
		
		borsaConAttrezziNomiUguali = new Borsa();
		borsaConAttrezziNomiUguali.addAttrezzo(new Attrezzo("Zappa", 5));
		borsaConAttrezziNomiUguali.addAttrezzo(new Attrezzo("Zappa", 2));
		borsaConAttrezziNomiUguali.addAttrezzo(new Attrezzo("Zappa", 1));
	
		

	}
	
	
	

	
	/*Verifica che addAttrezzo ritorni true 
	 * se inserito un'attrezzo con peso minore di 10*/
	@Test
	public void testAddAttrezzoPesoMinore10() {
		assertFalse(borsa.hasAttrezzo("Osso")); //verifica che non era presente
		assertTrue(borsa.addAttrezzo(new Attrezzo("Osso",4)));	
		assertTrue(borsa.hasAttrezzo("Osso")); //verifica che sia stato realmente inserito
		
	}
			
	
	/*TEST METODO:getPesoBorsa*/
	
	@Test
	/*Funzione che verifica che il metodo getPeso
	 *  ritorni 0 se la borsa è vuota*/
	public void testGetPesoBorsaVuota() {
		assertEquals(0,borsa.getPeso());
	}
	
	
	
	
	
	/*TEST METODO:getAttrezzo*/
	
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
	
	
	/*TEST METODO:isEmpty*/
	
	
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
	
	
	

	/*TEST METODO: hasAttrezzo*/
	

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
	
	
	
	
	/*TEST METODO: removeAttrezzo*/
	
	
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
	
	
	@Test
	public void TestgetContenutoOrdinatoPerPesoBorsaVuota() {
		
		assertTrue( this.borsa.getContenutoOrdinatoPerPeso().isEmpty());
		
	}
	@Test
	public void TestgetContenutoOrdinatoPerPesoPesiUguali() {
		Iterator<Attrezzo> i=  this.borsaConAttrezziStessoPeso.getContenutoOrdinatoPerPeso().iterator();
		List<Attrezzo> l=this.borsaConAttrezziStessoPeso.getContenutoOrdinatoPerPeso();
		for(Attrezzo a :l)
			System.out.println(a.getNome());
		assertEquals("Lanterna",i.next().getNome());
		assertEquals("Martello",i.next().getNome());
		assertEquals("Osso",i.next().getNome());
		
	}
	@Test
	public void TestgetContenutoOrdinatoPerPesoPesiDecrescenti() {
		Iterator<Attrezzo> i=  this.borsaConAttrezziDecrescente.getContenutoOrdinatoPerPeso().iterator();
		assertEquals("Bottiglia",i.next().getNome());
		assertEquals("Osso",i.next().getNome());
		assertEquals("Zappa",i.next().getNome());
		
	}
	
	
	@Test
	public void TestgetContenutoOrdinatoPerPesoCasuale() {
		Iterator<Attrezzo> i=  this.borsaConPiuAttrezzi.getContenutoOrdinatoPerPeso().iterator();
		assertEquals("Lanterna",i.next().getNome());
		assertEquals("Spada",i.next().getNome());
		assertEquals("Osso",i.next().getNome());
		
	}
	
	@Test
	public void TestgetContenutoOrdinatoPerNome() {
		Iterator<Attrezzo> i=  this.borsaConAttrezziDecrescente.getContenutoOrdinatoPerNome().iterator();
		assertEquals("Bottiglia",i.next().getNome());
		assertEquals("Osso",i.next().getNome());
		assertEquals("Zappa",i.next().getNome());
		
		
		
	}
	
	@Test
	public void TestgetContenutoOrdinatoPerNomiUguali() {
		Iterator<Attrezzo> i=  this.borsaConAttrezziDecrescente.getContenutoOrdinatoPerNome().iterator();
		assertEquals(1,i.next().getPeso());
		assertEquals(2,i.next().getPeso());
		assertEquals(5,i.next().getPeso());
		
	}

	
	
	

	
	@Test
	public void TestgetSortedSetOrdinatoPerPesoPesiUguali() {
		Iterator<Attrezzo> i=  this.borsaConAttrezziStessoPeso.getSortedSetOrdinatoPerPeso().iterator();
		assertEquals("Lanterna",i.next().getNome());
		assertEquals("Martello",i.next().getNome());
		assertEquals("Osso",i.next().getNome());
		
	}
	@Test
	public void TestgetSortedSetOrdinatoPerPesoPesiDecrescenti() {
		Iterator<Attrezzo> i=  this.borsaConAttrezziDecrescente.getSortedSetOrdinatoPerPeso().iterator();
		assertEquals("Bottiglia",i.next().getNome());
		assertEquals("Osso",i.next().getNome());
		assertEquals("Zappa",i.next().getNome());
		
	}
	
	
	@Test
	public void TestgetSortedSetOrdinatoPerPesoCasuale() {
		Iterator<Attrezzo> i=  this.borsaConPiuAttrezzi.getSortedSetOrdinatoPerPeso().iterator();
		assertEquals("Lanterna",i.next().getNome());
		assertEquals("Spada",i.next().getNome());
		assertEquals("Osso",i.next().getNome());
		
	}
	
	
	@Test
	public void TestgetContenutoRaggruppatoPerPesoBorsaVuota() {
		assertTrue(this.borsa.getContenutoRaggruppatoPerPeso().isEmpty());
	}
	
	
	@Test
	public void TestgetContenutoRaggruppatoPerPesoBorsaConPesiUguali() {
		Iterator<Attrezzo> i=  this.borsaConAttrezziStessoPeso.getContenutoRaggruppatoPerPeso().get(5).iterator();

		assertEquals("Lanterna",i.next().getNome());
		assertEquals("Martello",i.next().getNome());
		assertEquals("Osso",i.next().getNome());
	}
	
	@Test
	public void TestgetContenutoRaggruppatoPerPesoBorsaConPesiDiverso() {
		Iterator<Attrezzo> i=  this.borsaConAttrezziDecrescente.getContenutoRaggruppatoPerPeso().get(1).iterator();
		assertEquals("Bottiglia",i.next().getNome());
		Iterator<Attrezzo> j=  this.borsaConAttrezziDecrescente.getContenutoRaggruppatoPerPeso().get(2).iterator();
		assertEquals("Osso",j.next().getNome());
		Iterator<Attrezzo> k=  this.borsaConAttrezziDecrescente.getContenutoRaggruppatoPerPeso().get(5).iterator();
		assertEquals("Zappa",k.next().getNome());
	}
	
}
