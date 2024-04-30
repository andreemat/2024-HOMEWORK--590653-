package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	private String NomeAttrezzo;


	public StanzaBuia(String nome,String NomeAttrezzo) {
		super(nome);
		this.NomeAttrezzo=NomeAttrezzo;
		
	}
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(NomeAttrezzo))
			return super.getDescrizione();
			
		return ("Qui c'è buio pesto");
	}
	
	
}
