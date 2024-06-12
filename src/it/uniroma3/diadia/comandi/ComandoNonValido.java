package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	
	public ComandoNonValido() {
		super.setNome("non valido");
	}

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Comando non valido");

	}


}
