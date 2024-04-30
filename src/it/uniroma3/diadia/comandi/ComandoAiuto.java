package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	String nome;
	String parametro;
	
	public ComandoAiuto() {
		this.nome="aiuto";
		
	}
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda"};
	@Override
	public void esegui(Partita partita) {
		
		String Comandi= new String();
		for(int i=0; i< elencoComandi.length; i++) 
			Comandi= Comandi+ (elencoComandi[i]+" ");
		
		partita.getIO().mostraMessaggio(Comandi);
		partita.getIO().mostraMessaggio("\n");
	

	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}
	@Override
	public String getNome() {
		return this.nome;
	}
	@Override
	public String getParametro() {
		return this.parametro;
	}
}
