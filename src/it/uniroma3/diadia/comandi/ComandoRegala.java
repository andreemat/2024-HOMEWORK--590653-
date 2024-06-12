package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoRegala extends AbstractComando {
	public String nomeComando;
	private String NomeAttrezzo;
	
	
	public ComandoRegala() {
		super.setNome("regala");
	}
	@Override
	public void esegui(Partita partita) {
		if(NomeAttrezzo==null) {
			partita.getIO().mostraMessaggio("Che attrezzo vuoi regalare? Specificalo come parametro ");
		}
		else {
			if(partita.getStanzaCorrente().hasPersonaggio() && partita.getGiocatore().getBorsa().hasAttrezzo(NomeAttrezzo)){
				partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(NomeAttrezzo), partita));
						
			}
			else partita.getIO().mostraMessaggio("Non puoi regalare questo oggetto");
		}
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	

	@Override
	public void setParametro(String parametro) {
		this.NomeAttrezzo=parametro;

	}

}
