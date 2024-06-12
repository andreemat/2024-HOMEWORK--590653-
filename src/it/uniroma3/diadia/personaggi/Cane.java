package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	
	
			
	private static final String MESSAGGIO_SCUSE = "Mi spiace per il morso. Ma grazie per l'osso";

	private String preferito;
	private Attrezzo attrezzo;
	

	public Cane(String nome, String presentaz,String preferito,Attrezzo attrezzo) {
		super(nome, presentaz);
		this.attrezzo=attrezzo;
		this.preferito=preferito;
	
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_SCUSE ;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		if(attrezzo.getNome().equals(preferito)) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo=new Attrezzo(attrezzo.getNome(),attrezzo.getPeso());
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			return MESSAGGIO_SCUSE;
		}
		return "Questo cibo non mi piace";
		
	}

}
