package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	
	
	public ComandoAiuto() {
		this.nome="aiuto";
		
	}
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda","interagisci","saluta"};
	@Override
	public void esegui(Partita partita) {
		String Comandi= new String();
		for(int i=0; i< elencoComandi.length; i++) 
			Comandi= Comandi+ (elencoComandi[i]+" ");
		
		partita.getIO().mostraMessaggio(Comandi);
		partita.getIO().mostraMessaggio("\n");
	

	}


	
}
