package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	private String NomeAttrezzo;
	private String NomeComando;
	public ComandoPosa() {
		this.NomeComando="posa";
	}
	@Override
	public void esegui(Partita partita) {
		if(NomeAttrezzo==null) {
			partita.getIO().mostraMessaggio("Che attrezzo vuoi posare? ");
		}
		else {
			if(partita.getGiocatore().getBorsa().hasAttrezzo(NomeAttrezzo)){
				Attrezzo attrezzo=partita.getGiocatore().getBorsa().removeAttrezzo(NomeAttrezzo);
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
		this.NomeAttrezzo=parametro;

	}
	
	
	@Override
	public String getNome() {
		return this.NomeComando;
	}
	@Override
	public String getParametro() {
		return this.NomeAttrezzo;
	}

}
