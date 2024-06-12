package it.uniroma3.diadia.ambienti;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public Labirinto(String Nomefile) throws FormatoFileNonValidoException, IOException  {
		CaricatoreLabirinto c;

		c = new CaricatoreLabirinto(Nomefile);
		c.carica();

		this.stanzaIniziale=c.getStanzaIniziale();
		this.stanzaVincente= c.getStanzaVincente();

	}
	
	public Labirinto() {
		init();
	}
	
	public static Labirinto.LabirintoBuilder newBuilder(){
		return new Labirinto.LabirintoBuilder();
	}

	public void init(){
		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("Lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo passpartout= new Attrezzo("passpartout",1);
		Attrezzo martello = new Attrezzo("Martello",3);
		Attrezzo ampolla=new Attrezzo("ampolla",3);
		Attrezzo Corda = new Attrezzo("Corda",1);
		Mago merlino = new Mago("Merlino","Ciao sono mago merlino",ampolla);
		Cane rocco = new Cane("Rocco","Regalami un osso e vedrai cosa succederà..","osso",Corda);
		Strega strega = new Strega("strega","ciao");
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		StanzaBloccata aulaN11 = new StanzaBloccata("Aula N11","est","passpartout");
		StanzaMagica aulaN10 = new StanzaMagica("Aula N10");
		StanzaBuia laboratorio = new StanzaBuia("Laboratorio Campus","lanterna");
		Stanza biblioteca = new Stanza("Biblioteca");

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
		aulaN10.addAttrezzo(martello);
		atrio.SetPersonaggio(merlino);
		aulaN11.SetPersonaggio(rocco);
		aulaN10.SetPersonaggio(strega);
		// il gioco comincia nell'atrio
		stanzaIniziale = atrio;  
		stanzaVincente = biblioteca;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public void SetStanzaIniziale(Stanza StanzaIniziale) {
		this.stanzaIniziale=StanzaIniziale;
	}
	public void SetStanzaVincente(Stanza StanzaVincente) {
		this.stanzaVincente=StanzaVincente;
	}
	
	public static class LabirintoBuilder extends Labirinto{

		private List<Stanza> Stanze;
		
		
		public LabirintoBuilder(String NomeFile) throws FormatoFileNonValidoException, IOException {
			super(NomeFile);
			
			Stanze= new LinkedList<>();
			
		}
		
		public LabirintoBuilder() {
			super();
			
			Stanze= new LinkedList<>();
			
		}
		
		
		public LabirintoBuilder addStanzaIniziale(String Stanza) {
			Stanza in = new Stanza(Stanza);
			this.Stanze.add(in);
			super.SetStanzaIniziale(in);
			
			return this;
			
		}
		public LabirintoBuilder addStanzaVincente(String Stanza) {
			Stanza vin = new Stanza(Stanza);
			super.SetStanzaVincente(vin);
			this.Stanze.add(vin);
			return this;
			
		}
		
		public LabirintoBuilder addStanza(String NomeStanza) {
			if(this.getStanza(NomeStanza)==null) {
				Stanza Stanza1= new Stanza(NomeStanza);
				this.Stanze.add(Stanza1);
			}
			return this;
			
		}
		
		public Labirinto getLabirinto() {
			return this;
		}
		
		
		public LabirintoBuilder addAttrezzo(String attrezzo, int Peso) {
		
			if(!this.Stanze.isEmpty())
				this.Stanze.get(this.Stanze.size()-1).addAttrezzo(new Attrezzo(attrezzo,Peso));
			return this;
			
		}
		
		public LabirintoBuilder addAttrezzo(String stanza, Attrezzo att) {
			Stanza Stanza2 = this.getStanza(stanza); 
			Stanza2.addAttrezzo(att);
			return this;
			
		}
		
		
		
		public  Stanza getStanza(String s1) {
			for( Stanza r: this.Stanze) {
				if(r!=null)
					if(r.getNome().equals(s1))
						return r;
			}
			return null;
		}
		
		
		public LabirintoBuilder addAdiacenza(String ns1,String ns2,String Direzione) {
			try {
				Stanza s1= getStanza(ns1);
				 Stanza s2= getStanza(ns2);
				 s1.impostaStanzaAdiacente(Direzione, s2);
			}catch(IllegalArgumentException e) {
				
			};
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String Stanza, String Direzione,String Attrezzo) {
			Stanza Stanza1= new StanzaBloccata(Stanza,Direzione,Attrezzo);
			this.Stanze.add(Stanza1);
			return this;
			
		}
		
		public LabirintoBuilder addStanzaMagica(String Stanza, int soglia) {
			Stanza Stanza1= new StanzaMagica(Stanza,soglia);
			this.Stanze.add(Stanza1);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String Stanza, String NomeAttrezzo) {
			Stanza Stanza1= new StanzaBuia(Stanza, NomeAttrezzo);
			this.Stanze.add(Stanza1);
			return this;
		}
		
		public LabirintoBuilder addPersonaggio(AbstractPersonaggio pg,String NomeStanza) {
			Stanza StanzaPersonaggio = getStanza(NomeStanza);
			StanzaPersonaggio.SetPersonaggio(pg);
			return this;
		}
		
		public LabirintoBuilder addCane(String Nome,String Descrizione,Attrezzo attrezzo,String preferito,String Stanza) {
			Cane cane = new Cane(Nome,Descrizione,preferito,attrezzo);
			this.addPersonaggio(cane, Stanza);
			return this;
		}
		
		public LabirintoBuilder addMago(String Nome,String Descrizione,Attrezzo attrezzo,String Stanza) {
			Mago mago = new Mago(Nome,Descrizione,attrezzo);
			this.addPersonaggio(mago, Stanza);
			return this;
		}
		      
		public LabirintoBuilder addStrega(String Nome,String Descrizione,String Stanza) {
			Strega strega = new Strega(Nome,Descrizione);
			this.addPersonaggio(strega, Stanza);
			return this;
		}
		
		
		public List<Stanza>getListaStanze() {
			return Stanze;
			
		}
		
		
	}

}
