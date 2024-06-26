package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;


import org.junit.Before;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtectedTest {
	private StanzaProtected Stanza1;
	private StanzaProtected Stanza2;
	private StanzaProtected Stanza3;
	private StanzaProtected StanzaCon10attrezzi;

	
	private Attrezzo Spada;

	private Attrezzo Lanterna;
	private Attrezzo Osso;
	private Attrezzo x;
	
	@Before
	public void SetUp() {
		this.Stanza1 =new StanzaProtected("N10");
		this.Stanza2= new StanzaProtected("N11");
		this.Stanza3=new StanzaProtected("Stanza3");

		
		this.StanzaCon10attrezzi=new StanzaProtected("StanzaCon10Attrezzi");

		for(int i=0;i<10;i++) {
			x =new Attrezzo("lama"+i,1);
			StanzaCon10attrezzi.addAttrezzo(x);
		}
		
		Spada= new Attrezzo("Spada",1);
	
		this.Stanza1.addAttrezzo(Spada);
		
		this.Lanterna=new Attrezzo("Lanterna",3);
		this.Osso=new Attrezzo("osso",2);
		
		this.Stanza2.addAttrezzo(Lanterna);
		this.Stanza2.addAttrezzo(Osso);
		
		
	}
	
	/*FUNZIONI DI TEST METODO: HasAttrezzo()*/
	
	/*Metodo di test che verifica che il metodo hasAttrezzo() ritorni True 
	 * se la stanza possiede quell'attrezzo*/
	@Test
	public void TesthasAttrezzoStanza1() {
		assertTrue(Stanza1.hasAttrezzo("Spada"));
	}
	
	/*Metodo di test che verifica che hasAttrezzo ritorni False 
	 * se la Stanza non possiede quel dato attrezzo"*/
	@Test
	public void TesthasNotAttrezzoStanza1() {
		assertFalse(Stanza1.hasAttrezzo("Lanterna"));
	}
	
	/*Metodo di test che verifica che hasAttrezzo ritorni True 
	 * anche se la Stanza possiede più attrezzi dello stesso nome"*/
	@Test
	public void Testhas2AttrezzoStanza1() {
		this.Stanza1.addAttrezzo(new Attrezzo("Spada",1)); //ora sono presenti due attrezzi di nome Spada.
		assertTrue(Stanza1.hasAttrezzo("Spada"));
	}
	
	
	
	
	/*FUNZIONE DI TEST METODO: SetStanzaAdiacente() */
	
	
	/*Test che verifica che getStanzaAdiacente ritorni Null se in quella direzione
	 * non è stata impostata nessuna adiacente
	 */
	@Test
	public void TestGetStanzaAdiacenteNull() {
		assertNull(Stanza1.getStanzaAdiacente("nord"));
	}
	

	/*Test che verifica che impostaStanzaAdiacente imposti correttamente la stanza adiacente 
	 * nella direzione passata come parametro
	 */
	@Test
	public void TestStanzaAdiacente() {
		this.Stanza2.impostaStanzaAdiacente("ovest", Stanza1);
		assertEquals(Stanza1,Stanza2.getStanzaAdiacente("ovest"));
	}
	
	
	/*Test che verifica che impostaStanzaAdiacente aggiorni correttamente 
	 * la nuova stanza adiacente  nella direzione passata come parametro;
	 * se una era gia presente in quella.
	 */
	@Test
	public void TestAggiornaStanzaAdiacenteDirezioneOccupata() {
		this.Stanza2.impostaStanzaAdiacente("ovest", Stanza1);
		assertEquals(Stanza1,Stanza2.getStanzaAdiacente("ovest"));
		/*ora l'adiacente è Stanza1*/
		this.Stanza2.impostaStanzaAdiacente("ovest", Stanza3);
		assertEquals(Stanza3,Stanza2.getStanzaAdiacente("ovest"));
		/*ora l'adiacente è stanza3*/

	}

	
	
	
	
	
	/*FUNZIONI DI TEST METODO: addAttrezzo()*/
	
	/*Test che verifica che venga inserito un nuovo attrezzo in una stanza vuota*/
	@Test
	public void TestStanzaAddAttrezzoStanzaVuota() {
		assertTrue(this.Stanza3.addAttrezzo(new Attrezzo("Lanterna",3)));
		/*aggiunto l'attrezzo nella stanza vuota*/
		assertTrue(Stanza3.hasAttrezzo("Lanterna")); // verifica che sia stato inserito
	}

	
	
	/*Test che verifica che  venga inserito un nuovo attrezzo (non presente) 
	 * in una stanza gia con più attrezzi*/
	@Test
	public void TestStanzaPiuAttrezziAddAttrezzo() {
		assertFalse(this.Stanza2.hasAttrezzo("Spada")); //verifica che non era presente
		assertTrue(this.Stanza2.addAttrezzo(this.Spada)); //aggiunto un attrezzo
		assertTrue(Stanza2.hasAttrezzo("Spada")); // verifica che sia stato inserito
	}
	
	

	
	
	
	/*FUNZIONI DI TEST METODO: removeAttrezzo()*/
	
	
	/* test che verifica che il metodo remove attrezzo rimuova un attrezzo 
	 * presente in un array di un solo elemento*/
	@Test
	public void TestRemove1AttrezzoDaArrrayDi1soloElemento() {
		/* la stanza contiene l'attrezzo "Spada"*/
		assertTrue(this.Stanza1.hasAttrezzo("Spada"));
		assertTrue(this.Stanza1.removeAttrezzo(this.Spada));
		/*la stanza non contiene più "Spada"*/
		assertFalse(this.Stanza1.hasAttrezzo("Spada"));	//verifica che sia stato correttamente rimosso
	}
	
	/* test che verifica che il metodo remove attrezzo NON rimuova un attrezzo 
	 * (ritornando False) se non presente in attrezzi[]*/

	@Test
	public void TestRemoveAttrezzoStanzaSenzaAttrezzoX() {
		assertFalse(this.Stanza3.removeAttrezzo(Spada)); 
	}
	
	/* test che verifica che il metodo remove attrezzo 
	 * rimuova l' ultimo attrezzo presente nell'array*/
	@Test
	public void TestRemoveSecondoEdUltimoAttrezzoNellArray() {
		assertTrue(this.Stanza2.hasAttrezzo("osso"));
		/*"osso" è presente nella stanza come attrezzo ed è l'ultimo*/
		assertTrue(this.Stanza2.removeAttrezzo(this.Osso));
		/*la stanza non contiene più "osso"*/
		assertFalse(this.Stanza2.hasAttrezzo("osso")); //verifica che sia stato correttamente rimosso
		
	}
	
	/* test che verifica che il metodo remove attrezzo rimuova il primo attrezzo 
	 * presente nell'array di 2 elementi*/
	@Test
	public void TestRemovePrimoAttrezzoNellArray() {
		assertTrue(this.Stanza2.hasAttrezzo("Lanterna"));
		/*"Lanterna" è presente nella stanza come attrezzo*/
		assertTrue(this.Stanza2.removeAttrezzo(Lanterna));
		/*la stanza non contiene più "Lanterna"*/
		assertFalse(this.Stanza2.hasAttrezzo("Lanterna")); //verifica che sia stato correttamente rimosso
		
	}

	/* test che verifica che il metodo remove attrezzo 
	 * rimuova l'elemento a metà all'array*/
	@Test
	public void TestRemoveSecondoAttrezzoNellArray() {
		this.Stanza2.addAttrezzo(new Attrezzo("martello",1));
		/*"ora osso è a metà dell'array di attrezzi*/
		this.Stanza2.removeAttrezzo(Osso);
		/*la stanza non contiene più "osso"*/
		assertFalse(this.Stanza2.hasAttrezzo("osso"));
		
	}
}
