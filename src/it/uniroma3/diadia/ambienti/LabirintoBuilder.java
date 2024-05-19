package it.uniroma3.diadia.ambienti;

import java.util.LinkedList;
import java.util.List;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{

	private List<Stanza> Stanze;
	
	
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
	
	
	public  Stanza getStanza(String s1) {
		for( Stanza r: this.Stanze) {
			if(r!=null)
				if(r.getNome().equals(s1))
					return r;
		}
		return null;
	}
	
	
	public LabirintoBuilder addAdiacenza(String ns1,String ns2,String Direzione) {
			 Stanza s1= getStanza(ns1);
			 Stanza s2= getStanza(ns2);
			 s1.impostaStanzaAdiacente(Direzione, s2);
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
	
	
	
	
	public List<Stanza>getListaStanze() {
		return Stanze;
		
	}
	
	
	
	
}
