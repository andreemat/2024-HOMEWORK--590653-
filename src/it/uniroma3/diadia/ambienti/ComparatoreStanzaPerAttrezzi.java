package it.uniroma3.diadia.ambienti;

import java.util.Comparator;

public class ComparatoreStanzaPerAttrezzi implements Comparator<Stanza> {

	@Override
	public int compare(Stanza o1, Stanza o2) {
		return o1.getAttrezzi().size()-o2.getAttrezzi().size();
	}

}
