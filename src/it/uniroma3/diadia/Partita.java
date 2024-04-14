//CORRETTO GIUSTO
package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {



	private Stanza stanzaCorrente;
	private Labirinto creastanze;
	private boolean finita;
	private Giocatore Giocatore1;

	
	
	public Partita(){
		creastanze= new Labirinto();
		Giocatore1=new Giocatore();
		stanzaCorrente=creastanze.getStanzaIniziale();
		this.finita = false;
		
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == creastanze.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (Giocatore1.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	public Giocatore getGiocatore() {
		return this.Giocatore1;
	}
	public Labirinto getLabirinto() {
		return this.creastanze;
	}
	
}
