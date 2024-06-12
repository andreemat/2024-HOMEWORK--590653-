package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {

	  public ComandoPosa() {
		  super.setNome("posa");
	}
	@Override
	public void esegui(Partita partita) {
		if(this.parametro==null) {
			partita.getIO().mostraMessaggio("Che attrezzo vuoi posare? ");
		}
		else {
			if(partita.getGiocatore().getBorsa().hasAttrezzo(this.parametro)){
				Attrezzo attrezzo=partita.getGiocatore().getBorsa().removeAttrezzo(this.parametro);
				partita.getStanzaCorrente().addAttrezzo(attrezzo);
				partita.getIO().mostraMessaggio("hai posato l'attrezzo: "+ attrezzo);
			}
			else partita.getIO().mostraMessaggio("Attrezzo non presente nella borsa");
		}
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}

	
	@Override
	public void setParametro(String parametro) {
	
		this.parametro=parametro;

	}




}
