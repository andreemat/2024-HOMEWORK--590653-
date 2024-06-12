package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	public String nomeComando;
	private String NomeAttrezzo;
	
	
	public ComandoPrendi() {
		super.setNome("prendi");
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
	

	@Override
	public void setParametro(String parametro) {
		this.NomeAttrezzo=parametro;

	}
	

}
