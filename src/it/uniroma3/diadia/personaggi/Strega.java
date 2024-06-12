package it.uniroma3.diadia.personaggi;

import java.util.Collections;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreStanzaPerAttrezzi;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Strega extends AbstractPersonaggio {


	public Strega(String nome, String presentaz) {
		super(nome, presentaz);

	}

	@Override
	public String agisci(Partita partita) {
		ComparatoreStanzaPerAttrezzi cmp= new ComparatoreStanzaPerAttrezzi();
		if(!super.haSalutato()) {

			partita.setStanzaCorrente(Collections.min(partita.getStanzaCorrente().getMapStanzeAdiacenti().values(),cmp));
		}
		else partita.setStanzaCorrente(Collections.max(partita.getStanzaCorrente().getMapStanzeAdiacenti().values(),cmp));
		return "Guarda ora dove sei...";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return "Grazie per il regalo, ahahahahahahah";
	}

}
