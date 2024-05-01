package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	private StanzaProtected stanzaIniziale;
	private StanzaProtected stanzaVincente;
	
	
	public Labirinto() {
		init();
	}
	
	private void init(){
			/* crea gli attrezzi */
	    	Attrezzo lanterna = new Attrezzo("lanterna",3);
			Attrezzo osso = new Attrezzo("osso",1);
			Attrezzo passpartout= new Attrezzo("passpartout",1);
	
	    	
			/* crea stanze del labirinto */
			StanzaProtected atrio = new StanzaProtected("Atrio");
			StanzaBloccata aulaN11 = new StanzaBloccata("Aula N11","est","passpartout");
			StanzaMagicaProtected aulaN10 = new StanzaMagicaProtected("Aula N10");
			StanzaBuia laboratorio = new StanzaBuia("Laboratorio Campus","lanterna");
			StanzaProtected biblioteca = new StanzaProtected("Biblioteca");
			
			/* collega le stanze */
			atrio.impostaStanzaAdiacente("nord", biblioteca);
			atrio.impostaStanzaAdiacente("est", aulaN11);
			atrio.impostaStanzaAdiacente("sud", aulaN10);
			atrio.impostaStanzaAdiacente("ovest", laboratorio);
			aulaN11.impostaStanzaAdiacente("est", laboratorio);
			aulaN11.impostaStanzaAdiacente("ovest", atrio);
			aulaN10.impostaStanzaAdiacente("nord", atrio);
			aulaN10.impostaStanzaAdiacente("est", aulaN11);
			aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
			laboratorio.impostaStanzaAdiacente("est", atrio);
			laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
			biblioteca.impostaStanzaAdiacente("sud", atrio);

	        /* pone gli attrezzi nelle stanze */
			aulaN10.addAttrezzo(lanterna);
			atrio.addAttrezzo(osso);
			atrio.addAttrezzo(passpartout);


			// il gioco comincia nell'atrio
	        stanzaIniziale = atrio;  
			stanzaVincente = biblioteca;
	}
	
	public StanzaProtected getStanzaVincente() {
		return this.stanzaVincente;
	}

	public StanzaProtected getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
}
