package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	public String nomeComando;
	private String NomeAttrezzo;
	
	
	public ComandoPrendi() {
		this.nomeComando="prendi";
	}
	@Override
	public void esegui(Partita partita) {
		if(NomeAttrezzo==null) {
			partita.getIO().mostraMessaggio("Che attrezzo vuoi prendere? ");
		}
		else {
			if(partita.getStanzaCorrente().hasAttrezzo(NomeAttrezzo)){
			
				Attrezzo attrezzo=partita.getStanzaCorrente().getAttrezzo(NomeAttrezzo);
				partita.getStanzaCorrente().removeAttrezzo(attrezzo);
				partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
				partita.getIO().mostraMessaggio("hai preso l'attrezzo: "+partita.getGiocatore().getBorsa().getAttrezzo(NomeAttrezzo));				
			}
			else partita.getIO().mostraMessaggio("Attrezzo non presente nella stanza");
		}
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	


	public void setParametro(String parametro) {
		this.NomeAttrezzo=parametro;

	}
	
	@Override
	public String getNome() {
		return this.nomeComando;
	}
	@Override
	public String getParametro() {
		return this.NomeAttrezzo;
	}

}
