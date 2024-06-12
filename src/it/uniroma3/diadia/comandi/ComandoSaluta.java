package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoSaluta extends AbstractComando{
	public String nomeComando;

	
	
	public ComandoSaluta() {
		super.setNome("saluta");
	}
	@Override
	public void esegui(Partita partita) {
	
			if(partita.getStanzaCorrente().hasPersonaggio()){
				 partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());		
			}
			else partita.getIO().mostraMessaggio("In questa stanza non si torva nessuna");
		}

	}
	


