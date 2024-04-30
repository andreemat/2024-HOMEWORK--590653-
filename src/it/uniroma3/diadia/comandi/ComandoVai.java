package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	
	private String direzione;
	private String nome;
	  public ComandoVai() {
		  this.nome="vai";
	      
	    
	}
	  /**
	    * esecuzione del comando
	    */
	  @Override
	  public void esegui(Partita partita) {
		  Stanza stanzaCorrente = partita.getStanzaCorrente ();
		  Stanza prossimaStanza = null;
		  if (direzione==null) {
			  partita.getIO().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
					  return;
		  }
		  prossimaStanza = stanzaCorrente.getStanzaAdiacente (this.direzione);
		  if (prossimaStanza==null) {
			  System.out.println ("Direzione inesistente");
			  return;
		  }
		  partita.setStanzaCorrente (prossimaStanza);
		  partita.getIO().mostraMessaggio(partita.getStanzaCorrente ().getNome()) ;
		  partita.getGiocatore () .setCfu (partita.getGiocatore () .getCfu () -1) ;
	  }
	  @Override
	  public void setParametro(String parametro) {
		  this.direzione=parametro;
	  }
	  
		@Override
		public String getNome() {
			return this.nome;
		}
		@Override
		public String getParametro() {
			return this.direzione;
		}

}