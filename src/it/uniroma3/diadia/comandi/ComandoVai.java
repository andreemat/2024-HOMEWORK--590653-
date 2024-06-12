package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{

	private String direzione;


	  public ComandoVai() {
		  super.setNome("vai");
		  
	      
	    
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
		  try {
		  prossimaStanza = stanzaCorrente.getStanzaAdiacente (this.direzione);
		  if (prossimaStanza==null) {
			  partita.getIO().mostraMessaggio("Direzione inesistente");
			  return;
		  }
		  
		  partita.setStanzaCorrente (prossimaStanza);
		  partita.getIO().mostraMessaggio(partita.getStanzaCorrente ().getNome()) ;
		  partita.getGiocatore () .setCfu (partita.getGiocatore () .getCfu () -1) ;
		  }
		  catch(IllegalArgumentException e){
			  partita.getIO().mostraMessaggio(" Devi specificare una direzione valida");
			  return;
			  
		  }
	  }
	  @Override
	  public void setParametro(String parametro) {
		  this.direzione=parametro;
	  }
	  
	
}


