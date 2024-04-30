package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	String DirezioneBloccata;
	String AttrezzoSbloccaDirezione;
	public StanzaBloccata(String nome,String DirezioneBloccata,String AttrezzoSbloccaDirezione) {
		super(nome);
		this.DirezioneBloccata=DirezioneBloccata;
		this.AttrezzoSbloccaDirezione=AttrezzoSbloccaDirezione;
	}
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if(!this.hasAttrezzo(this.AttrezzoSbloccaDirezione) &&dir.equals(this.DirezioneBloccata))
			return this;
		return super.getStanzaAdiacente(dir);
		
	}

	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(this.AttrezzoSbloccaDirezione))
			return super.getDescrizione()+"\n"+
					"Per sbloccare la direzione "+DirezioneBloccata+
					" devi posare nella stanza l'attrezzo "+AttrezzoSbloccaDirezione+".";
		return super.getDescrizione();
	}
	
}
